package unit4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import org.openjdk.jol.vm.VM;

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

        // benchmark1();
        // benchmark2();
        benchmark3();
    }

    public static void benchmark1() {
        ArrayList<Integer> arrList;
        int[] arr;
        long perm;

        int n = 100;
        for (int i = 0; i < 4; i++) {
            // Generate a random ArrayList of size n
            arrList = generateRandomNumbers(n);
            // copy the same data to an array
            arr = toArray(arrList);

            long startTime, endTime, elapsedTime1, elapsedTime2;

            System.out.println("--- Sorting " + n + " integers ---");

            // Time Array sorting
            startTime = System.currentTimeMillis();
            perm = bubbleSort(arr);
            endTime = System.currentTimeMillis();
            elapsedTime1 = endTime - startTime;
            System.out.println("Array: " + elapsedTime1 + "ms in "
                    + perm + " permutations");

            // Time ArraList sorting
            startTime = System.currentTimeMillis();
            perm = bubbleSort(arrList);
            endTime = System.currentTimeMillis();
            elapsedTime2 = endTime - startTime;
            System.out.println("ArrayList: " + elapsedTime2 + "ms in "
                    + perm + " permutations");

            // calculate how faster is array compared to ArrayList
            double tf = elapsedTime2 * 1.0 / elapsedTime1;
            System.out.printf("Array is %5.2f times "
                    + "faster than ArrayList.\n\n", tf);
            n *= 10;
        }
    }

    public static void benchmark2() {
        ArrayList<Integer> arrList = generateRandomNumbers(100000);
        int[] arr = toArray(arrList);

        System.out.println("Sizeof ArrayList: " + VM.current().sizeOf(arrList));
        System.out.println("Sizeof array: " + VM.current().sizeOf(arr));
    }

    public static ArrayList<Integer> generateRandomNumbers(int count, int min, int max) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            arr.add(rand.nextInt(min, max));
        }
        return arr;
    }

    public static ArrayList<Integer> generateRandomNumbers(int count) {
        return generateRandomNumbers(count, 100, 99999);
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

    public static void benchmark3() {
        ArrayList<Integer> arrList = generateRandomNumbers(
                15, 10, 100);

        // Printing
        System.out.println("\nContent:\n" + arrList);

        // inserting
        arrList.add(17000);
        System.out.println("\nAfter instering\n" + arrList);

        // removing
        arrList.remove((Integer) 17000);
        System.out.println("\nAfter removing:\n" + arrList);

        // Searching
        System.out.println("\nSearching data");
        int value, pos;
        value = arrList.get(14); // Accessing
        pos = arrList.indexOf(value); // Find first occurence
        System.out.println(value + " found at position " + pos);

        int newVal = (value % 10) * 10 + value / 10;
        arrList.set(pos, newVal); // Mutating

        System.out.println("\nSorting even number precede odds");
        // sorting
        arrList.sort((Integer o1, Integer o2) -> (o1 % 2) - (o2 % 2));
        System.out.println(arrList);

        System.out.println("\nDispaying digits sum > 10");
        // iterating over arraylist
        for (int val : arrList) {
            int s = val / 10 + val % 10;
            if (s > 10) {
                System.out.print(val + ", ");
            }
        }
        System.out.println();
    }

}

class Stack<T> {
    ArrayList<T> st = new ArrayList<>();

    public Stack() {
    }
    
    public void push(T elem) {
        st.add(elem);
    }
    
    public boolean isEmpty() {
        return st.isEmpty();
    }
    
    public T pop() {
        return st.remove(st.size() - 1);
    }
};

class ArrStack<T extends Object> {
    T[] st;
    int count;

    @SuppressWarnings("unchecked")
    public ArrStack() {
        count = 0;
        st = (T[]) new Object[10];
    }
    
    public void push(T elem) {
        if (count >= st.length) {
            @SuppressWarnings("unchecked")
            T[] stc = (T[]) new Object[st.length + 10];
            for (int i = 0; i < st.length; i++) stc[i] = st[i];
            st = stc;
        }
        st[count++] = elem;
    }
    
    public boolean isEmpty() {
        return count != 0;
    }
    
    public T pop() {
        if (count > 0) {
            T elem = st[count - 1];
            count--;
            return elem;
        }
        return null;
    }
};