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
        // Stock prices for 10 days in the period of Jan, 29 to Feb, 9
        double[] stockPrices = new double[] {
                58.32, 58.11, 58.60, 57.54, 56.92,
                56.24, 55.83, 56.74, 56.70, 56.88
        };
        double maxPr = findMaximumPrice(stockPrices);
        double minPr = findMinimumPrice(stockPrices);
        displayStocks(stockPrices);
        displaySummary(stockPrices);
        displayHistogram(stockPrices, minPr, maxPr);
        displayBarChart(stockPrices);

        // Random stock prices
        stockPrices = generateRandomStock(15, 10, 15, 2);
        maxPr = findMaximumPrice(stockPrices);
        minPr = findMinimumPrice(stockPrices);
        displayStocks(stockPrices);
        displaySummary(stockPrices);
        displayHistogram(stockPrices, minPr, maxPr);
        displayBarChart(stockPrices);

        stockPrices = generateRandomStock(50, 10, 11, 1);
        ArrayList<Double> arrStockPrices = copyArray(stockPrices);
        ArrayList<Double> cumStockPrices = computeCumulativeSum(arrStockPrices);
        displayCumulativeSum(arrStockPrices, cumStockPrices);

        ArrayList<Double> distValues = new ArrayList<>();
        ArrayList<Integer> valFreq = new ArrayList<>();
        for (Double val : arrStockPrices) {
            if (distValues.indexOf(val) == -1) {
                distValues.add(val);
            }
        }
        for (int i = 0; i < distValues.size(); i++) {
            Double val = distValues.get(i);
            valFreq.add(countOccurences(stockPrices, val));
            System.out.println(val + " " + valFreq.get(i));
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
     * Calculate the number of occurrences of the number targetPrice. The price
     * is searched in the interval +/-delta
     *
     * @param stockPrices An array of stock prices.
     * @param minPrice    minimum price
     * @param maxPrice    maximum price
     * @return the number of occurrence
     */
    public static int countOccurences(double[] stockPrices,
            double minPrice, double maxPrice) {
        int count = 0;
        for (int i = 0; i < stockPrices.length; i++) {
            if (minPrice <= stockPrices[i] && stockPrices[i] <= maxPrice) {
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
        return countOccurences(stockPrices, targetPrice, targetPrice);
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
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    public static int min(int[] arr) {
        int mn = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    public static int max(double[] arr) {
        int mx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[mx]) {
                mx = i;
            }
        }
        return mx;
    }

    public static int max(int[] arr) {
        int mx = 0;
        for (int i = 1; i < arr.length; i++) {
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
        for (int i = 0; i < stockPrices.length; i++) {
            sum += stockPrices[i] * stockPrices[i];
        }
        return Math.sqrt(sum / stockPrices.length - avg * avg);
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
                double axis = min;
                System.out.print("Day ");
                for (int j = 0; j <= 50; j += 10) {
                    System.out.printf("| $%5.2f  ", axis);
                    axis += step * 10;
                }
                System.out.println();
                System.out.print(duplicateChar('─', 65));
                System.out.println();
            }
            System.out.printf("%3d |", i + 1);
            System.out.print(duplicateChar('▓', (int) Math.ceil((stockPrices[i] - min) / step)));
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
            System.out.printf(duplicateChar('▓', freq[i] * 30 / maxFr));
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

    public static void displayCumulativeSum(ArrayList<Double> stocks,
            ArrayList<Double> cumSum) {
        System.out.println("\n--- Stock Prices Cumulative Sum ---\n");
        System.out.println("Price      Sum");
        System.out.println(duplicateChar('─', 20));
        String format = "%5.2f   %"
                + ((int) Math.log10(cumSum.get(cumSum.size() - 1)) + 4)
                + ".2f\n";
        for (int i = 0; i < stocks.size(); i++) {
            System.out.printf(format, stocks.get(i), cumSum.get(i));
        }
    }

    /**
     * Generate count random stock prices in the specified interval.
     * Numbers are rounded to two decimals.
     * 
     * @param count     The number of prices values to generate.
     * @param min       The minimal price included.
     * @param max       The maximal price included.
     * @param precision Number of decimals
     * @return count random prices.
     */
    public static double[] generateRandomStock(int count, double min, double max, int precision) {
        double[] items = new double[count];
        double nextDown = 1 - 1e-15;
        double decimals = Math.pow(10, precision);
        for (int i = 0; i < count; i++) {
            items[i] = Math.floor(((Math.random() / nextDown) * (max - min) + min) * decimals) / decimals;
        }
        return items;
    }

    /**
     * Convert a double array into an ArrayList<Double>
     * 
     * @param arr an array of doubles
     * @return an ArrayList<Double>
     */
    public static ArrayList<Double> copyArray(double[] arr) {
        ArrayList<Double> items = new ArrayList<>();
        for (double val : arr) {
            items.add(val);
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
        int d = 0, f = arr.length - 2;
        while (f >= d) {
            int mid = (d + f) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (value < arr[mid]) {
                f = mid - 1;
            } else {
                d = mid + 1;
            }
        }
        return f;
    }

    // public static int indexOf2(double[] arr, double value) {
    // for (int i = arr.length - 2; i >= 0; i--) {
    // if (value >= arr[i]) {
    // return i;
    // }
    // }
    // return 0;
    // }

    /**
     * Create a String from duplicating a character many times.
     * 
     * @param c     char to duplicate
     * @param count number of repetitions
     * @return a c character duplicated count times.
     */
    private static String duplicateChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
