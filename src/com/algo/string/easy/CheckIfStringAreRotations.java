package com.algo.string.easy;

/**
 * You are given two strings and you need to check if the two strings are rotations of each other
 * i.e first string can be obtained by rotating the second string by k elements.
 * Example:
 * s1 = abcde
 * s2 = cdeab
 * Output: true
 * Explanation: If you rotate the second string by three elements you will get the first string.
 * A simple approach is to check if the lengths of the two strings are equal or not.
 * If equal then rotate the second string by 1 element for each iteration until it matches with the first string.
 * Efficient approach:
 * Consider the two strings are stored in the variables str1 and str2
 * Now concatenate the same string str2+str2
 * If the two strings are rotations of each other, then str1 must be a substring of str2+str2
 * Example:
 * str1 = abcde
 * str2 = cdeab
 * str = str2+str2 = cdeabcdeab
 */
public class CheckIfStringAreRotations {
    static boolean areRotations(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        String temp = str2 + str2;
        if (temp.contains(str1))
            return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(areRotations("I'm a student. I'm preparing for my interviews. ", "I'm preparing for my interviews. I'm a student. "));
        System.out.println(areRotations("abfcde", "cdeab"));
        System.out.println(areRotations("abcde", "cdeab"));
    }
}
