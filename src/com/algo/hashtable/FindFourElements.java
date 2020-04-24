package com.algo.hashtable;

import java.util.*;

/**
 * You will be given an array of distinct positive integers, and you have to check whether there
 * exist any four integers (a, b, c and d) in the array such that a + b = c + d.
 * Example:
 * Input:
 * 1 2 6 7 10 13
 * Output:
 * True
 * Explanation:
 * The given array of elements is {1, 2, 6, 7, 10, 13}.
 * In this array, there are four integers (a, b, c and d) such that a + b = c + d.
 * They are 1, 7, 2 and 6, where 1 + 7 = 2 + 6. Therefore, this input produces 'true' output.
 * Variant 2:
 * You will be given an array of distinct integers, and you have to print all possible sets
 * of four integers of the array (a, b, c and d) such that a*b = c*d.
 * Variant 2 is discussed below.
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 */
class QuadrapleSet {
    private int a, b, c, d;

    public QuadrapleSet(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}

class Pair {
    private int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

public class FindFourElements {
    public static void main(String[] args) {
        findMatchingPair(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        findMatchingPair(new int[]{4, 5, 6, 7, 8, 1, 2, 3});
        findMatchingPair(new int[]{1, 2, 3, 4, 5});
        findMatchingPair(new int[]{1, 2, 3, 4, 7, 8});
    }

    private static void findMatchingPair(int[] array) {
        int n = array.length;
        Map<Integer, List<Pair>> map = new HashMap<>();
        Set<QuadrapleSet> quadrapleSetList = new TreeSet<>(new Comparator<QuadrapleSet>() {
            @Override
            /* Just to order Quadruple set, no impact on business logic */
            public int compare(QuadrapleSet a1, QuadrapleSet a2) {
                /*q1 (a1, b1, c1, d1) is less than quadruple q2 (a2, b2, c2, d2) */
                if (((a1.getA() == a2.getA()) && (a1.getB() == a2.getB()) && (a1.getC() == a2.getC()) && (a1.getD() == a2.getD()))
                        || ((a1.getA() == a2.getC()) && (a1.getB() == a2.getD()) && (a1.getC() == a2.getA()) && (a1.getD() == a2.getB()))
                ) {
                    return 0;
                }
                if ((a1.getA() < a2.getA()) ||
                        ((a1.getA() == a2.getA()) && (a1.getB() < a2.getB())) ||
                        ((a1.getA() == a2.getA()) && (a1.getB() == a2.getB()) && (a1.getC() < a2.getC())) ||
                        ((a1.getA() == a2.getA()) && (a1.getB() == a2.getB()) && (a1.getC() == a2.getC()) && (a1.getD() < a2.getD()))) {
                    return -1;
                }
                return 1;
            }
        });
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = array[i] * array[j];
                if (!map.containsKey(product)) {
                    map.put(product, new ArrayList<Pair>(Arrays.asList(new Pair(array[i], array[j]))));
                } else {
                    List<Pair> pairs = map.get(product);
                    for (Pair p : pairs) {
                        quadrapleSetList.add(new QuadrapleSet(p.getA(), p.getB(), array[i], array[j]));
                    }
                    pairs.add(new Pair(array[i], array[j]));
                    map.put(product, pairs);
                }
            }
        }
        if (quadrapleSetList.size() == 0) {
            System.out.println("Not Found");
        } else {
            for (QuadrapleSet set : quadrapleSetList) {
                System.out.println(set.getA() + " " + set.getB() + " " + set.getC() + " " + set.getD());
            }
        }
        System.out.println();
    }
}
