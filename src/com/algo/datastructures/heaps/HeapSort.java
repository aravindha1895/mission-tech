package com.algo.datastructures.heaps;


/**
 * Given an unsorted array, sort the array using heap sort.
 * <p>
 * For this first we have to heapify the given array so that heap properties are followed and then sort the array
 * by swapping te root one by one to last and reducing the size of te heap.
 * <p>
 * Hint: To sort te array in decreasing order, build min heap, To sort te array in increasing order, build max heap.
 * <p>
 * Time Complexity: O(n log n).
 * <p>
 * Heapify looks like it will take O (n log n) time but theory says that it takes only o(n). Sorting even in worst case
 * happens in O (n log n) only.
 * SO o(n)+O(n log n) = O(n log n) because (n log n)>O(n).
 */

import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {


    public static int leftChild(int i) {
        return (2 * i + 1);
    }

    public static int rightChild(int i) {
        return (2 * i + 2);
    }

    public static void heapSort(int[] arr, int n) {
        // build heap
        int i = (arr.length) / 2;
        while (i >= 0) {
            heapify(arr, i, arr.length);
            i--;
        }
        /* Sort operation. One by one bring the root ele to last and reduce the heap size. When 1st root ele is brought
         to last , then new root will contain 2nd smallest ele due to heapify operation. So on repeating this process
         we get sorted array*/
        for (i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            //Swap first and last element
            heapify(arr, 0, i);
        }
    }

    /* To maintain heap property */
    private static void heapify(int[] arr, int i, int n) {

        int l = leftChild(i);
        int r = rightChild(i);

        int smallest = i;

        if (l < n && arr[l] < arr[smallest]) {
            smallest = l;
        }

        if (r < n && arr[r] < arr[smallest]) {
            smallest = r;
        }

        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, smallest, n);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int[] getArrayInput(int size, Scanner scanner) {
        int array[] = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arr[];

        arr = new int[]{55, 23, 19, 14, 9, 7, 10, 12};
        heapSort(arr, arr.length);
        System.out.println("Case 1: " + Arrays.toString(arr));

        arr = new int[]{32, -12, 15, 178, 0, 111};
        heapSort(arr, arr.length);
        System.out.println("Case 2: " + Arrays.toString(arr));

        arr = new int[]{1, 10, 2, 6, 9, 13, 15, 178, 0, 111};
        heapSort(arr, arr.length);
        System.out.println("Case 3: " + Arrays.toString(arr));

        arr = new int[]{-17, -221, 111, 32, -12, 15, 178, 0, 111};
        heapSort(arr, arr.length);
        System.out.println("Case 4: " + Arrays.toString(arr));

    }
}
