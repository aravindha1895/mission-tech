package com.algo.basics;

/**
 * You are given an array of size n. You need to count the number of increasing sequences of
 * length k using recursion. Note that the subsequence should maintain the order and should go from
 * left to right.
 * Example:
 * arr[] = {1, 3, 2, 4, 5}
 * k = 3
 * Output: 7
 * Explanation:
 * 1 3 4
 * 1 3 5
 * 1 2 4
 * 1 2 5
 * 1 4 5
 * 3 4 5
 * 2 4 5
 * Note: If arr[] = {1, 3, 2, 5, 5} then 1, 3, 5 should be counted twice because you are asked to
 * find all possible increasing subsequences, not unique increasing subsequences.
 * Time complexity O(2^N)
 * Space complexity: O(N)
 */
public class IncreasingSubSequence {
    private static int count = 0;

    private static void numberOfIncreasingSubSequences(int[] arr, int k) {
        int n = arr.length;
        /* CHeck for subsequence starting from from each index */
        // If i need LIS of size k, we need to have starting point as at least n-k
        for (int i = 0; i < n - k + 1; i++) {
            printSubsequence(arr, i, new int[k], 0, k);
        }
    }

    private static boolean printSubsequence(int[] arr, int i, int result[], int j, int k) {
        if (i >= arr.length || k < 0) return false; // We have breached the bounds
        // compare current ele with the last ele of sub sequence formed so far
        if ((j == 0) || (result[j - 1] < arr[i])) {
            result[j] = arr[i];
            j++;
            k--;
        } else
            /* This number cannot be part of current subsequence, so discard this recursion */
            return false;
        if (k == 0) { // LIS of size k found
            /*If you need / dont need actual pairs to be printed, comment/ uncomment below function call*/
            printResult(result);
            count++;
            return true;
        }
        boolean res = false;
        int n;
        for (n = i + 1; n < arr.length; n++) {
            res = printSubsequence(arr, n, result, j, k);
            if (res) {
                /* Erase the last number from pair formed, so that subsequent LIS comparison
                dont get affected.
                Note: Array is passed as reference hence this step needed.
                K is passed as value hence k-- not needed.*/
                result[j] = 0;
            }
        }
        // Returning true of false does not matter here
        return res;
    }

    private static void printResult(int result[]) {
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Test case 1");
        numberOfIncreasingSubSequences(new int[]{6, 5, 4, 3, 2, 1}, 4);
        System.out.println("No of LIS = " + count);
        count = 0;// Reset count for next test case

        System.out.println("Test case 2");
        numberOfIncreasingSubSequences(new int[]{3, 2, 4, 4, 5, 4, 4}, 3);
        System.out.println("No of LIS = " + count);
        count = 0;// Reset count for next test case

        System.out.println("Test case 3");
        numberOfIncreasingSubSequences(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        System.out.println("No of LIS = " + count);
        count = 0;// Reset count for next test case

        System.out.println("Test case 4");
        numberOfIncreasingSubSequences(new int[]{1, 3, 2, 4, 5}, 3);
        System.out.println("No of LIS = " + count);
        count = 0;// Reset count for next test case


    }
}
