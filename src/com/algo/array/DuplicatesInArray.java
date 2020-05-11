package com.algo.array;

/**
 * Given an array that has values from 0 to n-1 where n is te length of the array,
 * find duplicates without using extra space.
 * Approach:
 * As array only as values from 0 to n-1, use values as index, and add k(Size of the array) to it if we visit that index.
 * had we visited same index more than 2 times, that index would have had more than (k+k) added to that. If that is the case, it
 * is duplicate.
 * Time complexity O(N)
 * Space complexity: O(1)
 */
public class DuplicatesInArray {
    private static void printDuplicatesInArray(int a[]) {
        int k = a.length;
        for (int i = 0; i < k; i++) {
            a[a[i] % k] = a[a[i] % k] + k;
        }
        boolean isDuplicateFound = false;
        for (int i = 0; i < k; i++) {
            if (a[i] > (2 * k - 1)) {
                System.out.print(i + " ");
                isDuplicateFound = true;
            }
        }
        if (!isDuplicateFound) {
            System.out.println("No Duplicates");
        } else {
            System.out.println();
        }

    }

    public static void main(String[] args) {
        printDuplicatesInArray(new int[]{0, 4, 3, 7, 2, 8, 2, 3, 1});
        printDuplicatesInArray(new int[]{0, 1, 2, 3, 4, 5});
    }
}
