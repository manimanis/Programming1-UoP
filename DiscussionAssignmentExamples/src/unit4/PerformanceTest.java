package unit4;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author manianis
 */
public class PerformanceTest {

    public static void main(String[] args) {
        ArrayList<Integer> arrList = generateRandomNumbers(10);
        int[] arr = toArray(arrList);
        long perm;

        System.out.println("ArrayList\nBefore sorting:\n" + arrList);
        perm = bubbleSort(arrList);
        System.out.println(perm + " permutations.");
        System.out.println("After sorting:\n" + arrList);

        System.out.println("\nArray\nBefore sorting:");
        displayArray(arr);
        perm = bubbleSort(arr);
        System.out.println(perm + " permutations.");
        System.out.println("After sorting:");
        displayArray(arr);

        int n = 100;
        for (int i = 0; i < 4; i++) {
            arrList = generateRandomNumbers(n);
            arr = toArray(arrList);

            long startTime, endTime, elapsedTime;

            System.out.println("--- Sorting " + n + " integers ---");

            // Array sorting
            startTime = System.currentTimeMillis();
            perm = bubbleSort(arr);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Array: " + elapsedTime + "ms in " + perm + " permutations");

            // ArraList sorting
            startTime = System.currentTimeMillis();
            perm = bubbleSort(arrList);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("ArrayList: " + elapsedTime + "ms in " + perm + " permutations");

            n *= 10;
        }
    }

    public static ArrayList<Integer> generateRandomNumbers(int count) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            arr.add(rand.nextInt(100, 99999));
        }
        return arr;
    }

    public static int[] toArray(ArrayList<Integer> arrList) {
        int[] arr = new int[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            arr[i] = arrList.get(i);
        }
        return arr;
    }

    public static long bubbleSort(ArrayList<Integer> arr) {
        boolean flag;
        int n = arr.size();
        long perm = 0l;
        do {
            flag = true;
            for (int j = 1; j < n; j++) {
                if (arr.get(j) < arr.get(j - 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j - 1));
                    arr.set(j - 1, temp);
                    flag = false;
                    perm++;
                }
            }
            n--;
        } while (!flag);
        return perm;
    }

    public static long bubbleSort(int[] arr) {
        boolean flag;
        int n = arr.length;
        long perm = 0l;
        do {
            flag = true;
            for (int j = 1; j < n; j++) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = false;
                    perm++;
                }
            }
            n--;
        } while (!flag);
        return perm;
    }

    public static void displayArray(int[] arr) {
        System.out.print('[');
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println(']');
    }
}
