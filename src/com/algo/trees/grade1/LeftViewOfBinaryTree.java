package com.algo.trees.grade1;
/**
 * Given a Binary Tree, print left view of it.
 * Left view of a Binary Tree is set of nodes visible when tree is visited from left side.
 * https://www.geeksforgeeks.org/print-left-view-binary-tree/
 * Time complexity - O(N)
 * Space complexity - O(H) H = Height of tree.
 */

import com.algo.trees.BinaryTreeNode;

import java.util.LinkedList;

public class LeftViewOfBinaryTree {
    static BinaryTreeNode root;

    public static void main(String[] args) {
        LeftViewOfBinaryTree tree = new LeftViewOfBinaryTree();
        tree.root = new BinaryTreeNode(12);
        tree.root.left = new BinaryTreeNode(10);
        tree.root.right = new BinaryTreeNode(30);
        tree.root.right.left = new BinaryTreeNode(25);
        tree.root.right.right = new BinaryTreeNode(40);
        System.out.println("Tree 1:");
        printLeftView(root, new LinkedList<>(), 0);
        /*
                    1
                  /   \
                2       3
                  \
                    4
                      \
                        5
                         \
                           6

             Let us now print left view of tis tree
             Expected output: 1 2 4 5 6
        * */
        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.right = new BinaryTreeNode(4);
        tree.root.left.right.right = new BinaryTreeNode(5);
        tree.root.left.right.right.right = new BinaryTreeNode(6);
        System.out.println("\nTree 2:");
        printLeftView(root, new LinkedList<>(), 0);


    }

    /* Can be optimized by having level as just a integer and not as list. Because we start from level 0
     * and proceed down, and in any point we are not backtracking, so it is just enough if
     * we have the track of prev level alone.
     *  */
    public static void printLeftView(BinaryTreeNode node, LinkedList<Integer> level, int currentLevel) {
        if (node == null)
            return;
        // If this is the first node of that level
        if (!level.contains(currentLevel)) {
            System.out.println(node.data);
            // Mark the current level
            level.add(currentLevel);
        }
        /* Note: For right view of tree, just swap te below statements such that right child recurse first */
        printLeftView(node.left, level, currentLevel + 1);
        printLeftView(node.right, level, currentLevel + 1);
    }
}
