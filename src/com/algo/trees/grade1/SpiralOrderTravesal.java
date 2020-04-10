package com.algo.trees.grade1;

import com.algo.trees.BinaryTreeNode;

import java.util.Stack;

/**
 * Write a function to print spiral order traversal of a tree. For below tree, function should print 1, 2, 3, 4, 5, 6, 7.
 * spiral_order
                    1 Right to left
                   / \
                 2    3 left to right
                / \  / \
              7  6  5  4  Right to left
 * <p>
 * Time complexity - O(N)
 * Space complexity - O(L), L= Max number of nodes possible in any level
 */
public class SpiralOrderTravesal {
    static BinaryTreeNode root;

    /* We use 2 stacks one for left to right and other for right to left. For each level
     * we use different stacks. For level 0 (Left to right)-> stack 1 , level 1 (Right to left) ->stack 2,
     * level 2 (Left to right) -> stack 1 to get the spiral order.
     * IN stack as we are pushing and popping from same end unlike queues, we need 2 stacks for alternate levels to avoid
     * overlap of data between 2 different levels.
     * */
    public static void printSpiral(BinaryTreeNode node) {
        Stack<BinaryTreeNode> s1 = new Stack();
        Stack<BinaryTreeNode> s2 = new Stack();

        s1.push(node);
        /* Until any of te stack is not empty */
        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                BinaryTreeNode current = s1.pop();
                System.out.print(current.data + " ");
                /* First right child then left child is pushed to other stack s2*/
                if (current.right != null)
                    s2.push(current.right);
                if (current.left != null)
                    s2.push(current.left);
            }
            while (!s2.isEmpty()) {
                BinaryTreeNode current = s2.pop();
                System.out.print(current.data + " ");
                /* First left child then right child is pushed to other stack s1 */
                if (current.left != null)
                    s1.push(current.left);
                if (current.right != null)
                    s1.push(current.right);
            }
        }
    }

    public static void main(String[] args) {
        SpiralOrderTravesal tree = new SpiralOrderTravesal();
        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(7);
        tree.root.left.right = new BinaryTreeNode(6);
        tree.root.right.left = new BinaryTreeNode(5);
        tree.root.right.right = new BinaryTreeNode(4);
        System.out.println("Spiral Order traversal of Binary Tree is ");
        tree.printSpiral(root);
    }
}
