package programmingassignmentunitfour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author manianis
 */
public class ProgrammingAssignmentUnitFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        // Stock prices for the period of Jan, 29 to Feb, 9
        double[] stockPrices = new double[] { 58.32, 58.11, 58.60, 57.54, 56.92,
                56.24, 55.83, 56.74, 56.7, 56.88 };
        double maxPr = findMaximumPrice(stockPrices);
        double minPr = findMinimumPrice(stockPrices);
        displayStocks(stockPrices);
        displaySummary(stockPrices);
        displayHistogram(stockPrices, minPr, maxPr);
        displayBarChart(stockPrices);

        stockPrices = generateRandomStock(15, 10, 15);
        maxPr = findMaximumPrice(stockPrices);
        minPr = findMinimumPrice(stockPrices);
        displayStocks(stockPrices);
        displaySummary(stockPrices);
        displayHistogram(stockPrices, minPr, maxPr);
        displayBarChart(stockPrices);

        List<double[]> arrStockPrices = Arrays.asList(stockPrices);
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

    public static int min(double[] arr) {
        int mn = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    public static int min(int[] arr) {
        int mn = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    public static int max(double[] arr) {
        int mx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[mx]) {
                mx = i;
            }
        }
        return mx;
    }

    public static int max(int[] arr) {
        int mx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[mx]) {
                mx = i;
            }
        }
        return mx;
    }

    /**
     * Calculate the minimum price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the minimum price
     */
    public static double findMinimumPrice(double[] stockPrices) {
        return stockPrices[min(stockPrices)];
    }

    /**
     * Calculate the maximum price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the maximum price
     */
    public static double findMaximumPrice(double[] stockPrices) {
        return stockPrices[max(stockPrices)];
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

    /**
     * Display the list of stock prices comma separated.
     * 
     * @param stockPrices The stock prices.
     */
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

    /**
     * Display an histogram of the stock prices.
     * 
     * @param stockPrices The stock prices.
     * @param min         The minimal value in the histogram.
     * @param max         The maximal value in the histogram.
     */
    public static void displayHistogram(double[] stockPrices, double min, double max) {
        System.out.println("\n--- Stock Prices Histogram ---\n");
        double step = (max - min) / 50;
        for (int i = 0; i < stockPrices.length; i++) {
            if (i == 0) {
                System.out.printf("Day | $%5.2f", min);
                displayChar(' ', 44);
                System.out.printf("| $%5.2f\n", max);
                displayChar('─', 65);
                System.out.println();
            }
            System.out.printf("%3d |", i + 1);
            displayChar('▓', (int) Math.ceil((stockPrices[i] - min + step) / step));
            System.out.printf(" $%5.2f\n", stockPrices[i]);
        }
    }

    /**
     * Display a bar chart of the stock prices.
     * 
     * @param stockPrices The stock prices.
     */
    public static void displayBarChart(double[] stockPrices) {
        double minPr = findMinimumPrice(stockPrices);
        double maxPr = findMaximumPrice(stockPrices);
        double[] bins = createBins(minPr, maxPr, 5);
        int[] freq = calculateOccurences(stockPrices, bins);
        int maxFr = freq[max(freq)];
        System.out.println("\n--- Stock Prices Bar Chart ---\n");
        for (int i = 0; i < bins.length - 1; i++) {
            System.out.printf("[%5.2f, %5.2f] | ", bins[i], bins[i + 1]);
            displayChar('▓', freq[i] * 30 / maxFr);
            System.out.printf(" %d\n", freq[i]);
        }
    }

    /**
     * Display a summary of the stock prices.
     * 
     * @param stockPrices The stock prices.
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
     * Generate count random stock prices in the specified interval.
     * 
     * @param count The number of prices values to generate.
     * @param min   The minimal price included.
     * @param max   The maximal price included.
     * @return count random prices.
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
     * Divide the [min, max] interval into binsCount bins.
     * 
     * @param min       The minimal value
     * @param max       The maximal value
     * @param binsCount The number of bins.
     * @return An array of key values of the bin.
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

    /**
     * Calculate the frequencies of values in each bin.
     * 
     * @param stockPrices The stock prices
     * @param bins        The stock prices bins.
     * @return frequencies of each bin.
     */
    public static int[] calculateOccurences(double[] stockPrices, double[] bins) {
        int[] freq = new int[bins.length - 1];
        for (int i = 0; i < stockPrices.length; i++) {
            int idx = indexOf(bins, stockPrices[i]);
            freq[idx]++;
        }
        return freq;
    }

    /**
     * Calculate in which interval the value is comprised.
     * 
     * @param arr   The arr to search in.
     * @param value The value to search for.
     * @return The index of the bin containing the value.
     */
    public static int indexOf(double[] arr, double value) {
        for (int i = arr.length - 2; i >= 0; i--) {
            if (value >= arr[i]) {
                return i;
            }
        }
        return 0;
    }

    private static void displayChar(char c, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
    }
}
