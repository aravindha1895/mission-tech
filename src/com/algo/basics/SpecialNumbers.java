package com.algo.basics;

/**
 * A number n is called special number if n can be expressed as the power of two integers.
 * n = pow(x,y), where x>0 and y>1.
 * Example:
 * n = 16
 * Output: Yes
 * Explanation: 16 = 2^4 or 4^2
 * n=48
 * Output: No
 * Explanation: Because you can’t express 48 as the power of two integers.
 * The approach is, for every x where 1<=x<=n check whether there exists a y such that pow(x,y) = n.
 * If you observe carefully, the minimum value y can have is 2. So, if you take any number greater than sqrt(n) as x
 * then for the minimum value of y (viz. 2), pow(x,y) will be greater than n. So checking for the numbers greater
 * than sqrt(n) is not necessary.
 * Time complexity: O(n*log(sqrt(n)))
 * Time complexity: O(1)
 */
public class SpecialNumbers {
    // Method to check whether the given number is a special number or not
    private static void powerOfTwoNumbers(int n) {
        int i = 2; // Start from power 2
        int k;
        /* For each i, check k^i==n, k=1,2,3,... */
        while (i <= Math.sqrt(n)) {
            k = 1;
            while (Math.pow(i, k) < n) k++;
            if (Math.pow(i, k) == n) {
                System.out.println(n + " = " + i + "^" + k);
                break;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        powerOfTwoNumbers(8);
        powerOfTwoNumbers(410338673);
        powerOfTwoNumbers(243);
        powerOfTwoNumbers(48);
        powerOfTwoNumbers(49);

    }
}
