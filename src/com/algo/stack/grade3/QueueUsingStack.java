package com.algo.stack.grade3;
/**
 * Implement the queue using single or multiple stacks and demonstrate the basic functions of queues,
 * i.e., ENQUEUE and DEQUEUE.
 * Time Complexity:
 * Enqueue: O(n)
 * Dequeue: O(1)
 */

import java.util.Arrays;
import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> stack = new Stack<Integer>();

    //The basic push and pop method of a stack are used to create enqueue and dequeue function of a queue
    public void enQueue(int newData) {
        // Add new element at bottom of the stack as queue is FIFO
        if (stack.isEmpty()) {
            stack.push(newData);
        } else {
            int top = stack.pop();
            enQueue(newData);
            stack.push(top);
        }
    }

    // Function to deQueue an item from queue

    public void deQueue() {
        if (!stack.isEmpty()) {
            System.out.println(stack.pop());
        } else {
            System.out.println("no elements left");
        }
    }

    public static void main(String[] args) {
        QueueUsingStack queueUsingStack = new QueueUsingStack();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).forEach(element -> queueUsingStack.enQueue(element));
        System.out.println(queueUsingStack.stack.toString());
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
        queueUsingStack.deQueue();
    }
}
