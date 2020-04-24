package com.algo.set;

import java.util.HashSet;
import java.util.Set;

/**
 * You will be given an array of integers, and you have to print the size of the
 * longest subsequence of the array such that:
 * All the elements of the subsequence are consecutive.
 * The consecutive numbers can be in any order.
 * These elements in the sequence need not be contiguous.
 * Example:
 * Input:
 * Array[] = {1, 9, 5, 2, 6, 7, 3, 8}
 * Output:
 * 5
 * Explanation:
 * The longest consecutive subsequence of the given array, according to the problem statement,
 * is {9, 5, 6, 7, 8}, and its size is 5. Therefore, the output is 5.
 * Approach:
 * array: It is the given array of elements.
 * hashSet: It is a HashSet to store all the elements of the array.
 * maxSize: By the end, it gives the size of the longest consecutive subsequence.
 * First, add all the elements of the array to the hashSet.
 * For every element of the array:
 * Check if the current element is the starting element of the consecutive subsequence using hashSet.
 * (If the currentElement is the starting element of the consecutive subsequence,
 * the integer one less than the currentElement, i.e., (currentElement-1) should not be present in the hashSet.)
 * If true, then get the size of the consecutive subsequence.
 * Update the maxSize to the maximum size of the subsequence that you have seen until now.
 * Print the maxSize.
 * Time complexity: O(N)
 * Space complexity: O(N)
 */
public class LongestConsecutiveSubSequence {
    public static void main(String[] args) {
        printLCS(new int[]{7, 6, 5, 4, 1, 2, 0});
        printLCS(new int[]{1, 2, 3, 4, 5, 6});
        printLCS(new int[]{1, 5, 2, 6, 7, 3, 8, 9});
    }

    private static void printLCS(int[] array) {
        int n = array.length;
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            set.add(array[i]);
        }
        int longest = 0;
        for (int i = 0; i < n; i++) {
            // Check if this element can be starting element (array[i]-1) should exist in set if this is starting ele.
            if (!set.contains(array[i] - 1)) {
                int ele = array[i];
                // Check how many consecutive elements can be found
                while (set.contains(ele))
                    ele++;
                // Update if this is longest LCS
                if (longest < ele - array[i])
                    longest = ele - array[i];
            }
        }
        System.out.println(longest);
    }
}
