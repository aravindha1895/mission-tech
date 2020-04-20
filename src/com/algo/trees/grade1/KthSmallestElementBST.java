package com.algo.trees.grade1;
/**
 * Given the root of a binary search tree and K as input, find K-th smallest element in BST.
 * If k-th smallest does not exist, print -1.
 * Time complexity - O(N)
 * Space complexity - O(H)
 */

import com.algo.trees.BinaryTreeNode;

import java.util.Stack;

public class KthSmallestElementBST {
    private BinaryTreeNode root;

    //function which returns the kth smallest element in the binary search tree
    // Iterative inorder traversal is done here
    public int kthsmallest(BinaryTreeNode node, int k) {
        //write your code here
        Stack<BinaryTreeNode> stack = new Stack();
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            BinaryTreeNode curr = stack.pop();
            k--;
            if (k == 0) return curr.data;
            else {
                if (curr.right != null) {
                    BinaryTreeNode temp = curr.right;
                    while (temp != null) {
                        stack.push(temp);
                        temp = temp.left;
                    }

                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /*
        The tree that is being dealt with:
                 4
                / \
               2   5
              / \
             1   3

         */
        KthSmallestElementBST tree = new KthSmallestElementBST();
        tree.root = new BinaryTreeNode(4);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(5);
        tree.root.left.left = new BinaryTreeNode(1);
        tree.root.left.right = new BinaryTreeNode(3);
        System.out.println(tree.kthsmallest(tree.root, 1));
        System.out.println(tree.kthsmallest(tree.root, 3));
        System.out.println(tree.kthsmallest(tree.root, 6));// Does not exist
    }
}
