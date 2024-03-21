package unit8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        new ConcurrentHashMapTest().startTests();
    }

    public void startTests() {
        test1();
        test2();
    }

    private Map<Integer, Integer> testMap = new ConcurrentHashMap<>();

    /**
     * Fills the test HashMap with dummy values.
     */
    private void fillMap() {
        testMap.put(166, 13);
        testMap.put(669, 903);
        testMap.put(906, 532);
        testMap.put(795, 936);
        testMap.put(469, 602);
    }

    /**
     * Mark a delay.
     *
     * @param timeout milliseconds
     */
    private void sleepms(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs a writing operation which takes 1s to complete. Simultaneously, runs
     * two reading operations. The first one makes an immediate read, while the
     * second reads after the first task completion.
     */
    private void test1() {
        fillMap();
        try {
            // Create a pool of three threads
            ExecutorService threadExecutor = Executors
                    .newFixedThreadPool(3);
            // Runnable used to compute and write
            // a new value to key 906 after 1000ms
            Runnable writeAfter1Sec = () -> testMap.computeIfPresent(906,
                    (k, v) -> {
                        sleepms(1000);
                        return v + k;
                    });
            // Create two tasks
            // The first immediately reads the key 906
            // The second read the same key after 1003ms
            Callable<Integer> readNow = () -> testMap.get(906);
            Callable<Integer> readAfter1001Ms = () -> {
                sleepms(1003);
                return testMap.get(906);
            };
            // Uses the execution pool to run the three tasks
            threadExecutor.submit(writeAfter1Sec);
            List<Future<Integer>> results = threadExecutor.invokeAll(
                    Arrays.asList(readNow, readAfter1001Ms));
            // Print the results after completion
            System.out.println(results.get(0).get()); // 532
            System.out.println(results.get(1).get()); // 1438
            System.out.println(testMap);
            // Make necessary clean-ups
            if (!threadExecutor.awaitTermination(2, TimeUnit.SECONDS)) {
                threadExecutor.shutdown();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ConcurrentHashMapTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        testMap.clear();
    }

    /**
     * Runs two writing operations which take respectively 5s and 1s
     * to complete.
     */
    private void test2() {
        fillMap();
        try {
            // Creates a pool of two threads
            ExecutorService threadExecutor = Executors.newFixedThreadPool(2);
            // Writes a value after 5s
            Callable<Integer> writeAfter5Sec = () -> testMap.computeIfPresent(
                    906, (k, v) -> { sleepms(5000); return v + 5; });
            // Writes a value after 1s
            Callable<Integer> writeAfter1Sec = () -> testMap.computeIfPresent(
                    906, (k, v) -> { sleepms(1000); return v + 1; });
            // Launches the writing thread simultaneously
            List<Future<Integer>> results = threadExecutor.invokeAll(
                    Arrays.asList(writeAfter5Sec, writeAfter1Sec));
            // Prints the results ini
            System.out.println(results.get(0).get()); // 537
            System.out.println(results.get(1).get()); // 938
            System.out.println(testMap); // Final 906 key's value is 538
            if (!threadExecutor.awaitTermination(2, TimeUnit.SECONDS)) {
                threadExecutor.shutdown();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ConcurrentHashMapTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        testMap.clear();
    }
}
