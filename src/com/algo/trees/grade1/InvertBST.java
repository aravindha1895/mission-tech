package com.algo.trees.grade1;
// TODO: DFS version of Mirroring
/**
 * Mirror of a Tree: Mirror of a Binary Tree T is another Binary Tree M(T)
 * with left and right children of all non-leaf nodes interchanged.
 * Time complexity - O(N)
 * Space complexity - O(N)
 */

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBST {
    static BinaryTreeNode root;

    /* Here we do level order traversal and swap left and rigth nodes as we move down */
    private void invertTree(BinaryTreeNode rootNode) {
        // write your code here
        Queue<BinaryTreeNode> q = new LinkedList();
        q.add(rootNode);
        while (!q.isEmpty()) {
            BinaryTreeNode node = q.remove();
            // Swap left and right child
            BinaryTreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
    }

    public static void main(String[] args) {
        /*
         Change a tree so that the roles of the left and  right pointers are swapped at every node.
            So the tree...
                 4
                / \
               2   5
              / \
             1   3

            is changed to...
                 4
                / \
               5   2
                  / \
                  3  1
    */
        InvertBST tree = new InvertBST();
        tree.root = new BinaryTreeNode(4);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(5);
        tree.root.left.left = new BinaryTreeNode(1);
        tree.root.left.right = new BinaryTreeNode(3);
        tree.invertTree(root);
        new BinaryTreePrinter(root).print(System.out);
    }
}
