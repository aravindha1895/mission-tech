package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

/**
 * You are given a binary tree and you have to convert that to a binary tree such that the
 * left of each node is null and the nodes should be present towards the right itself. ( Construct right skewed tree)
 *
 * ( Alternate way of questioning ) https://www.geeksforgeeks.org/flatten-a-binary-tree-into-linked-list/
 * Given a binary tree, flatten it into linked list in-place.
 * Usage of auxiliary data structure is not allowed. After flattening,
 * left of each node should point to NULL and right should contain next node in level order.
 *
 * Examples:
 *
 * Input :
 *           1
 *         /   \
 *        2     5
 *       / \     \
 *      3   4     6
 *
 * Output :
 *     1
 *      \
 *       2
 *        \
 *         3
 *          \
 *           4
 *            \
 *             5
 *              \
 *               6
 */
public class FlattenBinaryTree {
    private BinaryTreeNode root;

    public void flatten(BinaryTreeNode root) {
        //write your code here
        if (root == null) return;
        BinaryTreeNode temp = root.left;
        if (temp != null) {
            while (temp.right != null)
                temp = temp.right;

            /* Temporarily assign RST to right most leaf of LST, assign LST to RST and recurse for RST */
            temp.right = root.right;
            root.right = root.left;
            root.left = null;
            flatten(root.right);

        } else {
            flatten(root.right);
        }

    }

    public static void main(String[] args) {
        FlattenBinaryTree tree = new FlattenBinaryTree();

        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);
        tree.root.right.left = new BinaryTreeNode(6);
        tree.root.right.right = new BinaryTreeNode(7);
        System.out.println("The original tree");
        new BinaryTreePrinter(tree.root).print(System.out);
        System.out.println();
        tree.flatten(tree.root);
        System.out.println("After flattening");
        new BinaryTreePrinter(tree.root).print(System.out);
    }
}
