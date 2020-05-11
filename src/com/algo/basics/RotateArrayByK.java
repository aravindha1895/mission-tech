package com.algo.basics;

/**
 * You are given an array of size n. You need to rotate the array left by d elements.
 * Note: If d is greater than n then you can rotate the array by d%n elements because rotating an array
 * with n elements for d times is equivalent to rotating for n+d times because after rotating for n times the original
 * array is obtained.
 * Example:
 * arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
 * d = 3
 * Output: {3, 4, 5, 6, 7, 8, 9, 0, 1, 2}
 * Solution: Copy first k element in temp array, shift remaining n-k elements and then copy k elements from temp to
 * original array at end.
 * Time complexity O((N-K)^2).
 * Space complexity: O(K)
 * Another simple solution is to rotate array without using extra space,
 * then time complexity will become O(n^2) and O(1) Aux space
 */
public class RotateArrayByK {
    private static void rotateArray(int[] arr, int d) {
        int n = arr.length;
        d = d % n; // If d>n we need to do only d%n rotations
        int[] temp = new int[d];
        // Copy first d elements in temp array
        for (int i = 0; i < d; i++)
            temp[i] = arr[i];
        int k = 0;
        // Shift remaining n-k elements
        for (int i = d; i < n; i++) {
            arr[k++] = arr[i];
        }
        // Copy temp arr to end to get the final rotated array
        for (int i = 0; i < d; i++)
            arr[k++] = temp[i];
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        rotateArray(new int[]{1, 2, 3, 4}, 2);
        rotateArray(new int[]{14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, 5);
        rotateArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 13);
        rotateArray(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 3);
    }
}
