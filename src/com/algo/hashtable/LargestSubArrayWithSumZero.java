package com.algo.hashtable;
/**
 * You will be given an array of positive and negative integers,
 * and you have to find the size of the largest subarray, whose sum is equal to 0, and all the elements in the subarray should be contiguous.
 * Example:
 * Input:
 * Array[] = {1, 5, 3, -3, 4, -9, 7}
 * Output:
 * 5
 * Explanation:
 * The largest subarray whose sum is equal to 0 is {5, 3, -3, 4, -9}
 * Time complexity: O(N)
 * Space complexity: O(N)
 */

import java.util.HashMap;

public class LargestSubArrayWithSumZero {
    private void largestSubArrayWitSumZero(int array[]) {
        int n = array.length;
        HashMap<Integer, Integer> map = new HashMap();
        int sum = 0;
        // Without considering first ele at index 0, sum is 0.
        map.put(0, -1);
        // Put sum as key and value as index
        for (int i = 0; i < n; i++) {
            sum += array[i];
            // Only index with first occurrence of sum is pushed to map
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        sum = 0;
        int len = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
            /* If map has the same sum ,then ele from map.get(sum)+1 which is the earliest index the sum occurred
            until current i has sum 0.
             */
            if (map.containsKey(sum)) {
                int currLen = i - map.get(sum);
                // Update is tis is max length
                if (len < currLen) {
                    len = currLen;
                    startIndex = map.get(sum) + 1;
                    endIndex = i;
                }
            }
        }
        if (len != 0) {
            System.out.println("Largest sub array length= " + len + " from [" + array[startIndex] + " to " + array[endIndex] + "]");
        } else
            System.out.println("Not found");

    }

    public static void main(String[] args) {
        LargestSubArrayWithSumZero largestSubArrayWithSumZero = new LargestSubArrayWithSumZero();
        largestSubArrayWithSumZero.largestSubArrayWitSumZero(new int[]{1, 2, 3, 4, -6, -4, 5, 11});
        largestSubArrayWithSumZero.largestSubArrayWitSumZero(new int[]{1, 5, 3, -3, 4, -9, 7});
        largestSubArrayWithSumZero.largestSubArrayWitSumZero(new int[]{17, 23, 15, 25, 33, 17});


    }
}
