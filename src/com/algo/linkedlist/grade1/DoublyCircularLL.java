package com.algo.linkedlist.grade1;

import java.util.StringJoiner;

/**
 * Program to create a Double Circular linked list of ‘n’ nodes and corresponding ‘n’ data values.
 * After creating, traverse your list in forward and backward direction and print both the output.
 * Following should be taken as input from the user :
 */
public class DoublyCircularLL {
    Node head;

    // Creating structure for nodes
    class Node {
        int data;
        Node next;
        Node prev;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    void push(int data) {
        // Write your code here
        if (head == null) {
            head = new Node(data);
            head.next = head;
            head.prev = head;
            return;
        }
        Node temp = head;
        while (temp.next != head)
            temp = temp.next;
        Node newNode = new Node(data);
        newNode.prev = temp;
        newNode.next = head;
        head.prev = newNode;
        temp.next = newNode;
    }

    private void print() {
        StringJoiner joiner = new StringJoiner("->");
        Node temp = head;
        if (head == null) {
            System.out.println("list is empty");
            return;
        }
        while (temp.next != head) {
            joiner.add(String.valueOf(temp.data));
            temp = temp.next;
        }
        joiner.add(String.valueOf(temp.data)); // Add last node
        System.out.println("Forward: " + joiner.toString());
        joiner = new StringJoiner("->");
        Node tail = head.prev;
        temp = tail;
        while (temp.prev != tail) {
            joiner.add(String.valueOf(temp.data));
            temp = temp.prev;
        }
        joiner.add(String.valueOf(temp.data));// Add first node
        System.out.println("Reverse: " + joiner.toString());
    }

    private void insertAndPrintLL(int[] arr) {
        head = null;
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        print();

    }

    public static void main(String[] args) {
        DoublyCircularLL doublyCircularLL = new DoublyCircularLL();
        doublyCircularLL.insertAndPrintLL(new int[]{1, 2, 3, 4, 5, 6});
        doublyCircularLL.insertAndPrintLL(new int[]{5, 3, 1, 2, 4, 8, 9, 9, 10, 0, 1, 6, 8, 9, 5, 4, 1, 66, 90, 11});

    }
}
