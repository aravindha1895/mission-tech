package com.algo.string.grade2;
/**
 * You are given two strings. You need to check whether the given two strings are anagrams to each other.
 * The two strings are said to be anagrams of each other if and if both the strings contain the same characters and the order may be different.
 * Example:
 * str1 = listen
 * str2 = silent
 * Output: true
 * Example:
 * str1 = Listen
 * str2 = Silent
 * Output: false
 * Explanation: Uppercase and lowercase characters have different ASCII values,
 * so they are considered as different characters.
 * A simple approach is to sort both the strings based on the ASCII values of the characters
 * in the given string and check whether sorted strings are equal or not.
 * Cons: The ideal sorting algorithm takes O(nlogn) to sort a string of length n.
 * In the worst case comparing two strings for equality takes O(n) time.
 * So in total to sort two strings of length n and to compare the strings, the time complexity will be O(2*n*logn+n) i.e O(nlogn)
 * Below is a liner approach
 * Time complexity: O(N)
 * Space complexity: O(N)
 * Moreover if we consider ASCII space, N becomes N=256 and algo becomes constant time algorithm
 */

import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    // Method to check if two strings are anagrams of each other
    static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        // Add characters of string 1 to map along wit frequencies
        for (int i = 0; i < str1.length(); i++) {
            if (map1.containsKey(str1.charAt(i))) {
                map1.put(str1.charAt(i), map1.get(str1.charAt(i)) + 1);
            } else {
                map1.put(str1.charAt(i), 1);
            }
        }
        // Loop for string 2
        for (int i = 0; i < str2.length(); i++) {
            // If map already has character (By string 1) then reduce frequency by 1 (Act of nullifying)
            if (map1.containsKey(str2.charAt(i))) {
                map1.put(str2.charAt(i), map1.get(str2.charAt(i)) - 1);
            } else {
                // New character in string 2. String are not anagrams if we reach here itself
                map1.put(str1.charAt(i), 1);
            }
        }
        /* We increase frequency for string 1, decrease frequency for string2, so for all characters, the end
         * frequency must be 0, for them to be anagrams */
        for (Character c : map1.keySet()) {
            if (map1.get(c) != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("I'm a student. I'm preparing for my interviews.", "I'm preparing for my interviews. I'm a student."));
        System.out.println(areAnagrams("debit card", "bad credit"));
        System.out.println(areAnagrams("Listen", "Silent")); // Case sensitive
        System.out.println(areAnagrams("listen", "silent"));
    }
}
