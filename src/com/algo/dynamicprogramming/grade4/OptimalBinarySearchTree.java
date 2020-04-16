package com.algo.dynamicprogramming.grade4;
/**
 * Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts,
 * where freq[i] is the number of searches to keys[i].
 * Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
 * <p>
 * Time Complexity:  O(n^3) , O(n^2) if prefixSum is calculated beforehand.
 * Below implementation has O(n^2) time complexity
 * Auxiliary Space: O(n^2)
 */

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

class OptimalBinarySearchTreeCell {
    int parent;
    int cost;

    public OptimalBinarySearchTreeCell(int cost, int parent) {
        this.parent = parent;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return cost + "(" + parent + ")";
    }
}

public class OptimalBinarySearchTree {

    BinaryTreeNode root;

    private void findAndPrintOptimalCostBST(int[] keys, int[] freq) {
        int n = keys.length;
        int prefixSum[] = new int[n];
        prefixSum[0] = freq[0];
        /* Generate prefix sum tat is needed in every iteration below. Once generated prefix sum can be fetched in O(1) time*/
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + freq[i];

        OptimalBinarySearchTreeCell cost[][] = new OptimalBinarySearchTreeCell[n][n];

        /* Fill Main Diagonal wit te element itself  */
        for (int i = 0; i < n; i++)
            cost[i][i] = new OptimalBinarySearchTreeCell(freq[i], -1);

        for (int level = 2; level <= n; level++) {
            for (int i = 0; i < n - level + 1; i++) {
                int j = i + level - 1;
                int optimalCost = Integer.MAX_VALUE;
                int parent = -1;
                /*From k =  i to j, try everything as root and select root that gives min cost */
                for (int k = i; k <= j; k++) {
                    int leftSum = (k == i) ? 0 : cost[i][k - 1].cost;
                    int rightSum = (k == j) ? 0 : cost[k + 1][j].cost;
                    /* If picking this k gives min cost, ten make tis k as parent and update min cost */
                    if (leftSum + rightSum < optimalCost) {
                        optimalCost = leftSum + rightSum;
                        parent = k;
                    }
                }
                /* Prefix cost is te sum of all frequencies considered. This is done because wen we add a new
                 * node (Level increases), all existing nodes as to pass trough these node / new level increasing
                 * the cost by factor 1. so we have to add 1*freq[i..j]  */
                if (i == 0)
                    optimalCost += prefixSum[j];
                else
                    /*Prefix sum has cumulative sum from index 0, so for sum i to j wen i!=0
                     * we have to subtract prefixSum[i - 1]*/
                    optimalCost += prefixSum[j] - prefixSum[i - 1];
                cost[i][j] = new OptimalBinarySearchTreeCell(optimalCost, parent);
            }
        }

        System.out.println("The dynamic programming matrix view ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cost[i][j] != null)
                    System.out.print(cost[i][j].toString() + " ");
                else
                    System.out.print("      ");
            }
            System.out.println();
        }
        System.out.println("Min cost  = " + cost[0][n - 1].cost);
        root = generateActualBinaryTree(root, cost, keys, 0, n - 1);
        System.out.println("The actual optimal binary tree is ");
        new BinaryTreePrinter(root).print(System.out);
    }

    public BinaryTreeNode generateActualBinaryTree(BinaryTreeNode node, OptimalBinarySearchTreeCell[][] cost, int[] keys, int leftBound, int rightBound) {
        int parentIndex = cost[leftBound][rightBound].parent;
        /* If node is null Create new node */
        if (node == null) {
            if (parentIndex != -1)
                node = new BinaryTreeNode(keys[parentIndex]);
            else
                /* If we come here ten it means leftBound=rightBound. So use bound itself as a index
                 *  node = new BinaryTreeNode(keys[leftBound]) is same as
                 *  node = new BinaryTreeNode(keys[rightBound])
                 * as leftBound=rightBound. So use anyone of the statement */
                node = new BinaryTreeNode(keys[leftBound]);
        }
        /*Check for out of bounds. if out of bounds, it means respective left is null*/
        if (parentIndex != -1 && parentIndex - 1 >= 0)
            node.left = generateActualBinaryTree(node.left, cost, keys, leftBound, parentIndex - 1);
        /*Check for out of bounds. if out of bounds, it means respective right is null*/
        if (parentIndex != -1 && parentIndex + 1 < keys.length)
            node.right = generateActualBinaryTree(node.right, cost, keys, parentIndex + 1, rightBound);

        // Return formed node
        return node;
    }

    public static void main(String[] args) {
        int keys[] = {10, 12, 16, 21};
        int freq[] = {4, 2, 6, 3};
        new OptimalBinarySearchTree().findAndPrintOptimalCostBST(keys, freq);
    }


}
