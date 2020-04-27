package com.algo.stack.grade1;
/**
 * Algorithm where you can use a single queue .
 * Time complexity (For below implementation):
 * O(n) -> For push.
 * O(1) for top() and pop() operation
 * We can make top() and pop() to have O(N) and push to have O(1) based on our needs
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> q = new LinkedList<Integer>();

    /* Push operation of stack using queue*/
    void push(int x) {
        q.add(x);
        int size = q.size();
        // Shift remaining elements except this element to the end of the queue
        for (int i = 0; i < size - 1; i++) {
            q.add(q.remove());
        }
    }

    /*Removes the top element of the stack*/
    private int pop() {
        if (q.size() == 0) {
            return -1;
        } else
            return q.remove();
    }


    /*Returns the element at the top of the stack */
    private int top() {
        if (q.size() == 0) {
            return -1;
        } else
            return q.peek();
    }

    public static void main(String[] args) {
        StackUsingQueue stackUsingQueue = new StackUsingQueue();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).forEach(ele -> stackUsingQueue.push(ele));
        System.out.println(stackUsingQueue.q.toString());
        int x = stackUsingQueue.top();
        System.out.println(x);
        stackUsingQueue.pop();
        x = stackUsingQueue.top();
        System.out.println(x);
        System.out.println(stackUsingQueue.pop());
        System.out.println(stackUsingQueue.pop());
        System.out.println(stackUsingQueue.pop());
        System.out.println(stackUsingQueue.pop());
        System.out.println(stackUsingQueue.pop());
        System.out.println(stackUsingQueue.pop()); // Is stack is empty it returns -1
    }


}
