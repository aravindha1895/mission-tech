package com.algo.string.grade2;

import java.util.HashMap;

/**
 * You will be given a binary string. You need to find the longest substring with an equal number of 1s and 0s.
 * Example:
 * str = 1101010100001111
 * Output: 11010101000011
 * str = 10101
 * Output = 1010
 * Time complexity: O(N)
 * Space complexity: O(N)
 */
public class LongestSubString {
    private static String getLongestSubString(String str) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int arr[] = new int[str.length()];
        int start_index = 0;
        int end_index = -1;
        int max_length = 0;
        int sum = 0;
        if (str.charAt(0) == '1') {
            arr[0] = 1;
        } else {
            arr[0] = -1;
        }
        for (int i = 1; i < str.length(); i++) {
            /* Add 1 when 1 is encountered, subtract 1 wen 0 is encountered */
            if (str.charAt(i) == '1') {
                arr[i] = arr[i - 1] + 1;
            } else {
                arr[i] = arr[i - 1] - 1;
            }
        }
        map.put(0, -1); // At -1t index we have no 1s and no 0s hence diff is 0
        for (int i = 0; i < str.length(); i++) {
            // Means we have matching sum in array
            if (map.containsKey(arr[i])) {
                if (max_length < i - map.get(arr[i])) {
                    max_length = i - map.get(arr[i]);
                    // String from map.get(arr[i]) not including map.get(arr[i]) till i have equal 1s and 0s.
                    start_index = map.get(arr[i]) + 1;
                    end_index = i;
                }
            } else map.put(arr[i], i);
        }
        if (end_index == -1)
            return null;
        else
            return str.substring(start_index, end_index + 1);
    }

    public static void main(String[] args) {
        System.out.println(getLongestSubString("1101010100001110"));
        System.out.println(getLongestSubString("11111111111111111111"));
        System.out.println(getLongestSubString("1101010100001111"));
    }
}
