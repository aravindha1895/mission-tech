package com.algo.trees;

public class BinaryTreeNode {
    public int data;
    public  BinaryTreeNode left, right;

    public BinaryTreeNode(int item)
    {
        data = item;
        left = right = null;
    }
}