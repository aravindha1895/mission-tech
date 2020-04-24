package com.algo.trees.grade1;

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 * Time complexity - O(N)
 * Space complexity - O(H)
 */
public class BTLowestCommonAncestor {
    private BinaryTreeNode root;

    private BinaryTreeNode getLeastCommonAncestorBT(BinaryTreeNode node, int node1, int node2) {
        if (node == null) return null;
        // If any node node match, return that node
        if (node1 == node.data || node2 == node.data) {
            return node;
        }
        BinaryTreeNode left = getLeastCommonAncestorBT(node.left, node1, node2);
        BinaryTreeNode right = getLeastCommonAncestorBT(node.right, node1, node2);
        /* We assume there are no duplicate nodes. So if both left and right are not null, it means one node is on
         * left side and one is on right side.
         * (If 2 nodes are on one side and there are no duplicates, then one of the sub tree must be NULL)
         * Moreover as we are doing DFS, first node tat satisfies this condition is LCA.
         *   */
        if (left != null && right != null)
            return node;
        /*If right is null, then both values are in LST, so we search in LST*/
        if (left != null) return left;
            /*If left is null, then both values are in RST, so we search in RST*/
        else return right;

    }

    public static void main(String[] args) {

        BTLowestCommonAncestor tree = new BTLowestCommonAncestor();

        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);
        tree.root.left.right.left = new BinaryTreeNode(6);
        tree.root.left.right.right = new BinaryTreeNode(7);
        System.out.println("The original tree");
        new BinaryTreePrinter(tree.root).print(System.out);
        System.out.println();
        System.out.println(tree.getLeastCommonAncestorBT(tree.root, 4, 5).data);
        System.out.println(tree.getLeastCommonAncestorBT(tree.root, 4, 6).data);
        System.out.println(tree.getLeastCommonAncestorBT(tree.root, 3, 4).data);
        System.out.println(tree.getLeastCommonAncestorBT(tree.root, 2, 4).data);

    }
}
