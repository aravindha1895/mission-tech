package com.algo.linkedlist.grade2;
/**
 * Given a Doubly Linked List which has data members sorted in ascending order.
 * Construct a Balanced Binary Search Tree which has same data members as the given Doubly Linked List.
 * The tree must be constructed in-place (No new node should be allocated for tree conversion)
 * Input: Doubly Linked List 1  2 3  4 5  6  7
 * Output: A Balanced BST
 *         4
 *       /   \
 *      2     6
 *    /  \   / \
 *   1   3  4   7
 *   Approach: https://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/ (Method 2)
 *   Time Complexity: O(n)
 *   Space Complexity: O(n) -> Due to function call stack used in recursion
 */
/*
* Another simple approach:
* Node linkedListToBST(Node head, int start, int end) {
if (start > end)
  return NULL;
  int mid = start + (end - start) / 2;
  Node temp = head;
  int i = 0;
  // This while condition will make the temp.next point to the middle node
  // and we will be able to access it. As you can see from the piece of code,
  //  we want to make the middle node as the root of our BST.
  while (i<mid && temp.next!=NULL) {
        temp = temp.next;
        i++;
 }
 Node root = new_Node Node(temp.data);
 root.left = linkedListToBST(head, start, mid - 1);
 root.right = linkedListToBST(head, mid + 1, end);
 return root;
}

* */
import com.algo.trees.BinaryTreeNode;
import com.algo.trees.BinaryTreePrinter;

public class DLLtoBST {
    /* head of the given doubly linked list*/
    static LLNode head;

    /* Basic class that will initialize a doubly linked from the data values given by the user. */

    class LLNode {
        int data;
        LLNode next, prev;

        LLNode(int d) {
            data = d;
            next = prev = null;
        }
    }


    /* This function counts the number of nodes in Linked List 
    and then calls sortedListToBSTRecur() to construct BST */
    private BinaryTreeNode sortedListToBST() {
        /*Count the number of nodes in Linked List */
        int n = countNodes(head);

        /* Construct BST */
        return sortedListToBSTRecur(n);
    }

    /* The main function that constructs balanced BST and 
       returns root of it. 
       n  --> No. of nodes in the Doubly Linked List */
    private BinaryTreeNode sortedListToBSTRecur(int n) {
        /* Base Case */
        if (n <= 0)
            return null;

        /* Recursively construct the left subtree */
        BinaryTreeNode left = sortedListToBSTRecur(n / 2); 
  
        /* head ref now refers to middle node,
           make middle node as root of BST*/
        BinaryTreeNode root = new BinaryTreeNode(head.data);

        // Set pointer to left subtree 
        root.left = left; 
  
        /* Change head pointer of Linked List for parent 
           recursive calls */
        head = head.next; 
  
        /* Recursively construct the right subtree and link it 
           with root. The number of nodes in right subtree  is 
           total nodes - nodes in left subtree - 1 (for root) */
        root.right = sortedListToBSTRecur(n - n / 2 - 1);

        return root;
    }

    public void push(int new_data) {
        LLNode temp = head;
        LLNode new_node = new LLNode(new_data);
        if (head == null) {
            head = new_node;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;
        }
    }

    private int countNodes(LLNode head) {
        int count = 0;
        LLNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }


    /* Function to print nodes in a given linked list */
    private void printList(LLNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    /* A utility function to print pre order traversal of BST */
    private void preOrder(BinaryTreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void insertAndConvertToBST(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            push(arr[i]);
        }
        BinaryTreeNode result = sortedListToBST();
        new BinaryTreePrinter(result).print(System.out);
        System.out.print("\nPre order: ");
        preOrder(result);
        System.out.println();
    }

    public static void main(String[] args) {

        DLLtoBST obj = new DLLtoBST();
        /* Convert List to BST */
        obj.insertAndConvertToBST(new int[]{101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114});
        obj.insertAndConvertToBST(new int[]{8, 9});
        obj.insertAndConvertToBST(new int[]{4});

    }

}
