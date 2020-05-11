package com.algo.array.grade3;

/**
 * You will be given an array of n natural numbers of range (1-n) each number occurring only once.
 * You need to find the minimum number of swaps required to make it a sorted array.
 * We can swap the elements which are adjacent.
 * Example:
 * arr[] = {1, 2, 5, 3, 4}
 * Output:
 * 2
 * (Another variant: given that each number can be shifted at most two positions right)
 * Because you can shift element 5 two positions right and make it a sorted array.
 * arr[] = {1, 2, 6, 3, 4, 5}
 * Output:
 * Not Possible
 * Because even if you shift element 6 two positions right you can’t make it a sorted array.
 * <p>
 * This problem is replica is inversion count of array. Inversion count is the number of inversion or
 * swaps to make the array sorted. Hint: Use merge sort and count inversions.
 * Time complexity O(n*logn).
 * Space complexity: O(N)
 */
public class InversionCount {
    private static int minSwapsToGetPermutedArray(int[] arr, int k) {
        int N = arr.length;
        /* Code to loop over all elements to check Invalid  permutation condition  */
        /* Use the below loop only if upper bound is given on inversion which apply to
         * each individual elements not overall inversion count (Ex. k=2), each element
         * can be shifted by utmost k positions, but overall inversion count can be > k*/
        // If k=0, let us assume there is not upper bound on inversion count and not perform this check
        if (k != 0) {
            for (int i = 0; i < N; i++) {
            /* If an element is at distance more than 'k'
            from its actual position then it is not
            possible to reach permuted array just
            by swapping with k positions left elements
            so returning -1 */
                if ((arr[i] - 1) - i > k) {
                    return -1;
                }
            }
        }
        /* If permuted array is not Invalid, then number
        of Inversion in array will be our final answer */
        int numOfInversion = _mergeSort(arr, 0, N - 1);
        return numOfInversion;
    }

    /* This function merges two sorted arrays and returns inversion count in the arrays.*/
    private static int merge(int arr[], int left, int mid, int right) {
        /* Left might start from any position, to avoid OutOfBound, initialize wit arr length*/
        int temp[] = new int[arr.length];
        int inv_count = 0;
        int i = left;
        /* i is index for left subarray*/
        int j = mid;
        /* j is index for right subarray*/
        int k = left;
        /* k is index for resultant merged subarray*/
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inv_count = inv_count + (mid - i);
            }
        }
        /* Copy the remaining elements of left subarray (if there are any) to temp*/
        while (i <= mid - 1) {
            temp[k++] = arr[i++];
        }
        /* Copy the remaining elements of right subarray (if there are any) to temp*/
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        /* Copy back the merged elements to original array*/
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
        return inv_count;
    }

    /* An auxiliary recursive function that sorts the input array and returns the number of inversions
     in the array. */
    private static int _mergeSort(int arr[], int left, int right) {
        int mid, inv_count = 0;
        if (right > left) {
            /* Divide the array into two parts and  call _mergeSortAndCountInv() for each of the parts */
            mid = (right + left) / 2;

            /* Inversion count will be sum of inversions  in left-part, right-part and number of inversions
            in merging */
            inv_count = _mergeSort(arr, left, mid);
            inv_count += _mergeSort(arr, mid + 1, right);

            /* Merge the two parts*/
            inv_count += merge(arr, left, mid + 1, right);
        }
        return inv_count;
    }


    /* This function sorts the input array and returns the number of inversions in the array */
    private static int mergeSort(int arr[], int array_size) {
        return _mergeSort(arr, 0, array_size - 1);
    }

    public static void main(String[] args) {
        System.out.println(minSwapsToGetPermutedArray(new int[]{1, 2, 5, 3, 4, 6, 9, 7, 8, 12, 10, 11, 14, 13}, 2));

        // Output -1 as 7 is displaced by more than 2 position from its original position
        System.out.println(minSwapsToGetPermutedArray(new int[]{1, 2, 7, 3, 4, 8, 5, 6}, 2));
        System.out.println(minSwapsToGetPermutedArray(new int[]{1, 2, 7, 3, 4, 8, 5, 6}, 4));
        System.out.println(minSwapsToGetPermutedArray(new int[]{1, 2, 7, 3, 4, 8, 5, 6}, 0));

        System.out.println(minSwapsToGetPermutedArray(new int[]{1, 2, 5, 3, 4}, 0));


    }

}
