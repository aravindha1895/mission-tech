package com.algo.dynamicprogramming.grade4;
// TODO: 2D array solution that is easier to visualize, comments for recursive method
/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * For example, if length of the rod is 8 and the values of different pieces are given as following,
 * then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 * <p>
 * <p>
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 * Video solution: https://www.youtube.com/watch?v=IRwVmTmN6go&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=10
 *
 * This solution can be easily viewed in form of 2D array (matrix), but space optimised 1D array solution is
 * given below in maxValue method. 2D matrix solution as O(n*n) space complexity
 *
 * Time complexity: o(n*n)
 * Space complexity (OF DP method discussed here): O(n)
 */
public class CuttingRodDP {
    public int maxValue(int price[]) {
        int max[] = new int[price.length + 1];
        // i represents length of cuts available ex. if i=1, we can cut the rod only by length 1
        for (int i = 1; i <= price.length; i++) {
            // j represents the length of rod given for each i we go from length =i to length of rod
            // We start from j=i because for j<i, we cannot cut rod by i length, so we preserve prev value
            for (int j = i; j <= price.length; j++) {
                max[j] = Math.max(max[j], max[j - i] + price[i - 1]);
            }
        }
        return max[price.length];
    }

    public int maxValue1(int price[]) {
        int max[] = new int[price.length + 1];
        for (int i = 1; i <= price.length; i++) {
            max[i] = price[i - 1];
        }
        for (int i = 1; i <= price.length; i++) {
            for (int j = 1; j < i; j++) {
                max[i] = Math.max(max[i], max[i - j] + max[j]);
            }
        }
        return max[price.length];
    }

    public int recursiveMaxValue(int price[], int len) {
        if (len <= 0) {
            return 0;
        }
        int maxValue = 0;
        for (int i = 0; i < len; i++) {
            int val = price[i] + recursiveMaxValue(price, len - i - 1);
            if (maxValue < val) {
                maxValue = val;
            }
        }
        return maxValue;
    }

    public static void main(String args[]) {
        CuttingRodDP cr = new CuttingRodDP();
        int[] price = {3, 5, 8, 9, 10, 20, 22, 25};
        int r = cr.recursiveMaxValue(price, 8);
        System.out.println("Using recursion, max profit = "+r);
        r = cr.maxValue(price);
        System.out.println("Using DP method 1, max profit = "+r);
        r = cr.maxValue1(price);
        System.out.println("Using DP method 2, max profit = "+r);
    }
}
