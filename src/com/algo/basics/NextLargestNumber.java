package com.algo.basics;
/**
 * You are given a string which represents a number. You need to find the next largest number possible
 * by swapping a single digit.
 * Example:
 * Input: 2187654
 * Output: 2487651
 * Explanation: Swap 1 and 4 in the input to get the next largest number given in the output.
 * The approach is to find the rightmost digit which is less than the digit to its right.
 * Let that index be i. In the example (input) given above, this digit is 1.
 * Then find the minimum digit that is greater than the digit at ith index and is to the right of the ith index.
 * In the example (input) given above, this digit is 4.
 * Swap these two digits.
 * Time complexity O(N).
 * Space complexity: O(1)
 * */
public class NextLargestNumber {
    private static void nextLargestNumber(String str) {
        char[] input = str.toCharArray();
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < str.length() - 1; i++) {
            if (Integer.parseInt("" + str.charAt(i)) < Integer.parseInt("" + str.charAt(i + 1))) {
                index1 = i;
                index2 = -1;
            }
            if (index1 != -1) {
                if (index2 == -1)
                    index2 = i + 1;
                else if (Integer.parseInt("" + str.charAt(index2)) > Integer.parseInt("" + str.charAt(i + 1)) && Integer.parseInt("" + str.charAt(i + 1)) > Integer.parseInt("" + str.charAt(index1))) {
                    index2 = i + 1;
                }
            }
        }
        if (index1 == -1)
            System.out.println("Not Possible");
        else {
            // swap index1 and index2;
            char temp = input[index1];
            input[index1] = input[index2];
            input[index2] = temp;
            System.out.println(new String(input));
        }
    }

    public static void main(String[] args) {
        nextLargestNumber("54999988888777776666655555444443333222221111110000000");
        nextLargestNumber("2187654");
        nextLargestNumber("54321");
    }
}
