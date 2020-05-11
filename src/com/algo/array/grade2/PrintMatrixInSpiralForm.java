package com.algo.array.grade2;

/**
 * You are given a 2D array and you need to print the elements in the spiral form.
 * Check the below example to understand the problem statement
 * Example:
 * arr[][] = {
 * {1, 2, 3, 4, 5},
 * {6, 7, 8, 9, 10},
 * {11, 12, 13, 14, 15},
 * {16, 17, 18, 19, 20}
 * }
 * Output: 1 2 3 4 5 10 15 20 19 18 17 16 11 6 7 8 9 14 13 12
 * Time complexity O(N*M).
 * Space complexity: O(1)
 */
public class PrintMatrixInSpiralForm {
    public static void main(String[] args) {
        printInSpiralForm(new int[][]{{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}});

        System.out.println();
        printInSpiralForm(new int[][]{{1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18}});


    }

    private static void printInSpiralForm(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int i = 0, j = 0;
        while (i < n && j < m) {
            // Left to right row
            for (int k = j; k < m; k++)
                System.out.print(arr[i][k] + " ");
            i++;
            // Top down column
            for (int k = i; k < n; k++)
                System.out.print(arr[k][m - 1] + " ");
            m--;
            /* Check exit conditions again because if any one exit condition has been met,
             * executing below 2 for loop either prints redundant data or throw out of bound
             *  */
            if (i < n && j < m) {
                // Right left row
                for (int k = m - 1; k >= j; k--)
                    System.out.print(arr[n - 1][k] + " ");
                n--;
            }
            if (i < n && j < m) {
                // Bottom up column
                for (int k = n - 1; k >= i; k--)
                    System.out.print(arr[k][j] + " ");
                j++;
            }
        }
    }
}
