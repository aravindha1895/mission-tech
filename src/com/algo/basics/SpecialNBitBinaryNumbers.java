package com.algo.basics;
/**
 * An n-bit binary number is called special binary number if the number of 1s is more than or equal to the number of 0s in any prefix (including the entire number) of the number.
 *
 * You are given an integer n and you need to count the number of special n-bit binary numbers,
 * which are possible using recursion.
 * Example:
 * n = 4
 * Output: 6
 * Explanation:
 * 1111
 * 1110
 * 1101
 * 1100
 * 1011
 * 1010
 * For example, take 1011. 1011 has three 1’s and one 0. 101 has two 1’s and one 0. 10 has one 1 and one 0.
 * 1 has one 1 and zero 0’s. In any prefix, the number of 1’s is equal to or greater than the number of 0’s.
 * For example, take 1011. 1011 has three 1’s and one 0. 101 has two 1’s and one 0. 10 has one 1 and one 0.
 * 1 has one 1 and zero 0’s. In any prefix, the number of 1’s is equal to or greater than the number of 0’s.
 * Approach:
 * An efficient approach is to generate only those n-bit binary numbers that satisfy the given conditions.
 * You can use recursion to achieve this. At each point in the recursion, you need to append 0 or 1 to the
 * partially formed number and call the recursive function with one less digit.
 * Time complexity O(2^N).
 * Space complexity: O(N)
 * */
public class SpecialNBitBinaryNumbers {
    private static int count =0;
    // Method to count number of special n-bit binary numbers
    private static void specialBinaryNumbers(int n) {
        count=0; // Reset count for each test case
        /* We keep track of difference between No. of 1s and 0s in diff. If diff>0, it means we have more 1s. */
        specialBinaryNumbersRecv(n-1,new StringBuilder().append(1),1);
        System.out.println(count);
    }
    private static void specialBinaryNumbersRecv(int n,StringBuilder sb,int diff){
        /* If No. of 1 is greater and we ave got desired length n */
        if(diff>=0 && n==0){
            count++;
        }
        if(n-1>=0){
            /* Because te problem statement is In any prefix, the number of 1’s is equal to or greater than the number of 0’s.
            * if if diff is 0 we cannot add any more zero in this iteration. SO for example though 1001 has equal No. of 0 and 1,
            * cannot be part of answer because prefix 100 is more 0s. Hence this if condition. If we are not concerned about
            * prefix, this condition is not needed
            *  */
            if(diff!=0)
                specialBinaryNumbersRecv(n-1,new StringBuilder(sb).append(0),diff-1);
            specialBinaryNumbersRecv(n-1,new StringBuilder(sb).append(1),diff+1);
        }
    }

    public static void main(String[] args) {
        specialBinaryNumbers(4);
        specialBinaryNumbers(7);
        specialBinaryNumbers(0);
        specialBinaryNumbers(25);
    }
}
