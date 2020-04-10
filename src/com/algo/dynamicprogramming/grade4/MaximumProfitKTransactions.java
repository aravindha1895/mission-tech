package com.algo.dynamicprogramming.grade4;
/**
 * In share trading, a buyer buys shares and sells on a future date. Given the stock price of n days,
 * the trader is allowed to make at most k transactions, where a new transaction can only start
 * after the previous transaction is complete, find out the maximum profit that a share trader could have made.
 * Time complexity: O(N*N*N) for method 1, O(N*N) for method 2
 * Space complexity: O(N*N)
 */

import java.util.Stack;

public class MaximumProfitKTransactions {
    public static void printActualTransaction(int[][] profit, int[] stockPrice) {
        System.out.println("Transaction split up ");
        Stack<String> stack = new Stack<>();
        /* Look from last row, last column */
        int i = profit.length - 1;
        int j = profit[0].length - 1;
        while (true) {
            if (i <= 0 || j <= 0) {
                break;
            }
            if (j > 0 && profit[i][j] == profit[i][j - 1]) {
                j = j - 1;
                continue;
            }
            /* As we look from last row last column, and we are recording profit made so far, we are pushing sell first */
            stack.push("Sell at day " + j + " Current profit = " + profit[i][j]);
            // Calculate profit without this sell operation
            int maxDiff = profit[i][j] - stockPrice[j];
            i--; // One less transaction
            /* Calculate profit without the sell operation, go 1 day behind (j-1) and 1 transaction behind (i-1) and search for
            maxDiff == profit[i][k] - stockPrice[k]. Here we are subtracting stock price because we are buying, so subtract
            profit made so far - current stock price on that day*/
            for (int k = j - 1; k >= 0; k--) {
                if (i >= 0 && maxDiff == profit[i][k] - stockPrice[k]) {
                    /* k is the day we are buying the stock. SO final profit for that day will be
                    profit minus stock price of that day */
                    j = k;
                    stack.push("Buy at day " + k + " Current profit = " + (profit[i][j] - stockPrice[j]));
                    break;
                }
            }
        }
        /* Print all recorded transactions */
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void printDPTableAndMaxProfit(int[][] profit) {

        for (int i = 0; i < profit.length; i++) {
            for (int j = 0; j < profit[i].length; j++) {
                System.out.print(profit[i][j] + " ");
            }
            System.out.println();
        }
        // Last row last column
        System.out.println("\nMax profit that can be made = " + profit[profit.length - 1][profit[0].length - 1]);
    }

    /* Formula:
    Here we cumulatively accumulate the difference avoiding 3 rd for loop used in method 1 (Below).
    T[i][k] = max{  t[i][j-1]  // NOt transacting at all
                    stockPrice[j]+maxDiff;
                    [Update maxDiff to be used for next iteration maxDiff=max(maxDIff,profit[i-1][j]-stockPrice[j])]}
    */
    public static void calculateMaxProfitUsingDPMethod2(int[] stockPrice, int transactionCount) {

        int[][] profit = new int[transactionCount + 1][stockPrice.length];
        // If we have price for only 1 day, we cannot mae any profit. TO make profit we need at least 2 days one day to buy another to sell
        for (int i = 0; i < profit.length; i++) {
            profit[i][0] = 0;
        }
        // First row is 0 transaction. Whatever may be stock price, if we ave 0 transaction,
        // profit that can be made is 0
        for (int i = 0; i < profit[0].length; i++) {
            profit[0][i] = 0;
        }
        int maxDiff;
        for (int i = 1; i < profit.length; i++) {
            /* Every time we fill new row, we start from column 1 , so reset maxDiff to minus stockPrice[0] for new iter
             * Technically it is profit[i-1][0 (because j loop starts from 1)]-stockPrice[0] but 1st column is always So
             *  profit[i-1][0]-stockPrice[0] = 0-stockPrice[0]=-stockPrice[0];
             * */
            maxDiff = -stockPrice[0];
            for (int j = 1; j < profit[i].length; j++) {
                profit[i][j] = Math.max(profit[i][j - 1], stockPrice[j] + maxDiff);
                maxDiff = Math.max(maxDiff, profit[i - 1][j] - stockPrice[j]);
            }
        }
        printDPTableAndMaxProfit(profit);
        printActualTransaction(profit, stockPrice);

    }

    /* Formula:
        T[i][j] = max{  t[i][j-1]  // NOt transacting at all
                   stockPrice[j]-stockPrice[m]+t[i-1][m] m=0...j-1
    * */
    public static void calculateMaxProfitUsingDPMethod1(int[] stockPrice, int transactionCount) {

        int[][] profit = new int[transactionCount + 1][stockPrice.length];
        // If we have price for only 1 day, we cannot mae any profit. TO make profit we need at least 2 days one day to buy another to sell
        for (int i = 0; i < profit.length; i++) {
            profit[i][0] = 0;
        }
        // First row is 0 transaction. Whatever may be stock price, if we ave 0 transaction,
        // profit that can be made is 0
        for (int i = 0; i < profit[0].length; i++) {
            profit[0][i] = 0;
        }
        for (int i = 1; i < profit.length; i++) {
            for (int j = 1; j < profit[i].length; j++) {

                int max = -1;
                for (int k = 0; k < j; k++) {
                    /*From 1st day to current day-1 fetch max that can be done by buying on
                    k th day and selling on j th day. So subtract today price with kt price(day at which it is buyed)
                    and the profit that was made until kth day with 1 less transaction because we are doing 1 transaction today
                     */
                    max = Math.max(max, (stockPrice[j] - stockPrice[k]) + profit[i - 1][k]);
                }
                /* maximum from above from doing 1 transaction or not doing any transaction at all on tat day.
                Simply we use previous day profit if it is better
                 */
                max = Math.max(max, profit[i][j - 1]);
                profit[i][j] = max;
            }
        }
        printDPTableAndMaxProfit(profit);
    }

    public static void main(String[] args) {
        // Price of the stock on day 1, day 2, day3 ,.... day (arr.size)
        int[] stockPrice = {2, 5, 7, 1, 4, 3, 1, 3};
        int k = 3; // Total transaction allowed
        System.out.println("Method 1 matrix view ");
        calculateMaxProfitUsingDPMethod1(stockPrice, k);
        System.out.println();
        System.out.println("Method 2 matrix view ");
        calculateMaxProfitUsingDPMethod2(stockPrice, k);
    }
}
