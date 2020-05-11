package com.algo.array.grade3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * You will be given an array of integers of size n. You need to find out k unique subarrays which have
 * the maximum sum of its elements where k is the given integer. Subarrays may be overlapping.
 * You need to print the sum of the elements of those k subarrays.
 * Example:
 * arr[] = {4, -8, 9, -4, 1, -8, -1, 6}
 * k = 4
 * Output: 9 6 6 5
 * {9}, {9,-4,1}, {6}, {4, -8,9}
 * Time complexity O(N*K).
 * Space complexity: O(N)+O(K)=O(N)
 */
public class KMaxSumsSubArray {
    public void kMaxSumsOfSUBArrays(int a[], int k) {
        int size = a.length;
        List<Integer> res = new LinkedList<>();
        // To maintain next set of possible candidates to contribute to max
        int[] candidate = new int[k];
        // K min prefix. This algo is extension of max sum subarray using prefix sum
        List<Integer> min = new ArrayList<Integer>();
        min.add(0);
        for (int i = 1; i < k; i++) {
            min.add(Integer.MAX_VALUE);
        }
        // Maintains the result ok K max sums
        List<Integer> max = new ArrayList<Integer>();
        int prefix[] = new int[size];
        prefix[0] = a[0];
        // Calculate prefix sum
        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] + a[i];
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < k; j++) {
                /* Wen i=0 we have only one element and it is first element, so max no. of sum
                 * possible is 1. Once we move sufficient i, it always goes to else part. */
                if (min.get(j) == Integer.MAX_VALUE) {
                    candidate[j] = Integer.MIN_VALUE;
                } else {
                    candidate[j] = prefix[i] - min.get(j);
                }
            }
            /* Update min prefix sum so far */
            updateMin(min, prefix[i]);
            updateMax(max, candidate);
        }
        for (Integer n : max) {
            System.out.print(n + " ");
            k--;
            // We need only K max sums
            if (k == 0) break;
        }
        System.out.println();
    }

    public void updateMin(List<Integer> min, int prefix) {
        min.add(prefix); // Add current prefix sum
        Collections.sort(min); // Sort in increasing order as Min prefix to subtract contributes to Max value
    }

    public void updateMax(List<Integer> max, int[] cand) {
        for (int i = 0; i < cand.length; i++)
            if (cand[i] != Integer.MIN_VALUE)
                max.add(cand[i]);
        /* Add candidates and sort in decreasing order*/
        Collections.sort(max, Collections.reverseOrder());
    }

    public static void main(String[] args) {
        KMaxSumsSubArray kMaxSumsSubArray = new KMaxSumsSubArray();
      /*  kMaxSumsSubArray.kMaxSumsOfSUBArrays(new int[]{3, -12, 4, 5, 8, 9, -3, 5, -8, 11, 13, -25, 9, -14, 15}, 12);
        kMaxSumsSubArray.kMaxSumsOfSUBArrays(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}, 4);*/
        kMaxSumsSubArray.kMaxSumsOfSUBArrays(new int[]{4, -8, 9, -4, 1, -8, -1, 6}, 5);
    }
}
