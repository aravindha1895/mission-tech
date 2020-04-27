package com.algo.linkedlist.grade1;

/**
 * Check fo a palindrome tat is represented in form of doubly linked list
 * Time complexity - O(n)
 * Space complexity - O(Constant)
 */
public class PalindromeDoublyLL {
    Node head;

    class Node {
        int data;
        Node next;
        Node prev;

        Node(int d) {
            data = d;
        }
    }

    public void push(int new_data) {
        Node temp = head;
        Node new_Node = new Node(new_data);
        new_Node.prev = null;
        if (head == null) {
            head = new_Node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_Node;
            new_Node.prev = temp;
        }
    }

    public void checkPalindrome() {
        Node temp = head;
        Node last = null;
        /* Make last point to last node */
        while (temp.next != null)
            temp = temp.next;
        last = temp;
        Node first = head;
        boolean isPalindrome = true;
        while (first != last) {
            if (first.data != last.data) {
                isPalindrome = false;
            }
            first = first.next;
            /* For even size string, this statement detects cross over and breaks out of loop */
            if (first == last) break;
            // No cross over yet, so
            last = last.prev;
            /* For odd size string, this statement detects cross over and breaks out of loop */
            if (first == last) break;
        }
        System.out.println(isPalindrome);
    }

    private void insertAndCheckPalindrome(String input) {
        head = null;
        for (int i = 0; i < input.length(); i++) {
            push(input.charAt(i));
        }
        checkPalindrome();
    }

    public static void main(String[] args) {
        PalindromeDoublyLL palindromeDoublyLL = new PalindromeDoublyLL();
        palindromeDoublyLL.insertAndCheckPalindrome("aaa");
        palindromeDoublyLL.insertAndCheckPalindrome("abcd");
        palindromeDoublyLL.insertAndCheckPalindrome("1234567");
        palindromeDoublyLL.insertAndCheckPalindrome("AabaA");

    }
}
