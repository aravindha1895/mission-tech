package com.algo.linkedlist.grade1;

/**
 * Given a singly linked list along with reference to the head of the list.
 * Your task is to find the middle element of the given linked list.
 * Time complexity - O(n)
 * Space complexity - O(Constant)
 */
public class MiddleElementOfLL {
    class Node {
        int data;
        Node next;

        Node(int value) {
            data = value;
            next = null;
        }
    }

    //initialising head node 'head' to null
    public static Node head = null;

    public static Node last;

    //method to add the node at the end of the linked list
    public void add(int value) {

        Node newNode = new Node(value);
        //if the linked list is empty then
        if (head == null) {
            head = new Node(value);
            return;
        }

        last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        return;
    }

    //Method to print the middle element of the linked list
    /* 2 pointer concept used. fast pointer moves 2 steps and slow pointer moves 1 step.
     * Finally after fast ptr reach end of list, slow ptr is te middle element */
    public static void printMiddleElement(Node head) {
        //write your code here
        Node slow, fast;
        slow = head;
        fast = head.next;
        if (head == null || head.next == null) {
            System.out.println("middle element does not exist");
            return;
        }
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        System.out.println(slow.data);

    }

    private void insertAndFindMiddleElement(int arr[]) {
        head = null;
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
        printMiddleElement(head);
    }

    public static void main(String[] args) {
        MiddleElementOfLL middleElementOfLL = new MiddleElementOfLL();
        middleElementOfLL.insertAndFindMiddleElement(new int[]{1, 2, 3, 4, 5});
        middleElementOfLL.insertAndFindMiddleElement(new int[]{0});
        middleElementOfLL.insertAndFindMiddleElement(new int[]{1, 7});
        middleElementOfLL.insertAndFindMiddleElement(new int[]{1, 9, 5, 6});
    }
}
