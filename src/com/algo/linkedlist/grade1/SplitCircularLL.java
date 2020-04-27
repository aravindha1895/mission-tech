package com.algo.linkedlist.grade1;

import java.util.StringJoiner;

public class SplitCircularLL {
    static Node head;
    static Node h1, h2;

    static class Node {
        int data;
        Node next, prev;

        Node(int d) {
            data = d;
            next = prev = null;
        }
    }

    /* Similar to find middle element except maintain additional variable to track lst node to make it to
     * point to head as this is a circular LL and change in If condition for circular LL */
    private void splitLL() {
        if (head == null) {
            System.out.println("list is empty");
            return;
        } else if (head.next == head) {
            System.out.println("invalid input");
            return;
        }
        Node slow, fast;
        slow = fast = head;
        fast = head.next;
        Node lastNode = fast;
        do {
            fast = fast.next;
            if (fast != head) {
                lastNode = fast;
                fast = fast.next;
                if (fast != head) lastNode = fast;
                if (fast != head) slow = slow.next;
            }
        } while (fast != head);
        h1 = head;
        h2 = slow.next;
        slow.next = h1;
        lastNode.next = h2;
    }

    public void push(int new_data) {
        Node temp = head;
        Node new_node = new Node(new_data);
        if (head == null) {
            head = new_node;
            new_node.next = head;
        } else {
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = new_node;
            new_node.next = head;
        }
    }

    //Method to print data value of nodes of the linked list
    private void print(Node temp) {
        Node node = temp;
        StringJoiner joiner = new StringJoiner("->");
        if (temp != null) {
            do {
                joiner.add(String.valueOf(node.data));
                node = node.next;
            } while (node != temp);
        }
        System.out.println(joiner.toString());
    }

    private void insertAndSplitLL(int arr[]) {
        head = null;
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        System.out.print("Before division: ");
        print(head);
        splitLL();
        System.out.print("After division- List1: ");
        print(h1);
        System.out.print("After division- List2: ");
        print(h2);
        System.out.println();
    }

    public static void main(String[] args) {
        SplitCircularLL splitCircularLL = new SplitCircularLL();
        splitCircularLL.insertAndSplitLL(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        splitCircularLL.insertAndSplitLL(new int[]{89, 41, 23, 56, 12, 7, 22});
        splitCircularLL.insertAndSplitLL(new int[]{-1, -21, -11, -45, -78, 0, 54, 2, 1});
    }
}
