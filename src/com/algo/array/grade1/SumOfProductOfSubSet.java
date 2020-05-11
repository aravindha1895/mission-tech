package com.algo.array.grade1;

/**
 * You are given an array of non-negative integers. You need to find the sum of the product of
 * elements of all possible subsets.
 * Example:
 * arr[] = {3, 5, 1, 6}
 * Output: 335
 * (3) + (5) + (1) + (6) + (3*5) + (3*1) + (3*6) +  (5*1) + (5*6) + (1*6) + (3*5*1) + (3*5*6) +
 * (3*1*6) + (5*1*6) + (3*5*1*6)
 * The simple implementation will be generating all possible subsets and finding the product of elements
 * of each subset and adding that to the final sum. But time complexity is O(2^N)
 * Below is solution to solve it in liner time
 * Mathematical formula: sum of prod of all subset = ((1+a)(1+b)(1+c)...)-1
 * Time complexity O(N).
 * Space complexity: O(1)
 */
public class SumOfProductOfSubSet {
    private static void sumOfProductOfSubsets(int arr[]) {
        int n = arr.length;
        // Mathematical formula: sum of prod of all subset = ((1+a)(1+b)(1+c)...)-1
        int sumOfProduct = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) { // There should be only positive numbers in subset
                System.out.println("Invalid input");
                return;
            }
            sumOfProduct *= (1 + arr[i]); // ((1+a)(1+b)(1+c)...)
        }
        System.out.println(sumOfProduct - 1); // Subtract 1 as per formula
    }

    public static void main(String[] args) {
        sumOfProductOfSubsets(new int[]{13, 11, 12, 10, 8, 9, 6, 7, 4, 5, 2, 3, 1});
        sumOfProductOfSubsets(new int[]{3, 5, 7, 2, 6, 10, 14, 15, -20, 19});
        sumOfProductOfSubsets(new int[]{3, 5, 1, 6});
        sumOfProductOfSubsets(new int[]{0});
    }
}
