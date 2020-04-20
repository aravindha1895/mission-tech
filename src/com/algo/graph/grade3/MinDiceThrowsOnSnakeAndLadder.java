package com.algo.graph.grade3;
/**
 * You are given a snake and ladder board of size MxN and you have to find the minimum number of dice throws
 * required to reach the destination from the source.
 * The source is the first block of the board and the destination is the MxN th block of the board.
 * <p>
 * Edge array is given to signify snakes and ladders.
 * edge[n1]=n2; signifies there is a ladder between n1 and n2 if n1<n2, snake if n1>n2.
 * <p>
 * Time complexity: O(N*M), N*M is the dimension of snake and ladder board.
 * Space complexity: O(N*M)
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SnakeAndLadderNode {
    int cell;
    int moves;

    SnakeAndLadderNode(int cell, int moves) {
        this.cell = cell;
        this.moves = moves;
    }
}

public class MinDiceThrowsOnSnakeAndLadder {
    public static void main(String[] args) {


        int noOfCells;
        noOfCells = 7 * 7; // N*M cells
        int edges[] = new int[noOfCells];
        Arrays.fill(edges, -1);
        System.out.println("Test case 1");
        // Ladders
        edges[4] = 7; // Means there is a ladder from 4 to 7
        edges[10] = 25;
        edges[19] = 28;
        edges[6] = 48;
        // Snakes
        edges[26] = 1; // Means there is a snake from 26 to 1
        edges[20] = 28;
        edges[16] = 3;
        edges[18] = 6;
        printMinMoves(noOfCells, edges);

        System.out.println("Test case 2");
        noOfCells = 6 * 5; // N*M cells
        edges = new int[noOfCells];
        Arrays.fill(edges, -1);
        // Ladders
        edges[2] = 21;
        edges[5] = 7;
        edges[9] = 23;
        edges[20] = 27;
        // Snakes
        edges[25] = 0;
        edges[17] = 3;
        edges[8] = 6;
        printMinMoves(noOfCells, edges);

        System.out.println("Test case 3");
        noOfCells = 4 * 5; // N*M cells
        edges = new int[noOfCells];
        Arrays.fill(edges, -1);
        // Ladders
        edges[2] = 13;
        edges[10] = 19;
        // Snakes
        edges[18] = 9;
        edges[16] = 3;
        printMinMoves(noOfCells, edges);
    }

    private static void printMinMoves(int noOfCells, int[] edges) {
        // Write your code here
        Queue<SnakeAndLadderNode> queue = new LinkedList<SnakeAndLadderNode>();
        List<Integer> visited = new LinkedList<Integer>();
        int noOfMoves = 0;
        visited.add(0);// coin is initially placed in 1st cell (index 0) and not outside the board
        queue.add(new SnakeAndLadderNode(0, 0));
        while (!queue.isEmpty()) {
            SnakeAndLadderNode curr = queue.remove();
            if (curr.cell == noOfCells - 1) // We have reached the end
            {
                noOfMoves = curr.moves;
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int cellToMove = curr.cell + i;
                if (cellToMove >= noOfCells) break;
                /* If edges[cellToMove] != -1, it means there is either snake or ladder from that cell.
                 * So push value corresponding to edges[cellToMove],
                 * else there is no snake / ladder from te cell, so push cell itself.
                 *  */
                if (edges[cellToMove] != -1 && !visited.contains(edges[cellToMove])) {
                    queue.add(new SnakeAndLadderNode(edges[cellToMove], curr.moves + 1));
                    visited.add(edges[cellToMove]);
                } else if (!visited.contains(cellToMove)) {
                    queue.add(new SnakeAndLadderNode(cellToMove, curr.moves + 1));
                    visited.add(cellToMove);
                }
            }
        }
        System.out.println("Min moves = " + noOfMoves);
    }
}
