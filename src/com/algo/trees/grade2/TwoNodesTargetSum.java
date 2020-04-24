package com.algo.trees.grade2;

import com.algo.trees.BinaryTreeNode;

import java.util.Stack;

/**
 * You will be given a binary search tree and a target sum,
 * you have to find whether there are any two nodes in the BST whose sum is equal to the given target sum.
 * Time complexity - O(N)
 * Space complexity - O(N)
 */
public class TwoNodesTargetSum {
    private BinaryTreeNode root;

    /* Here we use 2 stacks one for inorder traversal and one for reverse in order traversal.
    * Like 2 pointer concept, if target sum is less than node1+node 2 then we proceed inorder else we proceed with
    * reverse inorder. If they cross over then the target sum is not fond.
    *  */
    private int CheckForSum(BinaryTreeNode rootNode, int targetSum) {
        Stack<BinaryTreeNode> inOrder = new Stack<>();
        Stack<BinaryTreeNode> revOrder = new Stack<>();
        BinaryTreeNode inNode, revNode;
        int value1 = 0, value2 = 0;
        inNode = rootNode;
        revNode = rootNode;
        boolean done1 = false, done2 = false;
        while (true) {
            while (!done1) {
                if (inNode != null) {
                    inOrder.push(inNode);
                    inNode = inNode.left;
                } else {
                    done1 = true;
                    // Prepare node for next inorder cycle
                    if (!inOrder.isEmpty()) {
                        inNode = inOrder.pop();
                        value1 = inNode.data;
                        inNode = inNode.right;
                    } else {
                        inNode = null;
                    }
                }
            }
            // Mirror of the above code for reverse order
            while (!done2) {
                if (revNode != null) {
                    revOrder.push(revNode);
                    revNode = revNode.right;
                } else {
                    done2 = true;
                    // Prepare node for next reverse order cycle
                    if (!revOrder.isEmpty()) {
                        revNode = revOrder.pop();
                        value2 = revNode.data;
                        revNode = revNode.left;
                    } else {
                        revNode = null;
                    }
                }
            }
            // To ensure value 1 and value 2 are not same
            if (value1 != value2 && value1 + value2 == targetSum) {
                System.out.println("Node 1 = " + value1 + " Node 2 = " + value2+" :Target = "+targetSum);
                return targetSum;
            } else if (value1 + value2 < targetSum)
                done1 = false; // Do in order
            else if (value1 + value2 > targetSum)
                done2 = false; // Do reverse order
            if (value1 >= value2) {
                System.out.println("Target sum not found");
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        /*
                        Below tree is constructed.
                                 8

                            /          \

                        2                 31

                     /     \           /      \

                  1          4      16       61
        * */
        TwoNodesTargetSum twoNodesTargetSum = new TwoNodesTargetSum();
        twoNodesTargetSum.root = new BinaryTreeNode(8);
        twoNodesTargetSum.root.left = new BinaryTreeNode(2);
        twoNodesTargetSum.root.right = new BinaryTreeNode(31);
        twoNodesTargetSum.root.left.left = new BinaryTreeNode(1);
        twoNodesTargetSum.root.left.right = new BinaryTreeNode(4);
        twoNodesTargetSum.root.right.left = new BinaryTreeNode(16);
        twoNodesTargetSum.root.right.right = new BinaryTreeNode(61);

        twoNodesTargetSum.CheckForSum(twoNodesTargetSum.root, 33);
        twoNodesTargetSum.CheckForSum(twoNodesTargetSum.root, 62);
        twoNodesTargetSum.CheckForSum(twoNodesTargetSum.root, 5);
        twoNodesTargetSum.CheckForSum(twoNodesTargetSum.root, 78);
    }
}
