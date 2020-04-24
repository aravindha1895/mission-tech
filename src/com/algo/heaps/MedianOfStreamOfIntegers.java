package com.algo.heaps;
/**
 * Given a stream of integers, print median
 * Median is the middle element of the series which are arranged in an order.
 * Median of the series of ‘n’ elements; where n is odd, is the middle element of the series.
 * <p>
 * Median of the series of ‘n’ elements; where n is even, is the average of the middle two elements
 * i.e., n/2 and n/2 +1 elements of the series.
 * You will be given the stream of running integers and you have to print the median of the numbers present so far
 * and keep on printing the medians as soon as the new number arrives.
 * If the stream of numbers is 2, 6, 1, 5, 8 …
 * When the 1st element is read, the median is 2 of (2)
 * When the 2nd element is read, the median is 4 of (2 ,6)
 * When the 3rd element is read, the median is 2 of (2, 6, 1)
 * When the 4th element is read, the median is 3.5 of (2, 6, 1, 5)
 * When the 5th element is read, the median is 5 of (2, 6, 1, 5, 8)
 * <p>
 * And so on.
 * Approach:
 * Suggested Answer
 * <p>
 * Use two heaps one min heap and another max heap.
 * Max-heap is used to store all the elements which are in the first half of sorted integers
 * Min-heap is used to store all the elements which are in the second half of sorted integers
 * Note: All the elements in the first half are less than all the elements in the second half
 * Assume the initial value of the median as 0.
 * When the new element arrives
 * If two heaps are of the same size then,
 * If that new element is greater than the previous median then insert that element into the min-heap. Now the new median will be the peek element of min-heap.
 * If the new element is less than the previous median then insert that element into the max-heap. Now the new median will be the peek element of max-heap.
 * If the size of min heap is less than max heap then,
 * If the previous median is greater than the new element then remove the top element of max-heap and add that to the min-heap and then add the new element to the max-heap.
 * Else add the new element to the min-heap
 * Now the new median is average of the top elements of both min and max heaps
 * If the size of min heap is greater than max heap then,
 * If the previous median is less than the new element then remove the top element of min-heap and add that to the max-heap and then add the new element to the min-heap.
 * Else add the new element to the max-heap
 * Now the new median is average of the top elements of both min and max heaps
 * <p>
 * Time complexity: N*log(N)
 * Space complexity: O(N)
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfStreamOfIntegers {
    public static float printMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int ele) {
        if (ele > minHeap.peek()) minHeap.add(ele);
        else
            maxHeap.add(ele);
        int balanceFactor = maxHeap.size() - minHeap.size();

        /* If one side is over balanced then sift element from heap wit more ele to other */
        if (balanceFactor < -1) {
            maxHeap.add(minHeap.poll());
        } else if (balanceFactor > 1) {
            minHeap.add(maxHeap.poll());
        }
        /*re calculate balance factor after above balancing*/
        balanceFactor = maxHeap.size() - minHeap.size();
        float median = 0f;
        if (balanceFactor == 0) { // both heap same size hence median is avg of root of 2 heaps
            median = ((float) (minHeap.peek() + maxHeap.peek())) / 2f;
        } else if (balanceFactor < 0) // Right min heap has more elements so median is root of min heap
            median = (float) minHeap.peek();
        else    // Left max heap has more elements so median is root of max heap
            median = (float) maxHeap.peek();
        return median;
    }

    public static void main(String[] args) {
        //write your code here
        System.out.println("Test case 1");
        printMedian(new int[]{1, 2, 5, 6, 8});

        System.out.println("Test case 2");
        printMedian(new int[]{2, 6, 1, 5, 8, 10});
    }

    private static void printMedian(int[] array) {
        int n = array.length;
        /* Create 2 heaps one for each half of the array */
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        float median = 0;
        minHeap.add(array[0]);
        median = (float) minHeap.peek();
        System.out.println(median);
        for (int i = 1; i < n; i++) {
            median = printMedian(minHeap, maxHeap, array[i]);
            System.out.println(median);
        }
        System.out.println();
    }
}
