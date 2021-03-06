package com.algo.datastructures.fenwicktree;
// TODO: Test data for range queries tat does start from  index 0 ex range [3,7], get freq at particular point ex, range [4,4]
/***
 * Fenwick tree implemetnation. Same code as in geeksforgeek.
 * https://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
 * Fenwick tree solves below problem
 * We have an array arr[0 . . . n-1]. We would like to
 * 1 Compute the sum of the first i elements.
 * 2 Modify the value of a specified element of the array arr[i] = x where 0 <= i <= n-
 *
 * Time complexity:
 * To construct tree from te given array: O(N log N).
 * Compute the sum of the first i elements - O(1)
 * Modify the value of a specified element of the array - O( Log N)
 * Space complexity: O(N)
 * Video solution: https://www.youtube.com/watch?v=CWDQJGaN1gY&t=943s (Tusar Roy)
 */


public class FenwickTreeImpl {

    // Max tree size
    final static int MAX = 1000;

    static int BITree[] = new int[MAX];

    /* n --> No. of elements present in input array.
    BITree[0..n] --> Array that represents Binary
                    Indexed Tree.
    arr[0..n-1] --> Input array for which prefix sum
                    is evaluated. */

    // Returns sum of arr[0..index]. This function
    // assumes that the array is preprocessed and
    // partial sums of array elements are stored
    // in BITree[].
    int getSum(int index) {
        int sum = 0; // Iniialize result

        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse ancestors of BITree[index]
        while (index > 0) {
            // Add current element of BITree
            // to sum
            sum += BITree[index];

            // Move index to parent node in
            // getSum View
            index -= index & (-index);
        }
        return sum;
    }

    // Updates a node in Binary Index Tree (BITree)
    // at given index in BITree. The given value
    // 'val' is added to BITree[i] and all of
    // its ancestors in tree.
    public static void updateBIT(int n, int index,
                                 int val) {
        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse all ancestors and add 'val'
        while (index <= n) {
            // Add 'val' to current node of BIT Tree
            BITree[index] += val;

            // Update index to that of parent
            // in update View
            index += index & (-index);
        }
    }

    /* Function to construct fenwick tree
    from given array.*/
    void constructBITree(int arr[], int n) {
        // Initialize BITree[] as 0
        for (int i = 1; i <= n; i++)
            BITree[i] = 0;

        // Store the actual values in BITree[]
        // using update()
        for (int i = 0; i < n; i++)
            updateBIT(n, i, arr[i]);
    }

    // Main function
    public static void main(String args[]) {
      /*  int freq[] = {2, 1, 1, 3, 2, 3,
                4, 5, 6, 7, 8, 9};*/
        int freq[] = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
        int n = freq.length;
        FenwickTreeImpl tree = new FenwickTreeImpl();

        // Build fenwick tree from given array
        tree.constructBITree(freq, n);

        System.out.println("Sum of elements in arr[0..5]" +
                " is " + tree.getSum(5));

        // Let use test the update operation
        freq[3] += 6;

        // Update BIT for above change in arr[]
        updateBIT(n, 3, 6);

        // Find sum after the value is updated
        System.out.println("Sum of elements in arr[0..5]" +
                " after update is " + tree.getSum(5));
    }
}
