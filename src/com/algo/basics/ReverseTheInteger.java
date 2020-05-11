package com.algo.basics;
/**
 * Given an integer value. You need to reverse the digits of the integer as shown in the below example.
 * Example:
 * Input:
 * n = 123
 * Output: 321
 * Input:
 * n = -123
 * Output: -321
 * Note: Check for overflow efficiently while reversing a integer
 * Time complexity Log(base10)(N). because at each iteration we do n/10.
 * Space complexity: O(1)
 * */

/*
Overflow logic:
To check whether a variable of type “int” is overflowed or not, you can use double or long because double or
long can store the integer values of range -261 to 261-1 which also covers the range of integer values
int can store i.e -231 to 231-1. But double or long will take 8 bytes whereas int takes only 4 bytes.
So try to check overflow by using only int.
To check whether a number is so large that it caused an overflow, backtrack the operations that you performed and check
if it is equal to the previous value.
If the overflow did not occur, the value after backtracking will be equal to the initial value or else they won’t be equal.
For example, imagine you multiplied “x” by 10 and then added 3:
Now backtrack both operations i.e subtract 3 from final value and then divide with 10
Now check whether the newly calculated value is equal to the value stored in “x” or not.

Let’s look at an example and let n=2147483646, which you multiplied by 10 and then added 3 to n.
First, backtrack the operations by subtracting 3 from n and then divide n by 10
The newly calculated value will be -17 which is not equal to 2147483646, so an overflow occurred.
* */
public class ReverseTheInteger {
    private static void reverseInteger(int n) {
        boolean isNeg=n<0;
        if(isNeg)n*=-1;
        int res=0;
        int x=res;
        while(n>0){
            res=res*10+n%10;
            // Check overflow
            if((res-n%10)/10!=x)// Overflow happened
            {
                System.out.println(0);return;
            }
            n=n/10;
            x=res;
        }
        System.out.println(isNeg?-res:res);
    }

    public static void main(String[] args) {
        reverseInteger(10000);
        reverseInteger(2147483612); // Overflow case
        reverseInteger(2147483602);
        reverseInteger(-12345);
        reverseInteger(12345);
    }
}
