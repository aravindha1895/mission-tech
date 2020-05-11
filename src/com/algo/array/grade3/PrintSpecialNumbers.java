package com.algo.array.grade3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
// TODO: Optimize this program further

/**
 * You are given an array of integers. You need to print the special numbers in the array.
 * A number is called special number if it is divisible by at least one other number of the array.
 * Example:
 * arr[] = {2, 3, 5, 7, 6, 12, 25, 40, 34, 9 }
 * Output:
 * 6 12 25 40 34 9
 * The simple approach will be running 2 nested loops and checking every element if it is
 * divisible by any other element in the array. But time complexity is
 * But think if the number of elements in the array is very high when compared to the maximum element
 * in the array and try to solve efficiently.
 * Time complexity: O(N)
 * Space complexity: O(1)
 */
public class PrintSpecialNumbers {
    private static void divisibilityCheck(int[] arr) {
        int n = arr.length;
        /* Storing all array elements in a hash and finding maximum element in array */
        List<Integer> s = new ArrayList<Integer>();
        int max_ele = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            s.add(arr[i]);
            /* Finding maximum element of array */
            max_ele = Math.max(max_ele,
                    arr[i]);
        }
        /* Traversing array element and storing the array multiples that are present in s in res.*/
        LinkedHashSet<Integer> res = new LinkedHashSet<Integer>();
        for (int i = 0; i < n; i++) {
            // Check for non-zero values only
            if (arr[i] != 0)
                // checking the factor of current element
                for (int j = arr[i] * 2; j <= max_ele; j += arr[i]) {
                    /* If factor is already part of array element then store it */
                    if (s.contains(j)) {
                        res.add(j);
                    }
                }
        }
        List<Integer> list = new ArrayList<Integer>(res);
        if (res.size() == 0) {
            System.out.println("No Special Numbers");
            return;
        }
        /*We can directly print res set, the below code is just to maintain expected output order*/
        for (int i = 0; i < n; i++) {
            if (res.contains(arr[i]))
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        divisibilityCheck(new int[]{5, 8, 12, 99, 32, 17, 51, 69, 7, 10, 77, 43, 11, 81, 100});
        divisibilityCheck(new int[]{2, 7, 5, 3, 13, 11, 17});
        divisibilityCheck(new int[]{2, 3, 5, 7, 6, 12, 25, 40, 34, 9});
    }
}
