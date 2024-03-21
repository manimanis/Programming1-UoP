package unit8;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *
 * @author manianis
 */
public class ParallelStreams {

    public static void main(String[] args) {
        IntPredicate isPrime = (n) -> {
            if (n < 2) {
                return false;
            }
            if (n == 2 || n == 3 || n == 5 || n == 7) {
                return true;
            }
            if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0) {
                return false;
            }
            int mx = (int) Math.ceil(Math.sqrt(n) / 2);
            return !IntStream.rangeClosed(3, mx + 1)
                    .anyMatch(i -> n % (i + i + 1) == 0);
        };

        int n = 1_000_000;
        for (int i = 0; i < 7; i++) {
            long start = System.currentTimeMillis();
            long count = IntStream.range(0, n)
                    //.parallel()   
                    .filter(isPrime)
                    .count();
            System.out.println();
            long end = System.currentTimeMillis();
            System.out.println("Number: " + n);
            System.out.println("Prime numbers count: " + count);
            System.out.println("Time elapsed: " + formatTime(end - start));
            System.out.println("");
            n *= 2;
        }

    }

    private static String formatTime(long ms) {
        return String.format("%dmn %ds %dms",
                ms / 60_000,
                (ms % 60_000) / 1000,
                ms % 1000);
    }
}
