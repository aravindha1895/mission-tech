package com.algo.string.easy;

/**
 * You will be given a string that contains special characters
 * (~, !, @, #, $, %, ^, &, *, (, ), _, -, +, =, {, [, }, ], |, \, :, ;, ", ', <, ,, >, .,
 * ?, /, , 1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
 * along with alphabets ('a' to 'z' and 'A' to 'Z'). You need to reverse the string
 * in a way that special characters are not affected such that the
 * position of the special characters in the string does not change. Check the example given below,
 * Example:
 * Input: a@bc%dEf g&*jk
 * Output: k@jg%fEd c&*ba
 * Time complexity: O(N)
 * Space complexity: O(1)
 */
public class ReverseTheString {
    private static void reverseTheString(String str) {
        char[] arr = str.toCharArray();
        int l = 0, r = str.length() - 1;
        while (l < r) {
            if (Character.isLetter(arr[l]) && Character.isLetter(arr[r])) {
                char t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
                l++;
                r--;
            } else {
                if (!Character.isLetter(arr[l])) {
                    l++;
                }
                if (!Character.isLetter(arr[r])) {
                    r--;
                }
            }
        }
        System.out.println(new String(arr));
    }

    public static void main(String[] args) {
        reverseTheString("a!b@c#d$e%f^g&h*i(j)k_l-m+n=o{p}q[r]s|t\\u:v\"w<x>y5z,");
        reverseTheString("1234567890!@#$%^&*()+?");
        reverseTheString("abcdefghijklmnopqrstuvwxyz");
        reverseTheString("a@bc%dEf g&*jk");
    }
}
