package com.algo.trees.grade1;

import com.algo.trees.BinaryTreeNode;

import java.util.Stack;

/**
 * Iterative version of pre-order travesal without recursion
 * Time Complexity: O(n)
 * Iterative version gives programmer more control than recursive version, during implementation of complex algo,
 * this approach gives more control for customization like traverse on need basis
 * */
public class IterativePreOrderTravesal {
    BinaryTreeNode root;

    public static void preOrder(BinaryTreeNode node) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTreeNode current = stack.pop();
            System.out.print(current.data + " ");
            /* In pre order we print root -> Left -> Right, but in stack push in reverse order -> Right, Left*/
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
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
        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);


        System.out.print("Iterative pre order traversal ");
        preOrder(tree.root);
    }
}
