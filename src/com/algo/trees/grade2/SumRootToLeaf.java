package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;

import java.util.ArrayList;

/**
 * You will be given a binary tree and a target sum,
 * you have to print all the paths from the root to the leaf nodes whose sum is equal to the given target sum.
 * Time complexity - O(N)
 * Space complexity - O(N) +  O(H) = O(N)
 */
public class SumRootToLeaf {
    private boolean isPathFound = false;
    private BinaryTreeNode root;

    /* DO pre order traversal and store each path from root to leaf in array*/
    private void preOrder(BinaryTreeNode rootNode, ArrayList<Integer> paths, int targetSum) {
        if (rootNode == null)
            return;
        paths.add(rootNode.data);
        // Leaf node so print the path
        if (rootNode.left == null && rootNode.right == null)
            printIfSumMatched(paths, targetSum);
        preOrder(rootNode.left, paths, targetSum);
        preOrder(rootNode.right, paths, targetSum);
        // If we use array below line is not needed as we operate array with index and dont use add() method
        // So parent function in call stack has one less index number than current function.
        paths.remove(paths.size() - 1);
    }

    private void printIfSumMatched(ArrayList<Integer> paths, int target) {
        int sum = paths.stream().mapToInt(num -> num).sum(); // Check if target sum is matched
        if (sum != target) {
            return;
        }
        isPathFound = true;
        for (int data : paths)
            System.out.print(data + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        SumRootToLeaf tree = new SumRootToLeaf();
                /*
                        Below tree is constructed.
                                 8

                            /          \

                        2                 31

                     /     \           /      \

                  1          4      16       61
        * */
        tree.root = new BinaryTreeNode(8);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(31);
        tree.root.left.left = new BinaryTreeNode(1);
        tree.root.left.right = new BinaryTreeNode(4);
        tree.root.right.left = new BinaryTreeNode(16);
        tree.root.right.right = new BinaryTreeNode(61);

        ArrayList<Integer> paths = new ArrayList<>();
        tree.preOrder(tree.root, paths, 11);
        tree.preOrder(tree.root, paths, 55);
        tree.preOrder(tree.root, paths, 10); // No paths

    }
}
