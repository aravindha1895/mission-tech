package com.algo.linkedlist.grade2;

/**
 * Given a sequence of integers as data values of the nodes in a singly linked list,
 * you have to sort the numbers in a non-decreasing manner such as:
 * Approach: Do merge sort in linked list
 * Time Complexity: O(N log N)
 * Space complexity: O(N)
 * Complexities are same as merge sort done in array.
 */
public class SortingSinglyLL {

    //initializing the head of the class
    Node head = null;

    // a generic class to create new nodes
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /* Returns middle element of linked list */
    private Node findMiddle(Node h) {
        //Base case
        if (h == null)
            return h;
        Node fastptr = h.next;
        Node slowptr = h;

        while (fastptr != null) {
            fastptr = fastptr.next;
            if (fastptr != null) {
                slowptr = slowptr.next;
                fastptr = fastptr.next;
            }
        }
        return slowptr;
    }

    private Node mergeSort(Node h) {
        // Write your code here
        if (h == null || h.next == null)
            return h;
        Node middle = findMiddle(h);
        /* Splitting list into 2 halves */
        Node nextOfMiddle = middle.next;
        middle.next = null;
        // Recursive split left
        Node left = mergeSort(h);
        // Recursive split right
        Node right = mergeSort(nextOfMiddle);
        // Merge split values
        Node result = merge(left, right);
        return result;
    }

    private Node merge(Node list1, Node list2) {

        Node result = new Node(0);// dummy node
        Node pointer = result;
        if (list1 == null && list2 == null)
            return null;
        else if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                pointer.next = new Node(list1.data);
                pointer = pointer.next;
                list1 = list1.next;
            } else {
                pointer.next = new Node(list2.data);
                pointer = pointer.next;
                list2 = list2.next;
            }
        }
        // Remaining elements of list 1
        while (list1 != null) {
            pointer.next = new Node(list1.data);
            pointer = pointer.next;
            list1 = list1.next;
        }
        // Remaining elements of list 2
        while (list2 != null) {
            pointer.next = new Node(list2.data);
            pointer = pointer.next;
            list2 = list2.next;
        }

        return result.next;// Remove dummy node
    }


    // code to insert nodes in a linked list
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

    //method to print the linked list
    void print() {
        Node tnode = head;
        while (tnode != null) {
            System.out.print(tnode.data + " ");
            tnode = tnode.next;
        }
        System.out.println();
    }

    private void insertAndMergeSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        head = mergeSort(head);
        print();
    }

    public static void main(String[] args) {
        SortingSinglyLL sortingSinglyLL = new SortingSinglyLL();
        sortingSinglyLL.head = null;
        sortingSinglyLL.insertAndMergeSort(new int[]{5, 4, 1, 2, 9, 0, 2, 4});
        sortingSinglyLL.head = null;
        sortingSinglyLL.insertAndMergeSort(new int[]{-1, -4, 2, 3, -2, 0});
        sortingSinglyLL.head = null;
        sortingSinglyLL.insertAndMergeSort(new int[]{5, 6, 7, 8, 9});
        sortingSinglyLL.head = null;
        sortingSinglyLL.insertAndMergeSort(new int[]{9, 8, 7, 6, 5});
    }
}