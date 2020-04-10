package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;

/**
 * Given a binary tree in which each node element contains a number. Find the maximum possible sum from one leaf node to another.
 * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/ for view of the tree
 * Time Complexity O(N)
 */
class MaxPathNode {
    int root;
    int sum;

    public MaxPathNode(int root, int sum) {
        this.root = root;
        this.sum = sum;
    }
}

public class MaxPathSumBetweenTwoLeaves {
    BinaryTreeNode root;

    public static MaxPathNode maxPathNode = new MaxPathNode(-1, 0);

    public static MaxPathNode getMaxPathNode(BinaryTreeNode node) {
        // Sum of NULL node is 0
        if (node == null) {
            return new MaxPathNode(-1, 0);
        } else {
            // Get left sum
            MaxPathNode left = getMaxPathNode(node.left);
            // Get right sum
            MaxPathNode right = getMaxPathNode(node.right);

            int sum = left.sum + right.sum + node.data;
            // Check if root as current node, sum of LST, RST + current node gives a greater sum, If yes update
            if (sum > maxPathNode.sum) {
                maxPathNode.sum = sum;
                maxPathNode.root = node.data;
            }
            /*  While returning to parent node, we have to consider only max among LST and RST + curr node,
            * because parent node now will become root and it has its own LST and RST
            * */
            return new MaxPathNode(node.data, Math.max(left.sum, right.sum) + node.data);

        }

    }


    public static void main(String[] args) {
        MaxPathSumBetweenTwoLeaves tree = new MaxPathSumBetweenTwoLeaves();
        tree.root = new BinaryTreeNode(-15);
        tree.root.left = new BinaryTreeNode(5);
        tree.root.right = new BinaryTreeNode(6);
        tree.root.left.left = new BinaryTreeNode(-8);
        tree.root.left.right = new BinaryTreeNode(1);
        tree.root.left.left.left = new BinaryTreeNode(2);
        tree.root.left.left.right = new BinaryTreeNode(6);
        tree.root.right.left = new BinaryTreeNode(3);
        tree.root.right.right = new BinaryTreeNode(9);
        tree.root.right.right.right = new BinaryTreeNode(0);
        tree.root.right.right.right.left = new BinaryTreeNode(4);
        tree.root.right.right.right.right = new BinaryTreeNode(-1);
        tree.root.right.right.right.right.left = new BinaryTreeNode(10);

        tree.getMaxPathNode(tree.root);
        System.out.println("Max path sum from leaf to leaf = " + maxPathNode.sum);
        System.out.println("Root of Max path sum tree = " + maxPathNode.root);
    }
}
