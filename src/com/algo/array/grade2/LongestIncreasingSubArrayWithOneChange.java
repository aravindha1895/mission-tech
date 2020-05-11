package com.algo.array.grade2;

/**
 * LIS with One change
 * Given an array, find the length of the longest increasing subarray (contiguous elements)
 * such that it is possible to change at most one number (change one number to any integer you want)
 * from the sequence to make the sequence strictly increasing.
 * Examples:
 * Input  : 6
 * 7 2 3 1 5 10
 * Output : 5
 * Explanation :
 * Here, we can choose subarray 2, 3, 1, 5, 10
 * and by changing its 3rd element (that is 1)
 * to 4, it will become increasing sequence.
 * Input  : 2
 * 10 10
 * Output : 2
 * Explanation :
 * Here, we can choose subarray 10, 10 and by
 * changing its 2nd element (that is 10) to 11,
 * it will become increasing sequence.
 * Time complexity O(N).
 * Space complexity: O(N)
 */
public class LongestIncreasingSubArrayWithOneChange {
    // Method to find longest Increasing Subarray with one change allowed
    static void longestIncreasingSubarray(int a[]) {
        int n = a.length;
        int[] l = new int[n];
        int[] r = new int[n + 1];

        int ans = 1;
        l[0] = 1;
        // LIS ending at index i
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1])
                l[i] = l[i - 1] + 1;
            else
                l[i] = 1;
            ans = Math.max(ans, l[i]);
        }
        /* Edge case. Consider 1st test case {23, 26, 30, 32, 15, 13, 14, 18} in main,
         * we have continuous increasing subsequence from
         * 23 to 32 (Assume that this is index i). Before changing i+1 , we check if index (i+2) - (i)  > 1 for
         * the change i+1 to be valid, because if i+2 - i = 1, then there is only one number between them and that
         * number is in i+1 index, so we could not consider this as change. If (i+2) - (i) < 0,
         * making a change disrupts  increasing structure. But still in this case i+1 can be increased
         *  to have max increasing sub sequence, after whole logic is over if no other combination
         * gives us maximum value. TO accommodate this scenario,
         * by default we are considering 1+answer if  answer< n because if answer=n we are
         * already in end of array and we cannot change i+1 index*/
        if (ans != n)
            ++ans;

        // LIS starting at index i, hence right to left calculation
        r[n] = 0; // Extra space to avoid  bounds checking if else in last for loop
        r[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] < a[i + 1])
                r[i] = r[i + 1] + 1;
            else
                r[i] = 1;
        }
        for (int i = n - 2; i > 0; i--) {
            if (a[i + 1] - a[i - 1] > 1) {
                // Ending at l-1 + starting at r+1 and plus 1 for the number in i th index
                // +1 means we are modifying ith index (One change). So one more ele is added in LI sequence
                ans = Math.max(ans, l[i - 1] + r[i + 1] + 1);
            }
        }
        ans = Math.max(ans, r[0]);// because the above for loop if >0 and not >=0, LIS starting at r[0]
        System.out.println("LIS = " + ans);
    }

    public static void main(String[] args) {
        longestIncreasingSubarray(new int[]{23, 26, 30, 32, 15, 13, 14, 18});
        longestIncreasingSubarray(new int[]{11, 14, 15, 17, 18, 3, 20, 23, 26, 30});
        longestIncreasingSubarray(new int[]{10, 3, 6, 4, 9, 12});
        longestIncreasingSubarray(new int[]{20, 3, 4, 5, 6, 7, 8});
        longestIncreasingSubarray(new int[]{10, 10});
        longestIncreasingSubarray(new int[]{7, 2, 3, 1, 5, 10});
    }
}
