package com.algo.array;
/**
 * https://www.geeksforgeeks.org/find-duplicates-constant-array-elements-0-n-1-o1-space/
 * Given a constant array of n elements which contains elements from 1 to n-1,
 * with any of these numbers appearing any number of times. Find any one of these repeating numbers
 * in O(n) and using only constant memory space.
 * Examples:
 * Input : arr[] = {1, 2, 3, 4, 5, 6, 3}
 * Output : 3
 * Here is an approach that is based on Floyd’s cycle finding algorithm. We use this to detect loop in a linked list.
 * The idea is to consider array items as linked list nodes. Any particular index is pointing to the value at that index.
 * And you will see that there is loop as shown in the image below- In case of duplicate, two indexes will have same
 * value and they will form a cycle just like in the image given below.
 * Linked list formed for above example would be :
 * 1->2->3->3->5->6
 * So we can find the entry point of cycle in the linked list and that will be our duplicate element.
 * We maintain two pointers fast and slow
 * For each step fast will move to the index that is equal to arr[arr[fast]](two jumps at a time)
 * and slow will move to the index arr[slow](one step at a time)
 * When fast==slow that means now we are in a cycle.
 * Fast and slow will meet in a circle and the entry point of that circle will be the duplicate element.
 * Now we need to find entry point so we will start with fast=0 and visit one step at a time for both fast and slow.
 * When fast==slow that will be entry point.
 * Return the entry point.
 * Time Complexity : O(N)
 * Auxiliary Space : O(1)
 * */
public class DuplicatesInReadOnlyArray {
    private static void printDuplicate(int a[]) {
        int slow = a[0];
        int fast = a[a[0]];
        /* Loop to enter in the cycle. Loop is similar to a circle and slow and fast ptr meet at
         * some point in circle indicating a loop */
        while (slow != fast) {
            // move one step for slow
            slow = a[slow];
            // move two step for fast
            fast = a[a[fast]];
        }
        fast = 0;
        /* To detect entry point of the circle */
        while (slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }
        System.out.println(slow);
    }

    public static void main(String[] args) {
        printDuplicate(new int[]{3, 4, 1, 4, 1});
        printDuplicate(new int[]{2, 1, 3, 4, 1});
        printDuplicate(new int[]{1, 2, 3, 4, 5, 6, 3});
    }
}
