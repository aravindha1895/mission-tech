package com.algo.string.grade2;
/**
 * You will be given a string which doesnt contain spaces.
 * You need to find the number of substrings possible with exactly k distinct characters where k is a given integer.
 * Example:
 * str = abcbaa
 * k = 3
 * Output: 8
 * Explanation: The substrings abc, abcb, abcba, abcbaa, bcba, bcbaa, cba, cbaa have exactly 3 distinct characters.
 * Time complexity: O(N)
 * Space complexity: O(N)
 * */
import java.util.HashSet;
import java.util.Set;

public class SubStringWithKDistinctCharacters {
    static String numberOfSubStrings(String str, int k) {
        str = str.toUpperCase(); // Case insensitive
        int count = 0;
        int curr_cont = 0;
        Set<Character> charSet = new HashSet();
        for (int i = 0; i < str.length() - 1; i++) {
            charSet.clear();
            // Add car to hash set
            charSet.add(str.charAt(i));
            for (int j = i + 1; j < str.length(); j++) {
                charSet.add(str.charAt(j));
                // As set has only unique items, size of the set gives number of unique characters
                curr_cont = charSet.size();
                // If current number of unique characters match with k, then increase overall count
                if (curr_cont == k) count++;
                /* If current number of unique characters becomes > k then there is no point
                in finding any more substrings, so break out of inner loop */
                if (curr_cont > k) break;
            }
        }
        return String.valueOf(count);
    }

    public static void main(String[] args) {
        System.out.println(numberOfSubStrings("ABCBAA", 3));
        System.out.println(numberOfSubStrings("abcbaa", 0));
        System.out.println(numberOfSubStrings("abc baa", 3));
    }
}
