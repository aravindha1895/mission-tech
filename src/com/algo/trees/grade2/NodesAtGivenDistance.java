package com.algo.trees.grade2;
/**
 * Given a binary tree, a target node in the binary tree, and an integer value k,
 * print all the nodes that are at distance k from the given target node. No parent pointers are available.
 * Time complexity - O(N)
 * Space complexity - O(N)
 */

import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class NodesAtGivenDistance {
    //The root node of the binary tree
    private BinaryTreeNode rootNode;

    /* Function to print nodes downwards from target at dist k from target.  */
    private void allNodesAtDist(BinaryTreeNode node, int d) {
        if (node == null) return;
        if (d == 0) {
            System.out.println(node.data);
            return;
        } else {
            if (node.left != null)
                allNodesAtDist(node.left, d - 1);
            if (node.right != null)
                allNodesAtDist(node.right, d - 1);
        }
    }

    public static void main(String args[]) {
        NodesAtGivenDistance binaryTree = new NodesAtGivenDistance();
        // creating a new binary tree.
        binaryTree.rootNode = new BinaryTreeNode(20);
        binaryTree.rootNode.left = new BinaryTreeNode(8);
        binaryTree.rootNode.right = new BinaryTreeNode(22);
        binaryTree.rootNode.left.left = new BinaryTreeNode(4);
        binaryTree.rootNode.left.right = new BinaryTreeNode(12);
        binaryTree.rootNode.left.right.left = new BinaryTreeNode(10);
        binaryTree.rootNode.left.right.right = new BinaryTreeNode(14);
        new BinaryTreePrinter(binaryTree.rootNode).print(System.out);
        System.out.println();
        //Target BinaryTreeNode from which you need to find all the nodes at the distance distance
        BinaryTreeNode targetNode = binaryTree.rootNode;
        int target = 8;
        int distance = 2;

        // q2FindTarget is used to find the target node with the given value
        Queue<BinaryTreeNode> q2FindTarget = new LinkedList<BinaryTreeNode>();
        /* Maintain parent stack to go upward from target node */
        Stack<BinaryTreeNode> parent = new Stack<BinaryTreeNode>();
        q2FindTarget.add(binaryTree.rootNode);

        //finding the target node by doing level order traversal of the tree
        while (!q2FindTarget.isEmpty()) {
            targetNode = q2FindTarget.poll();
            parent.push(targetNode);
            // If we have found target node, we stop iterating
            if (targetNode.data == target) {
                break;
            }
            if (targetNode.left != null) {
                q2FindTarget.add(targetNode.left);
            }
            if (targetNode.right != null) {
                q2FindTarget.add(targetNode.right);
            }
        }

        if (!parent.isEmpty())
            parent.pop();// Pop out target node

        // Printing all nodes at the given distance  from the target node (Downward wit target node as parent).
        binaryTree.allNodesAtDist(targetNode, distance);
        int d = distance;
        /* Now we go upward from the target node to print other nodes at distance k from target */
        while (!parent.isEmpty()) {
            BinaryTreeNode curr = parent.pop();
            // Check if this node the parent of target node
            if (curr.left != null && curr.left.data == targetNode.data) {
                d--; // Parent of target node is at d-1 from target
                targetNode = curr; // Update target for next iteration
                binaryTree.allNodesAtDist(curr.right, d - 1);// Another d-1 as right child is at distance 1 from the parent
            } else if (curr.right != null && curr.right.data == targetNode.data) {
                d--;
                targetNode = curr;
                binaryTree.allNodesAtDist(curr.left, d - 1);
            }
        }
    }
}

