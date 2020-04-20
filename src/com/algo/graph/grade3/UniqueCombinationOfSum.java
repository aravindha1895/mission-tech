package com.algo.graph.grade3;
/**
 * You are given an array of distinct positive integers of random order and a target sum.
 * You have to print all the unique combinations of array elements such that the sum of each
 * unique combination is equal to the given target sum. The same number can be used any number of times
 * in the combination.
 * <p>
 * Note:
 * <p>
 * All the elements of the combination should be printed in ascending order.
 * <p>
 * All the unique combinations should be printed in the ascending order as follows:
 * <p>
 * If there are two combinations,
 * then print the combination whose first element is less than the others.
 * If both the combinations have the same first element, then compare the second element and so on.
 * Example:
 * Input
 * 1 2 3 4
 * Target: 4
 * Output
 * 1 1 1 1
 * 1 1 2
 * 1 3
 * 2 2
 * Explanation:
 * From the input, we can get the number of the array as {1, 2, 3, 4} and the target sum as 4.
 * 1+1+1+1 = 4, which is the target sum
 * 1+1+2 = 4, which is the target sum
 * 1+3 = 4, which is the target sum
 * 2+2 = 4, which is the target sum
 */

import java.util.*;

public class UniqueCombinationOfSum {
    public static void main(String arg[]) {

        System.out.println("Array [4 ,7 ,2 ,6 ,3] | Target sum: 10");
        printUniqueCombinations(new int[]{4, 7, 2, 6, 3}, 10);

        System.out.println("Array [2, 4, 6] | Target sum: 7");
        printUniqueCombinations(new int[]{2, 4, 6}, 7);

        System.out.println("Array [2, 3, 1] | Target sum: 4");
        printUniqueCombinations(new int[]{2, 3, 1}, 4);

    }

    private static void printUniqueCombinations(int[] inputNumbers, int targetSum) {
        // Write your code here
        Set<List<Integer>> result = new TreeSet<List<Integer>>((List<Integer> l1, List<Integer> l2) -> {
            List<Integer> temp1 = new LinkedList(l1);
            List<Integer> temp2 = new LinkedList(l2);
            Collections.sort(temp1);
            Collections.sort(temp2);
            if (temp1.equals(temp2))
                return 0;
            else {
                int i = 0, j = 0;
                // If size of list are equal, then list will smaller lexicographical order should come first.
                while (i < l1.size() && j < l2.size()) {
                    if (l1.get(i) == l2.get(j)) {
                        i++;
                        j++;
                        continue;
                    } else if (l1.get(i) > l2.get(j)) return 1;
                    else return -1;
                }
                return 1;
            }
        });
        findCombo(inputNumbers, 0, targetSum, new ArrayList<Integer>(), result);
        if (result.size() == 0) {
            System.out.println("No Combination");
        }
        for (List<Integer> lists : result) {
            for (Integer num : lists) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void findCombo(int[] inputNumbers, int index, int targetSum, List<Integer> curr, Set<List<Integer>> result) {
        if (targetSum == 0) {
            List<Integer> temp1 = new LinkedList(curr);
            Collections.sort(temp1);
            result.add(temp1);
            return;
        }
        if (targetSum < 0) {
            return;
        }
        for (int i = index; i < inputNumbers.length; i++) {
            curr.add(inputNumbers[i]);
            // Subtract current value with target sum and recurse
            findCombo(inputNumbers, index, targetSum - inputNumbers[i], curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}
