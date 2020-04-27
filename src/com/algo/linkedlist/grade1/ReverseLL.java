package com.algo.linkedlist.grade1;

import java.util.StringJoiner;

/**
 * Reverse a given singly linked list, such that the first node becomes the last node,
 * the second node becomes the second last node and so on.
 * Approach: We just use 3 temp variables (prev,curr,next) to change pointers of the linked list to virtually reverse the list.
 * NO extra memory/ DS used.
 * Time complexity - O(n)
 * Space complexity - O(Constant)
 */
public class ReverseLL {
    Node head;
    static Node temp;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    /*this method will return the  head of the reversed linked list */
    private Node reverseLL() {
        Node next = null;
        Node current;
        Node prev = null;
        current = head;
        if (current == null) {
            System.out.println("list is empty");
            return null;
        }
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev; // Just to support print function
        return prev;
    }

    public void push(int new_data) {
        Node temp = head;
        Node new_node = new Node(new_data);
        if (head == null) {
            head = new_node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;
        }
    }

    /*Method to print LL*/
    private void print() {
        Node node = head;
        StringJoiner joiner = new StringJoiner("->");
        while (node != null) {
            joiner.add(String.valueOf(node.data));
            node = node.next;
        }
        System.out.println(joiner.toString());
    }

    private void insertAndReverseLL(int arr[]) {
        head = null;
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        System.out.print("Before reversal: ");
        print();
        reverseLL();
        System.out.print("After reversal: ");
        print();
        System.out.println();
    }

    public static void main(String[] args) {
        ReverseLL reverseLL = new ReverseLL();
        reverseLL.insertAndReverseLL(new int[]{1, 2, 3, 4, 5});
        reverseLL.insertAndReverseLL(new int[]{1, 2, 3, 4, 5, 6});
        reverseLL.insertAndReverseLL(new int[]{0});
    }
}
