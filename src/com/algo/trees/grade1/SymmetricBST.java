package com.algo.trees.grade1;

import com.algo.trees.BinaryTreeNode;

/**
 * You are given a binary tree and you have to identify whether the given tree is a symmetric binary tree or not.
 * A binary tree can be said symmetric, provided the mirror image of a binary tree is the same as the given binary tree.
 * Such that when you overlap both the mirror image and the given binary tree, they should seem alike
 * https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/
 * Time complexity - O(N)
 * Space complexity - O(H)
 */
public class SymmetricBST {
    private BinaryTreeNode root;

    public static void main(String[] args) {
        SymmetricBST tree = new SymmetricBST();

        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(2);
        tree.root.left.left = new BinaryTreeNode(3);
        tree.root.left.right = new BinaryTreeNode(4);
        tree.root.right.left = new BinaryTreeNode(4);
        tree.root.right.right = new BinaryTreeNode(3);

        System.out.println(tree.isSymmetric(tree.root, tree.root));
        // Change data to make tree non symmetric
        tree.root.right.right = new BinaryTreeNode(30);
        System.out.println(tree.isSymmetric(tree.root, tree.root));
    }

    public boolean isSymmetric(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 != null && node2 != null && node1.data == node2.data) {
            return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
        }
        return false;
    }
}
