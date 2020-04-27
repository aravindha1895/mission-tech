package com.algo.stack.grade3;
/**
 * Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. Given a binary tree, print the top view of it.
 * The output nodes can be printed in any order.
 * A node x is there in output if x is the topmost node at its horizontal distance.
 * Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1,
 * and that of right child is horizontal distance of x plus 1.
 * Time complexity is O(n)
 * Space complexity - O(n)
 */

import com.algo.trees.BinaryTreeNode;

import java.util.*;

public class TopViewBinaryTree {
    BinaryTreeNode root;

    class QueueObj {
        BinaryTreeNode node;
        int level;

        QueueObj(BinaryTreeNode node, int hd) {
            this.node = node;
            this.level = hd;
        }
    }


    private void printTopView() {
        Queue<QueueObj> queue = new LinkedList<QueueObj>();
        List<Integer> horizontalDistance = new LinkedList<Integer>();
        queue.add(new QueueObj(root, 0));
        while (!queue.isEmpty()) {
            QueueObj curr = queue.remove();
            if (!horizontalDistance.contains(curr.level)) {
                System.out.print(curr.node.data + " ");
                horizontalDistance.add(curr.level);
            }
            if (curr.node.left != null)
                queue.add(new QueueObj(curr.node.left, curr.level - 1));
            if (curr.node.right != null)
                queue.add(new QueueObj(curr.node.right, curr.level + 1));
        }
    }

    public static void main(String[] args) {
        /* Recursive solution (First thing that comes to mind) is not going to work as it does a DFS Ex., it 2
       nodes are at same level we have to print node at lowest level.
       Fails for the below case. Solution is to use BFS.(Implemented below)
                    1
                  /   \
                2       3
                  \
                    4
                      \
                        5
                         \
                           6
               */
        TopViewBinaryTree tree = new TopViewBinaryTree();
        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.right = new BinaryTreeNode(4);
        tree.root.left.right.right = new BinaryTreeNode(5);
        tree.root.left.right.right.right = new BinaryTreeNode(6);
        tree.printTopView();

    }
}
