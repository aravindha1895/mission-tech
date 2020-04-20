package com.algo.graph.grade3;

/**
 * You will be given a square matrix representing a maze, the source is considered as the upper leftmost entry
 * in the matrix and the destination is considered as the lower rightmost entry of the matrix.
 * You have to find the path from source to destination.
 * <p>
 * Constraint: You can move in only two directions in the matrix, that is rightwards and downwards.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * The matrix representing the maze is a square matrix of order ‘n’, and each entry of the matrix is
 * either integer 1 or 0.
 * <p>
 * 0 indicates that this cell can be used in the path to traverse from source to destination,
 * and 1 indicates that this cell of the matrix cannot be used as in path to traverse from source to destination.
 * In other words, 1 indicates a wall in the maze, which cannot be crossed over.
 * <p>
 * <p>
 * <p>
 * Print a matrix representing the path from source to the destination such that each entry used as path should
 * be ‘Y’ and the remaining should be ‘N’.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 0 0 1 1
 * 0 0 0 0
 * 0 1 1 1
 * 0 0 0 0
 * <p>
 * Output:
 * Y N N N
 * Y N N N
 * Y N N N
 * Y Y Y Y
 * <p>
 * Time complexity: O(n^2)
 * Space complexity: O(n^2)
 */
public class PathInMaze {
    public static void main(String[] args) {
        PathInMaze pathInMaze = new PathInMaze();

        int matrix1[][] = {{0, 0, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}};

        int matrix2[][] = {{0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0}};

        int matrix3[][] = {{0, 0, 1, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}};
        pathInMaze.printPatInMaze(matrix1);
        pathInMaze.printPatInMaze(matrix2);
        pathInMaze.printPatInMaze(matrix3);
    }

    private void printPatInMaze(int[][] inputMatrix) {
        int n = inputMatrix.length;
        char[][] pathMatrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pathMatrix[i][j] = 'N';
            }
        }
        findPath(inputMatrix, pathMatrix, 0, 0, n);
        // If the dest in N it means we cannot reach destination
        if (pathMatrix[n - 1][n - 1] == 'N')
            System.out.println("No Path");
        else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(pathMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    // input[row][col] == 0 represents there is a way. path[row][col] == 'N' means we have not visited that cell yet
    private boolean isSafe(int[][] input, char[][] path, int row, int col, int n) {
        if (row >= 0 && row < n && col >= 0 && col < n && input[row][col] == 0 && path[row][col] == 'N')
            return true;
        return false;
    }

    private boolean findPath(int[][] input, char[][] path, int row, int col, int n) {
        path[row][col] = 'Y';
        if (row == n - 1 && col == n - 1) {
            return true;
        }
        boolean isPathFound = false;
        // Below 2 if representing direction we move can be interchanged without affecting business logic.
        // Move in vertical direction (row+1)
        if (isSafe(input, path, row + 1, col, n))
            isPathFound = findPath(input, path, row + 1, col, n);

        // Move horizontally only if we cannot reach destination moving in vertical direction
        if (!isPathFound && isSafe(input, path, row, col + 1, n))
            isPathFound = findPath(input, path, row, col + 1, n);


        // If path is not found in current route, make everything in that route 'N'
        if (!isPathFound)
            path[row][col] = 'N';

        return isPathFound;

    }
}
