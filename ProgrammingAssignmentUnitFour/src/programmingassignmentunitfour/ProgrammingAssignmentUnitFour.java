package programmingassignmentunitfour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author manianis
 */
public class ProgrammingAssignmentUnitFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Stock prices for the period of Jan, 29 to Feb, 9
        double[] stockPrices = new double[] { 58.32, 58.11, 58.60, 57.54, 56.92,
                56.24, 55.83, 56.74, 56.7, 56.88 };
        List<double[]> arrStockPrices = Arrays.asList(stockPrices);

        double max = findMaximumPrice(stockPrices);
        double min = findMinimumPrice(stockPrices);

        displayStocks(stockPrices);
        displayGraphic(stockPrices, min, max);
        displaySummary(stockPrices);

        stockPrices = generateRandomStock(15, 40, 80);
        max = findMaximumPrice(stockPrices);
        min = findMinimumPrice(stockPrices);
        displayStocks(stockPrices);
        displayGraphic(stockPrices, min, max);
        displaySummary(stockPrices);

        double[] bins = createBins(min, max, 5);
        int[] freq = calculateOccurences(stockPrices, bins);
        for (int i = 0; i < bins.length - 1; i++) {
            System.out.println("[" + bins[i] + ", " + bins[i + 1] + "] " + freq[i]);
        }
    }

    /**
     * Calculate the average price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the average price
     */
    public static double calculateAveragePrice(double[] stockPrices) {
        if (stockPrices.length == 0) {
            throw new IllegalArgumentException("The array is empty!");
        }
        double tsp = 0.0;
        for (double sp : stockPrices) {
            tsp += sp;
        }
        return tsp / stockPrices.length;
    }

    /**
     * Calculate the maximum price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the maximum price
     */
    public static double findMaximumPrice(double[] stockPrices) {
        if (stockPrices.length == 0) {
            throw new IllegalArgumentException("The array is empty!");
        }
        double msp = stockPrices[0];
        for (int i = 1; i < stockPrices.length; i++) {
            if (stockPrices[i] > msp) {
                msp = stockPrices[i];
            }
        }
        return msp;
    }

    /**
     * Calculate the number of occurrences of the number targetPrice. The price
     * is searched in the interval +/-delta
     *
     * @param stockPrices An array of stock prices.
     * @param targetPrice target price
     * @param delta       target price range
     * @return the number of occurrence
     */
    public static int countOccurences(double[] stockPrices,
            double targetPrice, double delta) {
        int count = 0;
        for (int i = 0; i < stockPrices.length; i++) {
            if (Math.abs(stockPrices[i] - targetPrice) <= delta) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculate the number of occurrences of the number targetPrice.
     *
     * @param stockPrices An array of stock prices.
     * @param targetPrice target price
     * @return the number of occurrence
     */
    public static int countOccurences(double[] stockPrices, double targetPrice) {
        return countOccurences(stockPrices, targetPrice, 0.01);
    }

    /**
     * Calculate the cumulative price of a stock.
     *
     * @param stockPrices An arrayList of stock prices.
     * @return an ArrayList of cumulative prices
     */
    public static ArrayList<Double> computeCumulativeSum(ArrayList<Double> stockPrices) {
        ArrayList<Double> cumSum = new ArrayList<>();
        double cs = 0.0;
        for (int i = 0; i < stockPrices.size(); i++) {
            cs += stockPrices.get(i);
            cumSum.add(cs);
        }
        return cumSum;
    }

    /**
     * Calculate the minimum price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the minimum price
     */
    public static double findMinimumPrice(double[] stockPrices) {
        if (stockPrices.length == 0) {
            throw new IllegalArgumentException("The array is empty!");
        }
        double msp = stockPrices[0];
        for (int i = 1; i < stockPrices.length; i++) {
            if (stockPrices[i] < msp) {
                msp = stockPrices[i];
            }
        }
        return msp;
    }

    /**
     * Calculate the standard deviation of the stock prices.
     *
     * @param stockPrices An array of stock prices.
     * @return the maximum price
     */
    public static double calculateStandardDeviation(double[] stockPrices) {
        if (stockPrices.length == 0) {
            throw new IllegalArgumentException("The array is empty!");
        }
        double sum = 0.0;
        double avg = calculateAveragePrice(stockPrices);
        for (int i = 1; i < stockPrices.length; i++) {
            sum += (stockPrices[i] - avg) * (stockPrices[i] - avg);
        }
        return Math.sqrt(sum / stockPrices.length);
    }

    /**
     * Calculate the Quartiles of the stocks prices.
     *
     * @param stockPrices An array of stock prices.
     * @return the quartiles
     */
    public static double[] findQuartiles(double[] stockPrices) {
        if (stockPrices.length == 0) {
            throw new IllegalArgumentException("The array is empty!");
        }
        double[] quart = new double[5];
        double[] spc = Arrays.copyOf(stockPrices, stockPrices.length);
        Arrays.sort(spc);
        for (int quartile = 0; quartile <= 4; quartile++) {
            double idx = (spc.length - 1) * quartile * .25;
            if (idx == (int) idx) {
                quart[quartile] = spc[(int) idx];
            } else {
                int idx1 = (int) Math.floor(idx);
                int idx2 = (int) Math.ceil(idx);
                quart[quartile] = (spc[idx1] + spc[idx2]) / 2;
            }
        }
        return quart;
    }

    public static void displayStocks(double[] stockPrices) {
        System.out.println("\n--- Stock List ---\n");
        boolean firstLoop = true;
        for (double sp : stockPrices) {
            if (!firstLoop) {
                System.out.print(", ");
            }
            System.out.print("$" + sp);
            firstLoop = false;
        }
        System.out.println();
    }

    public static void displayGraphic(double[] stockPrices, double min, double max) {
        System.out.println("\n--- Stock Prices Histogram ---\n");
        double step = (max - min) / 50;
        for (int i = 0; i < stockPrices.length; i++) {
            if (i == 0) {
                System.out.printf("Day | $%5.2f", min - step);
                displayChar(' ', 44);
                System.out.printf("| $%5.2f\n", max + step);
                displayChar('─', 65);
                System.out.println();
            }
            System.out.printf("%3d |", i + 1);
            displayChar('▓', (int) Math.ceil((stockPrices[i] - min + step) / step));
            System.out.printf(" $%5.2f\n", stockPrices[i]);
        }
    }

    /**
     * 
     * @param stockPrices
     */
    public static void displaySummary(double[] stockPrices) {
        double average = calculateAveragePrice(stockPrices);
        double std = calculateStandardDeviation(stockPrices);
        double max = findMaximumPrice(stockPrices);
        double min = findMinimumPrice(stockPrices);
        double[] quart = findQuartiles(stockPrices);
        double interQuart = quart[3] - quart[1];

        System.out.println("\n--- Stock Prices Summary ---\n");
        System.out.printf("Average: %5.2f\n", average);
        System.out.printf("Standard Deviation: %5.2f\n", std);
        System.out.printf("Minimum: %5.2f\n", min);
        System.out.printf("Q1: %5.2f\n", quart[1]);
        System.out.printf("Median: %5.2f\n", quart[2]);
        System.out.printf("Q3: %5.2f\n", quart[3]);
        System.out.printf("Maximum: %5.2f\n", max);
        System.out.printf("Q3 - Q1: %5.2f\n", interQuart);
    }

    /**
     * 
     * @param count
     * @param min
     * @param max
     * @return
     */
    public static double[] generateRandomStock(int count, double min, double max) {
        double[] items = new double[count];
        double nextDown = Math.nextDown(1.0);
        for (int i = 0; i < count; i++) {
            items[i] = Math.floor(((Math.random() / nextDown) * (max - min) + min) * 100) / 100;
        }
        return items;
    }

    /**
     * 
     * @param min
     * @param max
     * @param binsCount
     * @return
     */
    public static double[] createBins(double min, double max, int binsCount) {
        double[] bins = new double[binsCount + 1];
        double step = (max - min) / binsCount;
        bins[0] = min;
        for (int i = 1; i <= binsCount; i++) {
            bins[i] = bins[i - 1] + step;
        }
        return bins;
    }

    public static int[] calculateOccurences(double[] stockPrices, double[] bins) {
        int[] freq = new int[bins.length];
        for (int i = 0; i < stockPrices.length; i++) {
            int idx = indexOf(bins, stockPrices[i]);
            System.out.println(stockPrices[i] + " " + idx);
            freq[idx]++;
        }
        return freq;
    }

    public static int indexOf(double[] arr, double value) {
        int d = 0, f = arr.length - 1, mid = 0;
        while (f > d) {
            mid = (d + f) / 2;
            if (value >= arr[mid]) {
                d = mid;
            } else if (value < arr[mid]) {
                f = mid - 1;
            }
        }
        return mid;
    }

    private static void displayChar(char c, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
    }
}
