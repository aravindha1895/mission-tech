package com.algo.stack.grade1;
/**
 * Given a stack, reverse te stack without any additional stack. We can use 2 recursive function but that is
 * time consuming, so below is a combination of iterative and recursive method.
 * Time complexity: O(N*N)
 * Space complexity: O(N) -> For function call stack in recursion
 */

import java.util.Stack;

public class StackReverseLoopAndRecurse {
    public static void print(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int x = stack.pop();
            System.out.print(x + " ");
            print(stack);
            stack.push(x);
        }
    }

    /* Pop n-1 elements from stack and insert te given value, then push popped elements again */
    public static void rearrangeStack(Stack<Integer> stack, int value, int n) {
        if (n < 0) return;
        if (n == 0) {
            stack.push(value);
        } else {
            int temp = stack.pop();
            rearrangeStack(stack, value, n - 1);
            stack.push(temp);
        }
    }

    public static void reverse(Stack<Integer> stack) {
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            int val = stack.pop();
            /*First time current element is inserted at last. For subsequent times,
            insert current element at last-i-1 (n-i-1) position as last i elements are already in order*/
            rearrangeStack(stack, val, n - i - 1);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("Before reversing:");
        print(stack);
        reverse(stack);
        System.out.println("\nAfter reversing:");
        print(stack);
    }
}
