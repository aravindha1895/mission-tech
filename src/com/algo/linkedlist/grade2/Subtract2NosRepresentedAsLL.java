package com.algo.linkedlist.grade2;

/**
 * Two positive numbers are taken as input in two different singly linked list.
 * The most significant digit is the first node, and the least significant digit is the last node of the linked list.
 * You need to perform algorithmic operations on the linked lists and return the output in a different linked list.
 * To perform the algorithmic operations, you are not allowed to reverse the linked list or use any other data structure.
 * Time Complexity: O(n)
 * Space Complexity: O(n) -> Due to function call stack used in recursion
 */
public class Subtract2NosRepresentedAsLL {
    Node head1, head2;
    boolean borrow;

    /* A class that creates a basic structure of a node in linked list */

    static class Node {
        int data;
        Node next;

        // Constructor to create a new node
        Node(int d) {
            data = d;
            next = null;
        }
    }


    /* to subtract 99 from 100 we need to pad one zero at the beginning of 99 i.e. 100-099.
    The method will perform the same function in case length of numbers are different*/
    Node zeroPadding(Node sNode, int diff) {
        // Write a code to pad zeroes when the length of the numbers is not same
        // Write a code to pad zeroes when the length of the numbers is not same
        Node head = sNode;
        int i = 1;
        while (i <= diff) {
            Node padding = new Node(0);
            padding.next = head;
            head = padding;
            i++;
        }
        return head;
    }


    Node recursiveSubtract(Node l1, Node l2) {
        //Write a code that will take care of the borrow and return a list that contains a result
        //Write a code that will take care of the borrow and return a list that contains a result
        if (l1 == null && l2 == null)
            return null;
        Node head = recursiveSubtract(l1.next, l2.next);

        int d1 = l1.data;
        int d2 = l2.data;
        int diff = 0;
        if (borrow == true) d1--;
        if (d1 < d2) {
            //Just a school subtraction if it has borrow
            diff = d1 + 10 - d2;
            borrow = true;
        } else {
            diff = d1 - d2;
            borrow = false;
        }

        if (head == null)
            head = new Node(diff);
        else {
            /* Similar to insert first in LL */
            Node newNode = new Node(diff);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    /* The method subtracts two linked lists and returns the linked list which shall have the subtracted result. */
    Node subtractLinkedList(int length1, int length2, Node h1, Node h2) {
        // When both of the lists are empty.
        if (h1 == null && h2 == null) {
            System.out.println("lists are empty");

        }
        Node lNode = null, sNode = null;

        // initializing two nodes with heads of the two input linked lists
        Node temp1 = h1;
        Node temp2 = h2;

        /* Compare the length of the two linked lists. Padd the smaller list with zeroes */
        /* For subtraction, both should have the same length */

        if (length1 != length2) {
            if (length1 > length2) {
                lNode = h1;
                sNode = h2;
            } else {
                lNode = h2;
                sNode = h1;
            }

            sNode = zeroPadding(sNode, Math.abs(length1 - length2));
        } else {
   /*If the length of both the lists is equal then find the greater number of the two in magnitude.
   For example 3->4->5->null is greater than 3->4->0->null.  For this, we will sequentially compare data value in each of the nodes*/

            while (h1 != null && h2 != null) {
                if (h1.data != h2.data) {
                    if (h1.data > h2.data) {
                        lNode = temp1;
                        sNode = temp2;
                    } else {
                        lNode = temp2;
                        sNode = temp1;
                    }
                    break;
                }
                h1 = h1.next;
                h2 = h2.next;
            }
        }

  /*After making the length of the two linked list equal and finding the greater of the two number now recursiveSubtract
  will be called to perform the actual subtraction and return a resultant linked list*/
        borrow = false;
        return recursiveSubtract(lNode, sNode);
    }

    // method to insert a number in linked list 1
    public void push_list1(int new_data) {
        Node temp = head1;
        Node new_node = new Node(new_data);
        if (head1 == null) {
            head1 = new_node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;
        }
    }

    //method to insert a number in linked list 2
    public void push_list2(int new_data) {
        Node temp = head2;
        Node new_node = new Node(new_data);
        if (head2 == null) {
            head2 = new_node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;
        }
    }


    // function to display the linked list
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private void insertToList1(int arr[]) {
        head1 = null;
        for (int i = 0; i < arr.length; i++) {
            push_list1(arr[i]);
        }
    }

    private void insertToList2(int arr[]) {
        head2 = null;
        for (int i = 0; i < arr.length; i++) {
            push_list2(arr[i]);
        }
    }

    public static void main(String[] args) {
        Subtract2NosRepresentedAsLL obj = new Subtract2NosRepresentedAsLL();
        obj.insertToList1(new int[]{0, 0, 0, 0});
        obj.insertToList2(new int[]{0, 0, 0});
        // Params: length(list1), length(list2), head(list1), head(list2)
        Node subtract = obj.subtractLinkedList(4, 3, obj.head1, obj.head2);
        printList(subtract);


        obj.insertToList1(new int[]{9, 2, 1, 7, 9, 2, 0, 1, 4, 5});
        obj.insertToList2(new int[]{7, 8, 9, 9, 9, 9, 9, 2, 1, 0, 4, 2, 5, 6, 2});
        // Params: length(list1), length(list2), head(list1), head(list2)
        subtract = obj.subtractLinkedList(10, 15, obj.head1, obj.head2);
        printList(subtract);

        obj.insertToList1(new int[]{1, 0, 0, 0});
        obj.insertToList2(new int[]{9, 9, 9});
        // Params: length(list1), length(list2), head(list1), head(list2)
        subtract = obj.subtractLinkedList(4, 3, obj.head1, obj.head2);
        printList(subtract);

        obj.insertToList1(new int[]{7, 5, 2});
        obj.insertToList2(new int[]{4, 9, 9});
        // Params: length(list1), length(list2), head(list1), head(list2)
        subtract = obj.subtractLinkedList(3, 3, obj.head1, obj.head2);
        printList(subtract);

    }
}
