package com.algo.stack.grade2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
 * Examples :
 * <p>
 * Input: arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, K = 3
 * Output: 3 3 4 5 5 5 6
 * Input: arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, K = 4
 * Output: 10 10 10 15 15 90 90
 * <p>
 * Hint: Sliding window approach using dequeue
 * Explanation: https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class MaxSubArrayOfSizeK {
    static void addToQueue(Deque<Integer> dequeue, int[] arr, int ele, int i) {
        // Remove all elements lesser than this element
        while (!dequeue.isEmpty() && arr[dequeue.peekLast()] < ele)
            dequeue.removeLast();
        // Add current element at the rear of Queue
        dequeue.addLast(i);
    }

    static void max(int arr[], int g) {
        int n = arr.length;
        Deque<Integer> dequeue = new ArrayDeque<Integer>();
        for (int i = 0; i < g; i++) {
            addToQueue(dequeue, arr, arr[i], i);
        }
        for (int i = g; i < n; i++) {
            /* The element at the front of the queue is the largest element of
             previous window, so print it */
            System.out.print(arr[dequeue.peekFirst()] + " ");
            // Remove all elements that are out of this window
            while (!dequeue.isEmpty() && dequeue.peekFirst() <= (i - g))
                dequeue.removeFirst();
            addToQueue(dequeue, arr, arr[i], i);
        }
        System.out.println(arr[dequeue.peekFirst()]);// For the last set
    }

    public static void main(String[] args) {
        max(new int[]{-1, 2, 4, -3, -2, -1, 8, 1, 3, 7}, 4);
        max(new int[]{2, 5, 7, 1, 9, 3, 6, 8}, 3);
        max(new int[]{8, 8, 8, 7, 9, 9}, 3);
    }
}
