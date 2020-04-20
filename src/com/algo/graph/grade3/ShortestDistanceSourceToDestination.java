package com.algo.graph.grade3;
/**
 * You are given a matrix of 0s and 1s. You are also given the source and the destination cells of the matrix.
 * You have to print the shortest distance between the source and the destination.
 * <p>
 * Note:
 * <p>
 * Only cells with ‘0’ can be used in the path from source to destination. You should not use cells with ‘1’ in the path.
 * <p>
 * The path from source to the destination should traverse through the adjacent cells
 * horizontally and vertically in both the directions but not diagonally.
 * <p>
 * For the MxN matrix, the index start from (0,0) and end at (M-1, N-1).
 * <p>
 * <p>
 * <p>
 * Example:
 * Input:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * Source: (1, 0)
 * Destination: (0, 1)
 * Output: 2
 * <p>
 * Time complexity: O(N*M)
 * Space complexity: O(N*M)
 */

import java.util.LinkedList;
import java.util.Queue;

class ShortestDistanceQueueNode {
    int i;
    int j;
    int distance;

    public ShortestDistanceQueueNode(int i, int j, int d) {
        this.i = i;
        this.j = j;
        this.distance = d;
    }
}

public class ShortestDistanceSourceToDestination {

    public static void main(String[] args) {
        ShortestDistanceSourceToDestination shortestDistanceSourceToDestination = new ShortestDistanceSourceToDestination();
        int matrix1[][] = {{0, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}};
        shortestDistanceSourceToDestination.printMinDistance(matrix1, 0, 0, 3, 0);


        int matrix2[][] = {{0, 0, 0},
                {0, 1, 1},
                {0, 1, 0}};
        shortestDistanceSourceToDestination.printMinDistance(matrix2, 0, 0, 2, 2);

        int matrix3[][] = {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};
        shortestDistanceSourceToDestination.printMinDistance(matrix3, 1, 0, 0, 1);

    }

    private void printMinDistance(int[][] inputMatrix, int srcIndicesX, int srcIndicesY, int destIndicesX, int destIndicesY) {
        int noOfRows = inputMatrix.length;
        int noOfCols = inputMatrix[0].length;
        boolean visited[][] = new boolean[noOfRows][noOfCols];
        int d = shortestDist(inputMatrix, srcIndicesX, srcIndicesY, destIndicesX, destIndicesY, visited);
        if (d == -1) {
            System.out.println("No Shortest Distance");
        } else {
            System.out.println(d);
        }
    }

    // inputMatrix[r][c] == 0 indicates presence of path
    public boolean isSafe(int[][] inputMatrix, int r, int c) {
        if (r >= 0 && r < inputMatrix.length && c >= 0 && c < inputMatrix[0].length && inputMatrix[r][c] == 0)
            return true;
        return false;
    }

    public int shortestDist(int[][] inputMatrix, int i, int j, int x, int y, boolean[][] visited) {
        // horizontal and vertical movement calculate helper
        int[] rows = {0, 0, 1, -1};
        int[] cols = {-1, 1, 0, 0};

        // Add source node to queue
        Queue<ShortestDistanceQueueNode> queue = new LinkedList();
        queue.add(new ShortestDistanceQueueNode(i, j, 0));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            ShortestDistanceQueueNode curr = queue.remove();
            // If we reach destination then return the distance
            if (curr.i == x && curr.j == y)
                return curr.distance;
            // Move in horizontal and vertical direction
            for (int k = 0; k < 4; k++) {
                // bounds and visited check
                if (isSafe(inputMatrix, curr.i + rows[k], curr.j + cols[k]) && !visited[curr.i + rows[k]][curr.j + cols[k]]) {
                    queue.add(new ShortestDistanceQueueNode(curr.i + rows[k], curr.j + cols[k], curr.distance + 1));
                    visited[curr.i + rows[k]][curr.j + cols[k]] = true;
                }
            }
        }
        // If we reach this point then there is no path
        return -1;
    }
}
