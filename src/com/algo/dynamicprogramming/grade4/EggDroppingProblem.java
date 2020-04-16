package com.algo.dynamicprogramming.grade4;

/**
 * Full problem statement: https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
 * Suppose that we wish to know which stories in a 36-story building are safe to drop eggs from,
 * and which will cause the eggs to break on landing. We make a few assumptions:
 * <p>
 * …..An egg that survives a fall can be used again.
 * …..A broken egg must be discarded.
 * …..The effect of a fall is the same for all eggs.
 * …..If an egg breaks when dropped, then it would break if dropped from a higher floor.
 * …..If an egg survives a fall then it would survive a shorter fall.
 * …..It is not ruled out that the first-floor windows break eggs, nor is it ruled out that the 36th-floor do not cause an egg to break.
 * <p>
 * If only one egg is available and we wish to be sure of obtaining the right result,
 * the experiment can be carried out in only one way. Drop the egg from the first-floor window;
 * if it survives, drop it from the second floor window. Continue upward until it breaks.
 * In the worst case, this method may require 36 droppings. Suppose 2 eggs are available.
 * What is the least number of egg-droppings that is guaranteed to work in all cases?
 * The problem is not actually to find the critical floor,
 * but merely to decide floors from which eggs should be dropped so that total number of trials are minimized.
 * Video link: https://www.youtube.com/watch?v=3hcaVyX00_4&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=13
 *
 * Time Complexity: O(nk^2)
 * Auxiliary Space: O(nk)
 * n= No. of eggs, k= No. of floors
 */
public class EggDroppingProblem {

    private static void calculateNumberOfAttempts(int floors, int eggs) {

        /*
         * Row: Eggs
         * Column: Floors
         * */
        int[][] attempts = new int[eggs + 1][floors + 1];
        /* If we have 0 floors, min attempt we can make is also 0 */
        for (int i = 0; i < attempts.length; i++)
            attempts[i][0] = 0;
        for (int i = 0; i < attempts[0].length; i++) {
            /* If we have 0 eggs, min attempt we can make is also 0 */
            attempts[0][i] = 0;
            /* If we have only 1 egg, min attempt that is needed is same as number of floors */
            attempts[1][i] = i;
        }
        for (int i = 2; i < attempts.length; i++) {
            for (int j = 1; j < attempts[i].length; j++) {
                // Number of eggs is more than floor, then take value from top
                if (i > j)
                    attempts[i][j] = attempts[i - 1][j];
                else {
                    int cost = Integer.MAX_VALUE;
                    /* Simulate the egg dropping from eac floor from floor 1 to j */
                    for (int k = 1; k <= j; k++) {
                        /*
                         * If the egg breaks we have 1 less floor and 1 less egg to work with => attempts[i-1][k-1]
                         * If the egg does not break, then we have same number of eggs and k minus floor that we are working
                         * on now (Simulation floor) to work with (i.e) We go up until k
                         * */
                        cost = Math.min(cost, 1 + Math.max(attempts[i - 1][k - 1], attempts[i][j - k]));
                        attempts[i][j] = cost;
                    }
                }
            }
        }
        System.out.println("The dynamic programming matrix is ");
        System.out.print("     ");
        for (int i = 0; i < attempts[0].length; i++) {
            System.out.print(i + " ");
        }
        for (int i = 0; i < attempts.length; i++) {
            System.out.println();
            for (int j = 0; j < attempts[i].length; j++) {
                if (j == 0)
                    System.out.print(i + " -> ");
                System.out.print(attempts[i][j] + " ");
            }
        }
        // Last row last column
        System.out.println("\nMin attempts needed  = " + attempts[eggs][floors]);
    }

    public static void main(String[] args) {
        int floors = 8;
        int eggs = 4;
        calculateNumberOfAttempts(floors, eggs);
        calculateNumberOfAttempts(6, 2);
    }

}
