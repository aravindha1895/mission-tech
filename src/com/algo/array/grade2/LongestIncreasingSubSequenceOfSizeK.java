package com.algo.array.grade2;
//TODO: Print actual sequence

/**
 * Given an array arr[] containing n integers. The problem is to count number of
 * increasing subsequences in the array of size k.
 * Examples:
 * Input : arr[] = {2, 6, 4, 5, 7},
 * k = 3
 * Output : 5
 * The subsequences of size '3' are:
 * {2, 6, 7}, {2, 4, 5}, {2, 4, 7},
 * {2, 5, 7} and {4, 5, 7}.
 * Input : arr[] = {12, 8, 11, 13, 10, 15, 14, 16, 20},
 * k = 4
 * Output : 39
 * Time complexity O(K*N^2).
 * Space complexity: O(N^2).
 * We can also print actual sequence by keeping matrix cell as class (Number,List)
 * and while calculating No. of LIS, we can also add in a list what are the actual elements.
 */
public class LongestIncreasingSubSequenceOfSizeK {
    // Method to find number of increasing sub sequences of size k
    static void numberOfIncreasingSubsequences(int arr[], int k) {
        int n = arr.length;
        int dp[][] = new int[k][n], sum = 0;

        /* Count of increasing subsequences of size ending at EACH arr[i]
         * So for the first row (l=1), everything is 1 -> here different dynamic programming approach is
         * followed where we dont calculate dp[i][j] based on dp[i][j-1] by checking dp[i][j] > dp[i][j-1]
         *  because it will be easy to proceed for higher l is we follow this approach.
         * SO to get total number of LIS of length 1, we have to add entire first row
         * before printing the answer similarly if LIS=2, we have to add 2nd row and so on..
         * */
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        print(dp);
        /* Building up the matrix dp[][] Here 'l' signifies the size of
        increasing subsequence of size (l+1) as l is index of array and starts from 0. */
        for (int l = 1; l < k; l++) {
            /* For each increasing subsequence of size 'l' ending with element arr[i]*/
            /* Start at i=l because for i<l we cannot have LIS pf size l */
            for (int i = l; i < n; i++) {

                /* Count of increasing subsequences of size 'l' ending with element arr[i]
                 * Here we are looking at index i for the given input (Ex. {2, 7, 3, 6, 9} let i=4, l=3)
                 * then we check if input[i]>input[j], for all j=l-1 to i. (i-e) We go one row up(LIS of l-1, let l=2)
                 * and if condition match, we add all LIS(l-1) because if there are k subsequence of
                 * length l-1 ending at dp[l-1][j], then there should be k subsequence of length l including current
                 * number ending at i.
                 * */
                dp[l][i] = 0;
                for (int j = l - 1; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[l][i] += dp[l - 1][j];
                    }
                }
            }
            print(dp);
        }

        /* Sum up the count of increasing subsequences of size 'k' ending at each element arr[i]*/
        for (int i = k - 1; i < n; i++) {
            sum += dp[k - 1][i];
        }

        /* Required number of increasing subsequences of size k*/
        System.out.println("No of LIS of size " + k + " is = " + sum);
        System.out.println();
    }

    private static void print(int arr[][]) {
        int i, j;
        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("--------------Test case 1-------------");
        numberOfIncreasingSubsequences(new int[]{2, 6, 4, 5, 7}, 3);
        System.out.println("--------------Test case 2-------------");
        numberOfIncreasingSubsequences(new int[]{12, 8, 11, 13, 10, 15, 14, 16, 20}, 4);
        System.out.println("--------------Test case 3-------------");
        numberOfIncreasingSubsequences(new int[]{3, 7, 1, 9, 13, 11, 19, 15, 20, 12}, 4);
    }
}
