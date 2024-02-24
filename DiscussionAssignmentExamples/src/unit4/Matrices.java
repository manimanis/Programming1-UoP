package unit4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author manianis
 */
public class Matrices {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
        mat.add(new ArrayList<>(Arrays.asList(23, 14, 25)));
        mat.add(new ArrayList<>(Arrays.asList(3, 8, 9)));
        mat.add(new ArrayList<>(Arrays.asList(89, 23, 100)));
        
        int sum = 0;
        for (ArrayList<Integer> arr : mat) {
            for (int val : arr) {
                sum += val;
            }
        }
        double avg = sum / (mat.size() * mat.get(0).size());
        System.out.println("Average: " + avg);
    }
}
