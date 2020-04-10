package com.algo.trees.grade1;

import com.algo.trees.BinaryTreeNode;

import java.util.Stack;

/**
 * Iterative version of in-order traversal without recursion
 * Time Complexity: O(n)
 * Iterative version gives programmer more control than recursive version, during implementation of complex algo,
 * this approach gives more control for customization like traverse on need basis
 * */
public class IterativeInOrderTravesal {
    BinaryTreeNode root;

    public static void inOrder(BinaryTreeNode node) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(node);
        node=node.left;
        /* Pus all left sub tree nodes */
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        while (!stack.isEmpty()) {
            BinaryTreeNode currentNode = stack.pop();
            System.out.print(currentNode.data + " ");
            /* GO to immediate right sub tree and pus all left sub tree nodes */
            if (currentNode.right != null) {
                currentNode = currentNode.right;
                stack.push(currentNode);
                currentNode= currentNode.left;
                while (currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
            }
        }
    }

    public static void main(String[] args) {
        IterativeInOrderTravesal tree = new IterativeInOrderTravesal();
        /* Constructed binary tree is
                  1
                /   \
              2      3
            /  \
          4     5
        */
        tree.root =  new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);


        System.out.print("Iterative in order traversal ");
        inOrder(tree.root);
    }
}
