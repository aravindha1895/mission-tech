package com.algo.array.grade3;
//TODO: Dynamic programming solution that solves this in O(n*x) time complexity

/**
 * There are n egg shops in Worli. Each shop can buy or sell exactly k eggs at a time.
 * The value of k will be different for different shops. You are the egg retailer with some m eggs initially.
 * You will be visiting the shops one by one in the given order. At each shop, you should buy or sell the eggs
 * such that you can’t have more than x eggs at any time. At each shop, you should perform only one
 * transaction either buying or selling. You will be given the value of k of each shop.
 * Find the maximum possible eggs that can be left with you after visiting all the shops.
 * Example:
 * k values of shops = {3, 10, 6, 4, 5}*
 * m = 1
 * x = 15
 * Output: 9
 * Maximum eggs that will be left = 1+3+10-6-4+5 = 9
 * Time complexity: O(2^N)
 * Space complexity: O(N) - Recursion
 */
public class MaxNoOfEggs {
    int max = -1;

    // Method to maximise the given number
    public void maximumNumberOfEggs(int[] arr, int m, int x) {
        int n = arr.length;
        int stock = m;
        int processed = 0;
        /*Buy as much as you can until selling is not possible due to negative value and buying not possible due to upper threshold*/
        int i;
        for (i = 0; i < n; i++) {
            if (stock + arr[i] > x) {
                processed = i - 1; // Take until previous iteration
                break;
            }
            ;
            if (stock - arr[i] < m) {
                stock += arr[i];
                processed = i; // Maintains until which index we ave processed
            } else break;// If not less than 0 we can either buy or sell so there are 2 possibilities
        }
        if (processed < n) {
            /* From this point we can either sell or buy, so call recursiveMaxFind with sign +1(buy)
             * and -1(Sell) and pass the processed index until which we have processed*/
            recursiveMaxFind(arr, processed, m, x, stock, 1);
            recursiveMaxFind(arr, processed, m, x, stock, -1);
          /*  recursiveMaxFind(arr, processed, m, x, stock + arr[processed], 1);
            recursiveMaxFind(arr, processed, m, x, stock + arr[processed], -1);

            recursiveMaxFind(arr, processed, m, x, stock - arr[processed], 1);
            recursiveMaxFind(arr, processed, m, x, stock - arr[processed], -1);*/
        }
    }

    public void recursiveMaxFind(int[] arr, int index, int m, int x, int stock, int sign) {
        if (stock > x || stock < m) return;
        // Record max transaction we have visited all shops
        if (index + 1 == arr.length) {
            if (max < stock) max = stock;
            return;
        }
        for (int i = index + 1; i < arr.length; i++) {
            // Buy or sell according to sign
            stock += arr[i] * sign;
            // x is upper bound and m is lower bound
            if (stock > x || stock < m) return;
            // Recursive call for buy
            recursiveMaxFind(arr, i, m, x, stock, 1);
            // Recursive call for sell
            recursiveMaxFind(arr, i, m, x, stock, -1);
        }
    }

    private void printMaxPossibleEggs(int arr[], int m, int x) {
        max = -1; // Include max
        maximumNumberOfEggs(arr, m, x);
        if (max == -1)
            System.out.println("Not Possible");
        else
            System.out.println(max);

    }

    public static void main(String[] args) {
        MaxNoOfEggs eggSellBuy = new MaxNoOfEggs();
        eggSellBuy.printMaxPossibleEggs(new int[]{3, 10, 6, 4, 5}, 1, 15);
        eggSellBuy.printMaxPossibleEggs(new int[]{2, 3, 5, 7, 9, 11, 13, 15, 17, 2, 4, 6, 8, 10, 12}, 5, 31);
        eggSellBuy.printMaxPossibleEggs(new int[]{7, 3, 14, 1, 6, 2, 4}, -8, 20);
    }
}
