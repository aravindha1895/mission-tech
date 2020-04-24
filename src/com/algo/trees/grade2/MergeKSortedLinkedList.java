package com.algo.trees.grade2;
/**
 * You will be given K sorted linked list and you have to merge these linked lists into one linked list
 * such that the resultant linked list is also sorted.
 * Note that all the k linked lists are of the same size.
 * Example:
 * <p>
 * Below are the three sorted linked lists and you have to merge them to only one linked list such that the resultant linked list is also sorted.
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 * 6 -> 7 -> 8 -> 9 -> 10 -> null
 * 11 -> 12 -> 13 -> 14 -> 15 -> null
 * <p>
 * After merging, the linked list looks like
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12 -> 13 -> 14 -> 15 -> null
 * Time complexity - O(nk log k)
 * Space complexity - O(k)
 */

import java.util.ArrayList;

class Node {
    int data;
    Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
        next = null;
    }
};

public class MergeKSortedLinkedList {
    public Node mergeLists(Node h1, Node h2) {
        Node result = new Node(0); // dummy node
        Node pointer = result;
        while (h1 != null && h2 != null) {
            if (h1.data <= h2.data) {
                pointer.next = new Node(h1.data);
                h1 = h1.next;
            } else {
                pointer.next = new Node(h2.data);
                h2 = h2.next;
            }
            pointer = pointer.next;
        }
        while (h1 != null) {
            pointer.next = new Node(h1.data);
            h1 = h1.next;
            pointer = pointer.next;
        }
        while (h2 != null) {
            pointer.next = new Node(h2.data);
            h2 = h2.next;
            pointer = pointer.next;
        }
        result = result.next; // remove dummy node created at the start of the function
        return result;
    }

    public Node mergeKLists(ArrayList<Node> a) {

        int n = a.size() - 1;
        int k = 0;
        if (n == 0)
            return a.get(0); // n==0 because we decrement actual size by 1. So instead of n==1 use n==0 or use a.size()==1
        /* K start from 0 so for each n lists, merged result is found in first k<=n/2 indexes */
        while (n > 1) {
            k = 0;
            for (int i = 0; i <= n; i = i + 2) {
                if (i + 1 <= n)
                    a.set(k, mergeLists(a.get(i), a.get(i + 1)));
                else
                    a.set(k, mergeLists(a.get(i), null));
                k++;
            }

            // Now first n/2 position have merged list. Repeat until we get overall merged list at a[0]
            n = n / 2;

        }
        // For even sized list this is not needed, even if it done it wont affect result as we just doing sorting
        return mergeLists(a.get(0), a.get(1));
    }

    private void printResult(Node result) {
        while (result != null) {
            System.out.print(" " + result.data);
            result = result.next;
        }
        System.out.println();
    }

    /* Form a list ad insert to array list */
    private void addData(ArrayList<Node> arr, int[] data) {
        Node root = new Node(data[0]);
        Node temp = root;
        for (int i = 1; i < data.length; i++) {
            temp.next = new Node(data[i]);
            temp = temp.next;
        }
        arr.add(root);
    }

    public static void main(String[] args) {
        MergeKSortedLinkedList mergeKSortedLinkedList = new MergeKSortedLinkedList();
        ArrayList<Node> arr = new ArrayList<>();

        mergeKSortedLinkedList.addData(arr, new int[]{4, 5, 7, 8}); // List 1
        mergeKSortedLinkedList.addData(arr, new int[]{1, 2, 10, 11}); // List 2
        mergeKSortedLinkedList.addData(arr, new int[]{0, 3, 6, 9}); // List 3
        mergeKSortedLinkedList.printResult(mergeKSortedLinkedList.mergeKLists(arr));
    }
}
