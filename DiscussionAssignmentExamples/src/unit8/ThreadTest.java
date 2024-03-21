package unit8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manianis
 */
public class ThreadTest {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTest.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> arr = new ArrayList<>();

        // Creates producer and consumer threads
        ProducerThread[] pt = new ProducerThread[3];
        for (int i = 0; i < pt.length; i++) {
            pt[i] = new ProducerThread(arr); pt[i].start();
        }
        ConsumerThread[] ct = new ConsumerThread[10];
        for (int i = 0; i < ct.length; i++) {
            ct[i] = new ConsumerThread(arr, i); ct[i].start();
        }

        // Creates monitor thread
        Thread monitor = new Thread(() -> {
            System.out.println("Monitor thread started!");
            // Give some time to producer and consumers
            // to do their jobs
            sleep(1000);

            // Stop all threads
            for (ProducerThread thread : pt) thread.stopThread();
            for (ConsumerThread thread : ct) thread.stopThread();

            // Wait for the threads to return
            for (ProducerThread thread : pt) join(thread);
            for (ConsumerThread thread : ct) join(thread);
            System.out.println("Monitor thread ended!");
        });
        monitor.start();
        monitor.join();

        System.out.println("Collection contains " + arr.size() + " items.");
        for (int i = 0; i < 10; i++) {
            final Integer x = i;
            System.out.print(i + ": ");
            int count = (int) arr.stream()
                    .filter((val) -> val % 10 == x)
                    .peek((val) -> System.out.print(val + ", "))
                    .count();
            System.out.println(" -> " + count);
        }
    }
}

class ProducerThread extends Thread {

    private static Random rand = new Random();

    private final Collection<Integer> collection;
    private boolean running = true;
    private int count = 0;

    public ProducerThread(Collection<Integer> collection) {
        this.collection = collection;
    }

    public synchronized void stopThread() {
        running = false;
    }

    public void add(Integer val) {
        synchronized (collection) {
            collection.add(val);
        }
    }

    @Override
    public void run() {
        System.out.println("Producer started!");
        while (running) {
            add(rand.nextInt(1, 99));
            count++;
            ThreadTest.sleep(10);
        }
        System.out.println("Producer produced " + count + " items.");
    }
}

class ConsumerThread extends Thread {

    private static final int MIN_ITEMS = 10;
    private final List<Integer> collection;
    private final int remainder;
    private boolean running = true;
    private int count = 0;

    public ConsumerThread(List<Integer> collection, int remainder) {
        this.collection = collection;
        this.remainder = remainder;
    }

    public synchronized void stopThread() {
        running = false;
    }

    public boolean hasEnoughItems() {
        int nb = 0;
        synchronized (collection) {
            for (int i = 0; i < collection.size(); i++) {
                Integer val = collection.get(i);
                if (val % 10 == remainder) {
                    nb++;
                }
            }
        }
        return nb >= MIN_ITEMS;
    }

    public void removeItems() {
        synchronized (collection) {
            int nb = 0, i = 0;
            while (i < collection.size() && nb < MIN_ITEMS) {
                int val = collection.get(i);
                if (val % 10 == remainder) {
                    if (nb == 0) {
                        System.out.print(remainder + ": ");
                    }
                    System.out.print(val + ", ");
                    collection.remove(i);
                    nb++;
                    count++;
                } else {
                    i++;
                }
            }
            if (nb != 0) {
                System.out.println();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Consumer " + remainder + " started!");
        while (running) {
            if (hasEnoughItems()) {
                removeItems();
            }
            ThreadTest.sleep(10);
        }
        if (hasEnoughItems()) {
            removeItems();
        }
        System.out.println("Consumer " + remainder
                + " consumed " + count + " items.");
    }
}
