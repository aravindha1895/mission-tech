package com.algo.hashtable;

import java.util.HashMap;

/**
 * You will be given an array of positive integers and an integer ‘k’.
 * You need to check whether the array can be divided into pairs of integers,
 * such that the sum of each pair of integers is divisible by k.
 * Note: Each integer in the array cannot be repeated across the pairs of integers.
 * Example:
 * Input:
 * Array = {17, 5, 23, 32, 25, 28}
 * k = 10
 * Output:
 * True
 * Explanation:
 * The array given above can be divided into pairs of integers such as {(17,23), (5, 25), (32, 28)},
 * where the sum of every pair is divisible by 10. Specifically:
 * Sum of (17, 23) is 40, which is divisible by 10.
 * Sum of (5, 25) is 30, which is divisible by 10.
 * Sum of (32, 28) is 60, which is divisible by 10.
 * Approach:
 * array: It is the given array of elements.
 * <p>
 * hashMap: It is a HashMap used to store the remainder of the array element when divided by ‘k’ as key,
 * and the count of that remainder (which means the number of times the same remainder appeared) as value.
 * <p>
 * If the size of the given array is odd, then print false and return.
 * <p>
 * Now, for every element of the array, get the remainder when array[i] is divided by k,
 * and store the remainder along with its count to the hashMap.
 * <p>
 * Next, traverse through the array, and for each element, get the remainder of the array element.
 * <p>
 * Now, using the value of the remainder, the count of the remainder, and the count of (k - remainder),
 * try to solve the problem.
 * Time complexity: O(N)
 * Space complexity: O(N)
 */
public class SumOfEachPairDivisibleByK {
    private static void checkPairs(int array[], int k) {
        int n = array.length;
        HashMap<Integer, Integer> map = new HashMap();
        // Put remainders in map, it it already as increase the frequency
        for (int i = 0; i < n; i++) {
            int remainder = array[i] % k;
            if (map.containsKey(remainder))
                map.put(remainder, map.get(remainder) + 1);
            else
                map.put(remainder, 1);
        }
        for (int i = 0; i < n; i++) {
            int remainder = array[i] % k;
            // Because remainder+(k-remainder) will be divisible by k
            if (map.containsKey(k - remainder)) { // Freq becomes 0 so delete
                if (map.get(k - remainder) == 1)
                    map.remove(k - remainder);
                else // Reduce freq
                    map.put(k - remainder, map.get(k - remainder) - 1);
            } else { // Pair not found for this element but we need pair for every element
                System.out.println("False");
                return;
            }
        }
        System.out.println("True");
    }

    public static void main(String[] args) {
        checkPairs(new int[]{17, 23, 15, 25, 33, 17}, 10);
        checkPairs(new int[]{10, 20, 30, 40, 50, 60, 70}, 5);
        checkPairs(new int[]{17, 5, 23, 32, 25, 28}, 10);


    }
}
