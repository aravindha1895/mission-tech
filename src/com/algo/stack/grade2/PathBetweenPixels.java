package com.algo.stack.grade2;
/**
 * Given is the pixel matrix of a binary image,
 * which has only two colours: black (represented as 0) and white (represented as 1).
 * In this segment, you need to find out whether a path exists between the given
 * two sets of coordinates of the pixel matrix if you are only allowed to move along the 1s in the given matrix.
 * In the example above, there exists a path between the pixels at coordinates (0,0) and (4,3) as shown below:
 * Source   (0, 0) -------> ( 1, 0) -----> ( 2, 0) -----> (3, 0) -----> ( 4, 0) ----->
 * (4, 1) -------> (4, 2) ------> (4, 3) Destination
 * Note: You can only move in the four directions: left, right, up, and down.
 * Time complexity - O(n*m)
 * Space complexity - O(n*m)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathBetweenPixels {
    /* Basic structure of a node of a stack. 'move' will keep track of the direction in which you can move next.*/
    class Node {
        int x, y;
        int move;

        Node(int i, int j) {
            x = i;
            y = j;
            move = 0;
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node) o;
            return this.x == n.x && this.y == n.y;
        }


        @Override
        public int hashCode() {
            int hash = 7;
            hash = 17 * x * y;
            return hash;
        }
    }

    public boolean isSafe(int[][] input, int i, int j) {
        if (i >= 0 && j >= 0 && i < input.length && j < input[0].length && input[i][j] == 1)
            return true;
        else
            return false;
    }

    public boolean findPath(int n, int m, int[][] input) {

        int rows[] = {-1, 0, 0, 1};
        int cols[] = {0, 1, -1, 0};
        Stack<Node> stack = new Stack<Node>();
        List<Node> visited = new ArrayList<Node>();
        stack.push(new Node(0, 0));
        visited.add(new Node(0, 0));
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (curr.x == n - 1 && curr.y == m - 1) return true;
            for (int i = 0; i < 4; i++) {
                if (isSafe(input, curr.x + rows[i], curr.y + cols[i]) && !visited.contains(new Node(curr.x + rows[i], curr.y + cols[i]))) {
                    stack.push(new Node(curr.x + rows[i], curr.y + cols[i]));
                    visited.add(new Node(curr.x + rows[i], curr.y + cols[i]));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /* The code needs to find if there exists a path between (0,0) and (n,m).
         * Condition : You can move only along the ‘1’s
         * */
        PathBetweenPixels pathBetweenPixels = new PathBetweenPixels();
        System.out.println(!pathBetweenPixels.findPath(2, 3, new int[][]{{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        }) ? "Path not found" : "Path found");
        System.out.println(!pathBetweenPixels.findPath(2, 2, new int[][]{{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        }) ? "Path not found" : "Path found");
        System.out.println(!pathBetweenPixels.findPath(2, 2, new int[][]{{1, 1},
                {1, 1}}) ? "Path not found" : "Path found");
    }
}
