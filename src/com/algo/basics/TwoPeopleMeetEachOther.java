package com.algo.basics;

/**
 * given starting points of two people p1 and p2 respectively and distance covered by two people in each jump
 * s1 and s2 respectively. You need to find whether they meet after the same number of jumps.
 * The starting points of the two persons should always be different.
 * Example:
 * Input:
 * p1: 6
 * s1: 3
 * p2: 8
 * s2: 2
 * Output: YES
 * Explanation:
 * Person1: 6->9->12
 * Person2: 8->10->12
 * They both will meet at point 12 after 2 jumps each.
 * Approach:
 * For the two people to  meet after the same number of jumps, the following conditions should be satisfied:
 * The speeds of two persons must be different as well
 * If initially, the first person is ahead of the second person, the speed of the second person should be
 * greater than the speed of the first person or else the second person can’t catch up to the first person ever or vice versa.
 * And if the difference of speeds divides the difference of initial starting points of two people.
 * Time complexity: O(1).
 * Space complexity: O(1)
 */
public class TwoPeopleMeetEachOther {
    private static void doesTwoPeopleMeet(int p1, int p2, int s1, int s2) {
        String res = p1 > p2 ? s2 > s1 && (p1 - p2) % (s2 - s1) == 0 ? "YES" : "NO" : s1 > s2 && (p2 - p1) % (s1 - s2) == 0 ? "YES" : "NO";
        System.out.println(res);
    }

    public static void main(String[] args) {
        doesTwoPeopleMeet(0, 5, 3, 4);
        doesTwoPeopleMeet(9, 6, 1, 3);
        doesTwoPeopleMeet(6, 8, 3, 2);
    }
}
