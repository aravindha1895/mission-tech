package com.algo.stack.grade1;
/**
 * Given a stack, find te kt largest element.
 * Approach:
 * TO find kt largest element, first we have to sort the stack. Decreasing order for kt largest and
 * Increasing order for kth smallest.
 * Time complexity: O(N^N) -> For sorting
 * Space complexity: O(N) -> Extra stack
 * */
import java.util.Stack;

public class KthLargestNumber {
    /* Function to sort the stack. To find kth largest number, first we have to sort the stack. */
    public static Stack<Integer> sortStack(Stack<Integer> input) {
        Stack<Integer> sortedStack = new Stack<>();
        while (!input.isEmpty()) {
            int value = input.pop();
            // Descending order. Push ele which is greater than current value to input stack
            while (!sortedStack.isEmpty() && sortedStack.peek() > value) {
                input.push(sortedStack.pop());
            }
            sortedStack.push(value); // Value is inserted in sorted order
        }
        return sortedStack;
    }

    public static void findKthLargestNum(Stack<Integer> sortedStack, int k) {
        /* To find kth largest element pop out k-1 elements first */
        while (k > 1) {
            sortedStack.pop();
            k--;
        }
        System.out.println(sortedStack.peek()); // kth largest element
    }

    public static void addToStackAndFindKthLargestElement(int arr[], int k) {
        Stack<Integer> inputStack = new Stack<Integer>();
        for (int i = 0; i < arr.length; i++) {
            inputStack.add(arr[i]);
        }
        Stack<Integer> temp = sortStack(inputStack);
        findKthLargestNum(temp, k);
    }

    public static void main(String[] args) {
        addToStackAndFindKthLargestElement(new int[]{1, 2, 3, 4, 5, 6}, 3);
        addToStackAndFindKthLargestElement(new int[]{-1, 4, -3, 7, 8, -8, -2, 5, 3, 10}, 4);
    }
}
