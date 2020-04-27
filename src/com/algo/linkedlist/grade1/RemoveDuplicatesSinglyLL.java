package com.algo.linkedlist.grade1;

import java.util.StringJoiner;

/**
 * You are given with a singly sorted linked list having ‘n’ nodes.
 * The list is such that it may have elements existing more than one time.
 * Remove the repeated elements and return a linked list that contains each unique element only once.
 * Input: Sorted linked list with duplicate elements.
 * Time complexity - O(n)
 * Space complexity - O(Constant)
 */
public class RemoveDuplicatesSinglyLL {
    Node head;
    boolean isUnique = true;

    //class to create nodes.
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    void removeDuplicates() {
        Node temp = head;
        /* Traverse entire list and change pointers to remove duplicates */
        while (temp != null) {
            Node t = temp;
            // For the duplicates nodes
            while (t.next != null && t.next.data == temp.data) {
                t = t.next;
                isUnique = false;
            }
            // Make current node next point to next unique node
            temp.next = t.next;
            temp = temp.next;
        }
    }

    // Below methods would help you create a singly linked list.
    // inserts a new node at the end of the list.
    public void insert(int new_data) {
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

    // Method that will print the linked list.
    void printList() {
        Node node = head;
        StringJoiner joiner = new StringJoiner("->");
        while (node != null) {
            joiner.add(String.valueOf(node.data));
            node = node.next;
        }
        if (isUnique)
            System.out.println(joiner.toString() + " :List Already Unique");
        else
            System.out.println(joiner.toString());
    }

    private void insertAndRemoveDuplicates(int arr[]) {
        isUnique = true; // Reset for each test case
        head = null;
        for (int i = 0; i < arr.length; i++) {
            insert(arr[i]);
        }
        removeDuplicates();
        printList();
    }

    public static void main(String[] args) {
        RemoveDuplicatesSinglyLL removeDuplicatesSinglyLL = new RemoveDuplicatesSinglyLL();
        removeDuplicatesSinglyLL.insertAndRemoveDuplicates(new int[]{1, 1, 1, 1, 2, 3, 4, 4, 5, 6});
        removeDuplicatesSinglyLL.insertAndRemoveDuplicates(new int[]{1, 2, 3, 4, 5});
        removeDuplicatesSinglyLL.insertAndRemoveDuplicates(new int[]{0, 0, 0, 0, 0, 0, 0});
        removeDuplicatesSinglyLL.insertAndRemoveDuplicates(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }
}
