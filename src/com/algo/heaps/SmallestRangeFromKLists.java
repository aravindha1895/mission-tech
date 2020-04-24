package com.algo.heaps;

/**
 * You will be given the ‘k’ sorted lists of different size and you have to print the smallest range such
 * that there is at least one element from each list in the range.
 * Example:
 * If the given lists are as follows
 * {1, 2, 3}
 * {4, 5, 6, 7}
 * {8, 9}
 * Then the output will be 3 - 8
 * Because the 3 - 8 is the smallest range in which at least one element from each list is present in the range.
 * Time complexity - O(M*N) -> M list * N elements
 * Space complexity - O(M)
 */

public class SmallestRangeFromKLists {

    // A min heap node
    static class Node {
        int ele; // The element to be stored
        int i;     // index of the list from which
        // the element is taken
        int j;     // index of the next element
        // to be picked from list

        Node(int a, int b, int c) {
            this.ele = a;
            this.i = b;
            this.j = c;
        }
    }

    // A class for Min Heap
    static class MinHeap {
        Node[] harr; // array of elements in heap
        int size;     // size of min heap

        // Constructor: creates a min heap
        // of given size
        MinHeap(Node[] arr, int size) {
            this.harr = arr;
            this.size = size;
            int i = (size - 1) / 2;
            while (i >= 0) {
                MinHeapify(i);
                i--;
            }
        }

        // to get index of left child
        // of node at index i
        int left(int i) {
            return 2 * i + 1;
        }

        // to get index of right child
        // of node at index i
        int right(int i) {
            return 2 * i + 2;
        }

        // to heapify a subtree with
        // root at given index
        void MinHeapify(int i) {
            int l = left(i);
            int r = right(i);
            int small = i;
            if (l < size &&
                    harr[l].ele < harr[i].ele)
                small = l;
            if (r < size &&
                    harr[r].ele < harr[small].ele)
                small = r;
            if (small != i) {
                swap(small, i);
                MinHeapify(small);
            }
        }

        void swap(int i, int j) {
            Node temp = harr[i];
            harr[i] = harr[j];
            harr[j] = temp;
        }

        // to get the root
        Node getMin() {
            return harr[0];
        }

        // to replace root with new node x
        // and heapify() new root
        void replaceMin(Node x) {
            harr[0] = x;
            MinHeapify(0);
        }
    }

    /* This function takes an k sorted lists in the form of 2D array as an argument.
    It finds out smallest range that includes elements from each of the k lists.*/
    private static void findSmallestRange(int[][] arr, int k) {
        int range = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int start = -1, end = -1;

        int n = arr[0].length;

        // Create a min heap with k heap nodes.
        // Every heap node has first element of an list
        Node[] arr1 = new Node[k];
        for (int i = 0; i < k; i++) {
            Node node = new Node(arr[i][0], i, 1);
            arr1[i] = node;

            // store max element
            max = Math.max(max, node.ele);
        }

        // Create the heap
        MinHeap mh = new MinHeap(arr1, k);

        /* Now one by one get the minimum element from min heap and replace it with next element of its list*/
        while (true) {
            // Get the minimum element and store it in output
            Node root = mh.getMin();

            // update min
            min = root.ele;

            // update range
            if (range > max - min + 1) {
                range = max - min + 1;
                start = min;
                end = max;
            }

            /* Find the next element that will replace current root of heap.
            The next element belongs to same list as the current root.*/
            if (root.j < arr[root.i].length) {
                root.ele = arr[root.i][root.j];
                root.j++;

                // update max element
                if (root.ele > max)
                    max = root.ele;
            } else
                break;    // break if we have reached
            // end of any list

            // Replace root with next element of list
            mh.replaceMin(root);
        }
        System.out.println("The smallest range is [" +
                start + " " + end + "]");
    }

    // Driver Code
    public static void main(String[] args) {
        int arr[][] = {{4, 7, 9, 12, 15},
                {0, 8, 10, 14, 20},
                {6, 12, 16, 30, 50}};
        findSmallestRange(arr, arr.length);

        int[][] arr1 = {{1, 2, 3, 4, 55},
                {11, 12, 13, 14, 15},
                {6, 7, 8, 9, 70},
        };
        findSmallestRange(arr1, arr1.length);

        int arr2[][] = {
                {1},
                {2, 3},
                {4, 5, 6, 7},
                {8, 9}};
        findSmallestRange(arr2, arr2.length);
    }
}
