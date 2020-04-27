package com.algo.stack.grade3;

import java.util.Stack;

/**
 * You will be given the record of the height of rainfall (in cm) for all 12 months in a year.
 * You need to design a program that performs below functions:
 * a. Compares the height of the rainfall of each month with the previous months of the year.
 * b. Prints the number of months that had the height of rainfall less than or equal to that of the current month
 * (including itself).
 * For example,
 * Input
 * 1 2 2 4 5 6 4 4 3 1 1 0
 * The input is an integer array of length 12,
 * which is representing the height of rainfall for all the months in a year.
 * Output
 * 1 2 3 4 5 6 5 6 4 2 3 1
 * Time complexity: O(C), c=12 months
 * Space complexity: O(C), c=12 months
 */
public class AnnualWeatherReport {
    static void printAnnualRainfall(int height[]) {
        Stack<Integer> s = new Stack();
        s.push(height[0]);
        System.out.print("1 ");// First month and we dont have any other month to compare. So output is always 1
        // For rest of the months
        for (int i = 1; i < height.length; i++) {
            // Pop out the months that had higher rainfall than this month
            while (!s.isEmpty() && s.peek() > height[i])
                s.pop();
            s.push(height[i]);
            /* Stack size gives no of months that had the height of rainfall less than or equal to that
             of the current month including current month as we have pushed current month also in stack
             */
            System.out.print(s.size() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printAnnualRainfall(new int[]{1, 2, 2, 4, 5, 6, 4, 4, 3, 1, 1, 0});
        printAnnualRainfall(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }
}
