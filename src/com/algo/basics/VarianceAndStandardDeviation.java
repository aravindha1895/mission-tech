package com.algo.basics;

/**
 * Print the variance and standard deviation of the given 2D matrix.
 * Adjust the values of variance and standard deviation to four decimal places
 * Example:
 * Input: {{1,2,3},
 * {4,5,6},
 * {7,8,9}}
 * Output:
 * Variance: 6.6667
 * Standard deviation: 2.582
 * Time complexity O(N^M).
 * Space complexity: O(1)
 */
public class VarianceAndStandardDeviation {
    private static void varianceAndStandardDeviation(int arr[][]) {
        // Variance formula = (summation of (mean-element)^2)/total no of elements
        int n = arr.length;
        int m = arr[0].length;
        int sum = 0;
        double mean;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                sum += arr[i][j];
        mean = (double) sum / (n * m); // Calculate mean of all elements in  matrix
        double variance = 0.0;
        // Calculate summation of (mean-element)^2 for all elements
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                variance += (mean - (double) arr[i][j]) * (mean - (double) arr[i][j]);
        variance = variance / (n * m); // Divide by total no of elements
        System.out.printf("%.4f", variance);
        System.out.println();
        System.out.printf("%.4f", Math.sqrt(variance)); // Variance = Sqrt(SD)
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        varianceAndStandardDeviation(new int[][]{{1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}});
        varianceAndStandardDeviation(new int[][]{{1, 2,3},
                {4,5,6},
                {7,8,9}});
        varianceAndStandardDeviation(new int[][]{{1, 2,3},
                {4,5,6},
                {6,6,6}});
    }
}
