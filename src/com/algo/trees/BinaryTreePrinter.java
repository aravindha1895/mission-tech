package com.algo.trees;

import java.io.PrintStream;

/**
 * Just a helper class that can be used to print binary tree view in command line.
 * It print binary tree in horizontal manner
 */
public class BinaryTreePrinter {

    private BinaryTreeNode tree;

    public BinaryTreePrinter(BinaryTreeNode tree) {
        this.tree = tree;
    }

    private String traversePreOrder(BinaryTreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.data);

        String pointerRight = "└──";
        String pointerLeft = (root.right != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        traverseNodes(sb, "", pointerRight, root.right, false);

        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeNode node,
                               boolean hasRightSibling) {

        if (node != null) {

            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.right != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);

        }

    }

    public void print(PrintStream os) {
        os.print(traversePreOrder(tree));
    }

}