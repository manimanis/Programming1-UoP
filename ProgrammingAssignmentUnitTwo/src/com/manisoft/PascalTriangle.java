
package com.manisoft;

/**
 *
 * @author manianis
 */
public class PascalTriangle {
    public static void main(String[] args) {
        short n = 13;
        byte[][] matrix = pascalTriangle(n);
        displayTriangle(n, matrix);
        short[] pos = errorPosition(n, matrix);
        System.out.printf("Error at %d, %d: %d\n", pos[0], pos[1]
                , matrix[pos[0]][pos[1]]);
    }
    
    public static byte[][] pascalTriangle(short n) {
        byte[][] matrix = new byte[n][n];
        matrix[0][0] = 1;
        for (short l = 1; l < n; l++) {
            matrix[l][0] = 1;
            for (short c = 1; c < l; c++) {
                matrix[l][c] = (byte) (matrix[l-1][c] + matrix[l-1][c-1]);
            }
        }
        return matrix;
    }
    
    public static void displayTriangle(short n, byte[][] matrix) {
        for (short l = 0; l < n; l++) {
            System.out.printf("Line %2d: ", l);
            for (short c = 0; c < l; c++) {
                System.out.printf("%4d", matrix[l][c]);
            }
            System.out.println();
        }
    }
    
    public static short[] errorPosition(short n, byte[][] matrix) {
        short[] position = new short[]{-1, -1};
        outer:
        for (short l = 0; l < n; l++) {
            for (short c = 0; c < l; c++) {
                if (matrix[l][c] < 0) {
                    position[0] = l;
                    position[1] = c;
                    break outer;
                }
            }
        }
        return position;
    }
}
