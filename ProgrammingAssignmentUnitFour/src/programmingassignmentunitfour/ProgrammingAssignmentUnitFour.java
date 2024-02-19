package programmingassignmentunitfour;

import java.util.ArrayList;
import java.util.Arrays;
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
        // Using US Locale to display properly the decimal char as 12.2
        // not 12,2 because my operating system is French.
        Locale.setDefault(Locale.US);

        testSet1();
        testSet2();
        testSet3();
    }

    public static void testSet1() {
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
    }

    public static void testSet2() {
        double[] stockPrices = generateRandomStock(10,
                10, 40, 1);
        ArrayList<Double> arrStockPrices = copyArray(stockPrices);
        ArrayList<Double> cumStockPrices = computeCumulativeSum(
                arrStockPrices);
        displayCumulativeSum(arrStockPrices, cumStockPrices);
    }

    public static void testSet3() {
        double[] stockPrices = generateRandomStock(105,
                10, 20, 0);
        displayStocks(stockPrices);

        ArrayList<Double> arrStock = copyArray(stockPrices);
        ArrayList<Double> dist = distinctValues(arrStock);
        sortItems(dist);
        ArrayList<Integer> freq = itemsFrequency(dist,
                stockPrices);
        displayOccurences(dist, freq);
    }

    /**
     * Calculate the average price of the stocks.
     *
     * @param stockPrices An array of stock prices.
     * @return the average price
     */
    public static double calculateAveragePrice(double[] stockPrices) {
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
    public static ArrayList<Double> computeCumulativeSum(
            ArrayList<Double> stockPrices) {
        ArrayList<Double> cumSum = new ArrayList<>();
        double cs = 0.0;
        for (int i = 0; i < stockPrices.size(); i++) {
            cs += stockPrices.get(i);
            cumSum.add(cs);
        }
        return cumSum;
    }

    /**
     * Find the index of the minimal value in the array.
     * 
     * @param arr an array of double.
     * @return The minimum index.
     */
    public static int min(double[] arr) {
        int mn = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    /**
     * Find the index of the minimal value in the array.
     * 
     * @param arr an array of int.
     * @return The minimum index.
     */
    public static int min(int[] arr) {
        int mn = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[mn]) {
                mn = i;
            }
        }
        return mn;
    }

    /**
     * Find the index of the maximal value in the array.
     * 
     * @param arr an array of double.
     * @return The maximum index.
     */
    public static int max(double[] arr) {
        int mx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[mx]) {
                mx = i;
            }
        }
        return mx;
    }

    /**
     * Find the index of the maximal value in the array.
     * 
     * @param arr an array of int.
     * @return The maximum index.
     */
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
        System.out.printf("Values for %d days.\n", stockPrices.length);
        for (int i = 0; i < stockPrices.length; i++) {
            if (i % 10 == 0) {
                if (i > 0)
                    System.out.println();
                int end = i + 10;
                if (end > stockPrices.length)
                    end = stockPrices.length;
                System.out.printf("Days %3d to %3d: ", i + 1, end);
            }
            if (i % 10 > 0) {
                System.out.print(", ");
            }
            System.out.print("$" + stockPrices[i]);
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

    /**
     * Display the cumulative sum of stock prices.
     * 
     * @param stocks The stock prices.
     * @param cumSum The cumulative sum of stocks.
     */
    public static void displayCumulativeSum(ArrayList<Double> stocks,
            ArrayList<Double> cumSum) {
        System.out.println("\n--- Stock Prices Cumulative Sum ---\n");
        System.out.println("Price       Cum. Sum");
        System.out.println(duplicateChar('─', 20));
        String format = "%5.2f      %"
                + ((int) Math.log10(cumSum.get(cumSum.size() - 1)) + 4)
                + ".2f\n";
        for (int i = 0; i < stocks.size(); i++) {
            System.out.printf(format, stocks.get(i), cumSum.get(i));
        }
    }

    /**
     * Display each price frequency.
     * 
     * @param values The stock prices.
     * @param freqs  The stock prices frequencies.
     */
    public static void displayOccurences(ArrayList<Double> values, ArrayList<Integer> freqs) {
        System.out.println("\n--- Number of Occurences ---\n");
        System.out.println("Price      Frequency");
        System.out.println(duplicateChar('─', 20));
        for (int i = 0; i < values.size(); i++) {
            System.out.printf("%5.0f      %9d\n",
                    values.get(i), freqs.get(i));
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
        for (int i = 0; i < count; i++) {
            items[i] = randDouble(min, max, precision);
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

    /**
     * Find distint values in an ArrayList.
     * 
     * @param arr input values
     * @return An ArrayList with distinct values
     */
    public static ArrayList<Double> distinctValues(ArrayList<Double> arr) {
        ArrayList<Double> dist = new ArrayList<>();
        for (double val : arr) {
            if (dist.indexOf(val) == -1) {
                dist.add(val);
            }
        }
        return dist;
    }

    /**
     * Compute items frequencies.
     * 
     * @param items       The items we want to count.
     * @param stockPrices The stock prices.
     * @return An ArrayList containing each item frequency.
     */
    public static ArrayList<Integer> itemsFrequency(ArrayList<Double> items, double[] stockPrices) {
        ArrayList<Integer> freq = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Double val = items.get(i);
            freq.add(countOccurences(stockPrices, val));
        }
        return freq;
    }

    /**
     * Sort items in ascending order using insertion sort algoritm.
     * 
     * @param items Items to be sorted.
     */
    public static void sortItems(ArrayList<Double> items) {
        for (int i = 1; i < items.size(); i++) {
            double t = items.get(i);
            int j = i - 1;
            while (j >= 0 && t < items.get(j)) {
                items.set(j + 1, items.get(j));
                j--;
            }
            items.set(j + 1, t);
        }
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

    /**
     * Generate a random float.
     * 
     * @param min       minimum value.
     * @param max       maximum value.
     * @param precision number of digits.
     * @return a random number in [min, max] interval.
     */
    private static double randDouble(double min, double max, int precision) {
        // 10.00 - 11.00 ==> 1000 - 1100
        double prec = Math.pow(10, precision);
        double interval = (max - min) * prec + 1;
        return (Math.floor(Math.random() * interval)) / prec + min;
    }
}
