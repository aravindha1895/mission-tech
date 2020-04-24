package com.algo.trees.grade1;
/**
 * Given values of two values n1 and n2 in a Binary Search Tree,
 * find the Lowest Common Ancestor (LCA). You may assume that both the values exist in the tree.
 * Time complexity - O(H)
 * Space complexity - O(H)
 */

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

public class BSTLowestCommonAncestor {
    private BinaryTreeNode root;

    private int getLeastCommonAncestor(BinaryTreeNode node, int node1, int node2) {
        if (node == null) {
            return -1;
        }
        if ((node1 <= node.data && node2 > node.data) || (node1 < node.data && node2 >= node.data)) {
            return node.data;
        } else if (node1 < node.data && node2 < node.data)
            return getLeastCommonAncestor(node.left, node1, node2);
        else
            return getLeastCommonAncestor(node.right, node1, node2);
    }

    public static void main(String[] args) {

        BSTLowestCommonAncestor tree = new BSTLowestCommonAncestor();

        tree.root = new BinaryTreeNode(20);
        tree.root.left = new BinaryTreeNode(8);
        tree.root.right = new BinaryTreeNode(22);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(12);
        tree.root.left.right.left = new BinaryTreeNode(10);
        tree.root.left.right.right = new BinaryTreeNode(14);
        System.out.println("The original tree");
        new BinaryTreePrinter(tree.root).print(System.out);
        System.out.println();
        System.out.println(tree.getLeastCommonAncestor(tree.root, 10, 14));
        System.out.println(tree.getLeastCommonAncestor(tree.root, 8, 14));
        System.out.println(tree.getLeastCommonAncestor(tree.root, 10, 22));

    }
}
