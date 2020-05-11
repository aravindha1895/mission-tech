package com.algo.basics;

/**
 * You will be given an integer n and you need to find the last non-zero digit in n!.
 * Example:
 * n=10
 * n!=3628800
 * So last non-zero digit of the 10! Is 8.
 * The simple approach is to calculate n! and then find the last non-zero digit by dividing it with 10 until
 * the remainder is not equal to zero.
 * Time complexity: O(N).
 * Space complexity: O(1)
 */
public class LastNonZeroDigitOfFactorial {
    private static int lastNonZeroDigit(int n) {
        if (n == 0 || n == 1) return 1;
        int num = n;
        long factorial = 1;
        // Calculate the factorial
        while (num > 0) {
            factorial *= num;
            num--;
        }
        int last = 0;
        // We need last non zero digit, so remove trailing 0s.
        while ((int) factorial % 10 == (int) 0) {
            factorial = factorial / 10;
        }
        // After removing trailing 0s, mod gives te last non 0 digit
        last = (int) factorial % 10;
        return last;
    }

    public static void main(String[] args) {
        System.out.println(lastNonZeroDigit(10));
        System.out.println(lastNonZeroDigit(12));
        System.out.println(lastNonZeroDigit(9));
        System.out.println(lastNonZeroDigit(0));
    }
}
