package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;

/**
 * Given a Binary Tree, write a function that returns the size of the largest subtree which is also a Binary Search Tree (BST).
 * If the complete Binary Tree is BST, then return the size of the whole tree.
 * Time complexity - O(N)
 * Space complexity - O(H) were H=log N = Height of te tree
 */
class LargestBSTData {
    int size;
    int min;
    int max;
    int root;
    boolean isBST;

    public LargestBSTData(int size, int min, int max, boolean isBST) {
        this.size = size;
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }

    public LargestBSTData(boolean isBST) {
        this.isBST = isBST;
    }
}

public class LargestBSTinBinaryTree {
    static BinaryTreeNode root;

    public static LargestBSTData largestBSTData = new LargestBSTData(0, 0, 0, true);

    public static LargestBSTData getLargestBST(BinaryTreeNode node) {
        // NULL node is a BST of size 0
        if (node == null)
            return new LargestBSTData(0, 0, 0, true);
            // Leaf node is a BST of size 1 with both min and max so far equal to its data
        else if (node.left == null && node.right == null) {
            return new LargestBSTData(1, node.data, node.data, true);
        } else {
            // Get data of left sub tree
            LargestBSTData left = getLargestBST(node.left);
            // Get data of right sub tree
            LargestBSTData right = getLargestBST(node.right);

            /* Check if both LST and RST are BST. If yes check if current node.data as root whether it remains BST
             * For tat current node.data must be greater than largest value in LST and less than lowest value in RST
             */
            if ((left.isBST && right.isBST) && (node.data > left.max && node.data < right.min)) {
                int newSize = left.size + right.size + 1;
                // Update overall largest BST data is newSize is greater
                if (newSize > largestBSTData.size) {
                    largestBSTData.min = left.min;
                    largestBSTData.max = right.max;
                    largestBSTData.size = newSize;
                    largestBSTData.root = node.data;
                }
                // Return newly formed BST data to caller function in recursion
                return new LargestBSTData(newSize, left.min, right.max, true);
            } else {
                // Size, min, max does not matter if is isBST is false.
                return new LargestBSTData(0, 0, 0, false);
            }
        }

    }


    public static void main(String[] args) {
        LargestBSTinBinaryTree tree = new LargestBSTinBinaryTree();

        /*
            Below tree is constructed
        *      50
             /    \
          30       60
         /  \     /  \
        5   20   45    70
                      /  \
                    65    80
       * */
        tree.root = new BinaryTreeNode(50);
        tree.root.left = new BinaryTreeNode(30);
        tree.root.right = new BinaryTreeNode(60);
        tree.root.left.left = new BinaryTreeNode(5);
        tree.root.left.right = new BinaryTreeNode(20);
        tree.root.right.left = new BinaryTreeNode(45);
        tree.root.right.right = new BinaryTreeNode(70);
        tree.root.right.right.left = new BinaryTreeNode(65);
        tree.root.right.right.right = new BinaryTreeNode(80);
        getLargestBST(tree.root);
        System.out.println("Largest Binary tree data ");
        System.out.println("Size " + largestBSTData.size);
        System.out.println("Root " + largestBSTData.root);
        System.out.println("Range [ " + largestBSTData.min + " - " + largestBSTData.max + " ]");
    }
}
