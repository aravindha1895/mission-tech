package com.algo.basics;

/**
 * Pascal’s triangle is a triangular array of the binomial coefficients.
 * Write a function that takes an integer value n as input and prints first n lines of the Pascal’s triangle.
 * Following are the first 6 rows of Pascal’s Triangle.
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * 1 5 10 10 5 1
 * Approach:
 * Value at every entry is the sum of the above two entries in the previous line except for first and last entries in each line.
 * If only last line is needed, we can optimise the below method to use O(n)
 * extra space because you need values of only the previous line.
 * Declare the two arrays of size n and store the values of the current line in the first array.
 * Then, compute the values of the next line using the values in the first array and store it in the second array.
 * After that, overwrite the values of the first array with the values of the second array.
 * Time complexity O(N^2).
 * Space complexity: O(N^2)
 */
public class PascalTriangle {
    private static void printPascalTriangle(int n) {
        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // First and last entries of a row is 1
                if (j == 0 || j == i)
                    arr[i][j] = 1;
                else // sum of the above two entries in the previous line
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
            }
        }

        /* Loop to print triangle */
        for (int i = 0; i < n; i++) {
            // Just to print spaces
            for (int k = i; k < n - 1; k++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printPascalTriangle(2);
        printPascalTriangle(5);
        printPascalTriangle(7);
        printPascalTriangle(20);
        printPascalTriangle(10);
    }
}
