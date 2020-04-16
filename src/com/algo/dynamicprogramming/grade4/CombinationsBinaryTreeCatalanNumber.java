package com.algo.dynamicprogramming.grade4;

/**
 * Given a preorder sequence how many unique trees can be created
 * Solution is catalan number. Number of tree is exactly same
 * as number of unique BST create with array of size n
 * <p>
 * The way it works for preorder sequence is as follows
 * <p>
 * Suppose our preorder sequence is 1 2 3 4
 * So we need to compute following things
 * count(3)* 2 (combination of 2,3 and 4 on both side of 1)
 * count(1)*count(2) (combination of 2 on one side and 3, 4 on other side)
 * count(2)*count(1) (combinatino of 2,3 on one side and 4 on other side)
 * count(3)*2 can be broken into count(3)*count(0) + count(0)*count(3)
 * <p>
 * So our final result is
 * count(0)*count(3) + count(1)*count(2) + count(2)*count(1) + count(3)*count(0)
 * which is a catalan number
 * <p>
 * NOTE: Same solution is applicable for catalan number, generate BST given n numbers ,generate BT wit n pre order numbers
 * Time complexity : O(n2)
 */
public class CombinationsBinaryTreeCatalanNumber {
    public static void main(String[] args) {
        printCatalanNumber(8);
    }

    private static void printCatalanNumber(int n) {
        int[] catalan = new int[n + 1];
        catalan[0] = catalan[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                catalan[i] += (catalan[j] * catalan[i - j - 1]);
            }
        }

        for (int i = 0; i <= n; i++)
            System.out.println(i + " -> " + catalan[i]);

    }
}
