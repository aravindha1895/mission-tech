package com.algo.array.grade3;
/**
 * You are given an array of integers of size n. The array contains integers of range 1 to n.
 * Some integers may be repeated and some may be missing, find both the missing and duplicate integers.
 * Example:
 * Input:
 * arr[] = {1, 3, 4, 6, 7, 9, 10, 3, 6, 3}
 * Output:
 * Duplicate integers: 3, 6
 * Missing integers: 2, 5, 8
 * Code the above problem in O(n) using O(1) auxiliary space.
 */

import java.util.HashSet;
import java.util.Set;

public class FindDuplicatesAndMissingElements {
    private static void duplicateAndMissingIntegers(int arr[]) {
        int n = arr.length;
        Set<Integer> dup = new HashSet<Integer>();
        Set<Integer> missing = new HashSet<Integer>();
        /* We are given a condition -> The array contains integers of range 1 to n.
         * So we can operate with data as index safely. That concept is used here
         * */
        for (int i = 0; i < n; i++) {
            int currIndex = Math.abs(arr[i]);
            /* Unvisited. Make te value negative */
            if (arr[currIndex - 1] > 0)
                arr[currIndex - 1] = -arr[currIndex - 1];
            else // Already visited, hence it is a duplicate
                dup.add(Math.abs(arr[i]));
        }
        /* After the above loop, if arr[i] still remain +ve, then this index+1 is missing in list of values */
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0)
                missing.add(i + 1);
        }
        if (dup.size() == 0) {
            System.out.println("There are no duplicate integers");
            System.out.println("There are no missing integers");
            return;
        }
        for (Integer num : dup)
            System.out.print(num + " ");
        System.out.println();
        for (Integer num : missing)
            System.out.print(num + " ");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        duplicateAndMissingIntegers(new int[]{1, 3, 4, 6, 7, 9, 10, 6, 3, 6});
        duplicateAndMissingIntegers(new int[]{9, 9, 9, 9, 9, 9, 9, 7, 8, 9, 9});
        duplicateAndMissingIntegers(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
    }
}
