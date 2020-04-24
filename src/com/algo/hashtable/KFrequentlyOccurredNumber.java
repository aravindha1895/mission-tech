package com.algo.hashtable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * ou will be given an array of integers and an integer k.
 * You have to print the k most frequently occurring integers in the given array of integers.
 * The frequency of an integer in the array is nothing but the number of times it appeared in the array.
 * Note:
 * The integer with higher frequency should be printed first.
 * If two integers have the same frequency, then the larger integer of the two should be printed first.
 * Example:
 * Input:
 * 1 2 3 2 2 3 4 1 5
 * 4
 * Output:
 * 2 3 1 5
 * Given below is a more time-efficient approach to solve the problem in O(k log m)
 * time by using O(m) space, where ‘m’ is the number of distinct integers in the given array.
 * Get the frequency of each integer of the array and store the integer and its frequency to the HashMap.
 * Get each entry of the HashMap and push it to the max heap.
 * (Note: The top of the max heap should contain the integer with the highest frequency.
 * If there are two integers with the same frequency, then priority should be given to the greater integer.)
 * This step takes O(m) space and O(m) time to store ‘m’ distinct elements and their frequency to the max heap.
 * Now, remove the top k elements from the max heap and print them.
 * To remove an element from the max heap of size ‘m’, it takes O(log m) time.
 * Since you need to remove k elements from the max heap, it takes O(k log m) time.
 */
class Numbers {
    public int num;
    public int freq;

    public Numbers(int n, int f) {
        num = n;
        freq = f;
    }
}

public class KFrequentlyOccurredNumber {
    private void printKFrequentlyOccurredNumber(int array[], int k) {
        int n = array.length;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < n; i++) {
            // If already present, increase frequency by 1
            if (map.containsKey(array[i])) {
                map.put(array[i], map.get(array[i]) + 1);
            } else {
                map.put(array[i], 1); // 1st time occurrence
            }
        }
      /* Comparator sorts based on frequency of occurrence (Max freq comes first).
      If same freq, highest number gets priority */
        PriorityQueue<Numbers> repeated = new PriorityQueue<>(new Comparator<Numbers>() {
            @Override
            public int compare(Numbers o1, Numbers o2) {
                if (o1.freq == o2.freq) {
                    return o1.num >= o2.num ? -1 : 1;
                } else {
                    return o1.freq > o2.freq ? -1 : 1;
                }
            }
        });
        // Add every element in map to priority queue
        for (Integer i : map.keySet()) {
            repeated.add(new Numbers(i, map.get(i)));
        }
        // Print k repeated elements
        for (int i = 0; i < k; i++) {
            System.out.print(repeated.remove().num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        KFrequentlyOccurredNumber kFrequentlyOccurredNumber = new KFrequentlyOccurredNumber();
        kFrequentlyOccurredNumber.printKFrequentlyOccurredNumber(new int[]{1, 2, 3, 4, 5, 6, 1, 2}, 5);
        kFrequentlyOccurredNumber.printKFrequentlyOccurredNumber(new int[]{1, 2, 3, 2, 2, 3, 4, 1}, 4);
        kFrequentlyOccurredNumber.printKFrequentlyOccurredNumber(new int[]{1, 2, 3, 2, 2, 3, 4, 1, 5}, 5);
    }
}
