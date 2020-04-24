package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

/**
 * You will be given a binary tree and you have to check whether the given tree is sum binary tree or not.
 * Sum tree is a binary tree in which the value of each node is the sum of all the nodes in the left subtree
 * and the right subtree. A leaf node is considered as a sum tree.
 * Time complexity - O(N)
 * Space complexity - O(H)
 */
public class SumBinaryTree {
    private BinaryTreeNode root;

    int isSumTree(BinaryTreeNode node) {
        // write your code here
        if (node == null) return 0;
        // For leaf nodes, return node.data itself
        if (node.left == null && node.right == null)
            return node.data;
        int leftSum = isSumTree(node.left);
        int rightSum = isSumTree(node.right);
        // For all non leaf nodes, check if sum(LST)+sum(RST)=node.data, if not return 0 meaning it is not sum tree
        if (leftSum + rightSum != node.data)
            return 0;
        else
            // If condition is satisfied, return sum(LST)+sum(RST)+node.data for parent node
            return leftSum + rightSum + node.data;

    }

    public static void main(String[] args) {
        SumBinaryTree tree = new SumBinaryTree();

        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);
        tree.root.right.left = new BinaryTreeNode(6);
        tree.root.right.right = new BinaryTreeNode(7);
        System.out.println("Case 1: The original tree");
        new BinaryTreePrinter(tree.root).print(System.out);
        System.out.println();
        boolean result = tree.isSumTree(tree.root) == 0 ? false : true;
        System.out.println(result);

        // Case 2
        tree.root = new BinaryTreeNode(20);
        tree.root.left = new BinaryTreeNode(3);
        tree.root.right = new BinaryTreeNode(7);
        tree.root.left.left = new BinaryTreeNode(1);
        tree.root.left.right = new BinaryTreeNode(2);
        tree.root.right.left = new BinaryTreeNode(3);
        tree.root.right.right = new BinaryTreeNode(4);
        System.out.println("Case 2: The original tree");
        new BinaryTreePrinter(tree.root).print(System.out);
        System.out.println();
        result = tree.isSumTree(tree.root) == 0 ? false : true;
        System.out.println(result);
    }

}
