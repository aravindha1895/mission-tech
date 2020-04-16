package com.algo.dynamicprogramming.grade4;
/**
 * Given an array of non negative integers where each element represents the max
 * number of steps that can be made forward from that element. Write a function to
 * return the minimum number of jumps to reach the end of the array from first element
 *
 * Solution
 * Have 2 for loop. j trails i. If arr[j] + j >= i then you calculate new jump
 * and result.
 *
 * Space complexity O(n) to maintain result and min jumps
 * Time complexity O(n^2)
 *
 * Reference
 * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 */
import java.util.Arrays;
import java.util.Stack;
import java.util.StringJoiner;

public class MinJumpsToReachEnd {
    public static void main(String[] args) {
        int factor[] = {2, 3, 1, 1, 2, 4, 2, 0, 1, 1};
        calculateMinJumpsToReachEnd(factor);
    }

    private static void calculateMinJumpsToReachEnd(int[] factor) {
        int n = factor.length;
        int T[] = new int[n];
        int parent[] = new int[n];
        Arrays.fill(T, Integer.MAX_VALUE);
        T[0] = 0;
        parent[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if we can jump to cell i from cell j by adding how much we can jump from  j
                if (j + factor[j] >= i) {
                    // If this j produces lower jump count
                    if (1 + T[j] < T[i]) {
                        T[i] = 1 + T[j];
                        parent[i] = j;
                    }
                }
            }
        }
        System.out.println("Dynamic programming jumps matrix");
        for (int i = 0; i < n; i++)
            System.out.print(T[i] + " ");
        System.out.println();
        System.out.println("Dynamic programming parent matrix");
        for (int i = 0; i < n; i++)
            System.out.print(parent[i] + " ");
        System.out.println("\nMin jumps to reach end = " + T[n - 1]);

        Stack<Integer> stack = new Stack<>();
        int i = n - 1;
        while (parent[i] != i) {
            stack.push(i);
            i = parent[i];
        }
        stack.push(0);
        StringJoiner joiner = new StringJoiner(" -> ");
        while(!stack.isEmpty())
            joiner.add(stack.pop().toString());
        System.out.println("Jump sequence starting from 0 th index");
        System.out.println(joiner.toString());

    }
}
