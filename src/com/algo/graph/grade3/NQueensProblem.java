package com.algo.graph.grade3;

/**
 * You are given an integer ‘K’ representing the size of the KxK chessboard.
 * You have to place K queens on the KxK chessboard in such a way that no two queens can attack each other
 * (i.e., no two queens should be placed in the same row, or same column or any diagonal from the current cell
 * on the chessboard)
 * <p>
 * After placing the queens, print the matrix of 0s and 1s of size KxK.
 * the matrix cell with value 1 represents the block on the chessboard where the queen is placed.
 * Note:
 * There can be multiple solutions for the given KxK chessboard and K queens. You have to print any one of them.
 * <p>
 * Example:
 * Input:
 * 4
 * Output:
 * 0 0 1 0
 * 1 0 0 0
 * 0 0 0 1
 * 0 1 0 0
 * <p>
 * Time complexity: O(N*N)
 * Space complexity: O(N*N)
 */
public class NQueensProblem {
    int noOfQueens;

    private boolean isSafe(int matrix[][], int row, int col) {
        if (row == 0) return true;
        // Main diagonal
        int sum = row + col;
        // Other diagonal
        int diff = row - col;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < noOfQueens; j++) {
                // Check if any other queen is attacking from  diagonals and vertically
                if ((i + j == sum || i - j == diff || j == col) && matrix[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    private boolean kQueen(int[][] result, int row) {
        boolean isSafeFound = false;
        for (int j = 0; j < noOfQueens; j++) {
            if (isSafe(result, row, j)) {
                // Place queen in this cell
                result[row][j] = 1;
                isSafeFound = true;
            }
            // If we can place queen in this row, then check for subsequent rows.
            // If subsequent rows return false, ten backtrack and continue this for loop to find alternate position to place queen
            if (isSafeFound && row + 1 < noOfQueens && !kQueen(result, row + 1)) {
                isSafeFound = false;
                // Make current cell 0 because, if we place queen here, then subsequent rows cannot be filled.
                result[row][j] = 0;
            }
            if (isSafeFound) break;
        }
        return isSafeFound;
    }

    public void printNoOfQueens() {
        int[][] result = new int[noOfQueens][noOfQueens];
        kQueen(result, 0);
        boolean isSolutionFound = false;
        for (int i = 0; i < noOfQueens; i++) {
            for (int j = 0; j < noOfQueens; j++) {
                System.out.print(result[i][j] + " ");
                // If there is no solution then entire result matrix will be 0.
                if (result[i][j] == 1)
                    isSolutionFound = true;
            }
            System.out.println();
        }
        if (!isSolutionFound)
            System.out.println("No solution found");
        System.out.println();
    }

    public static void main(String[] args) {
        NQueensProblem nQueensProblem = new NQueensProblem();
        // 3*3 board
        nQueensProblem.noOfQueens = 3;
        nQueensProblem.printNoOfQueens();

        // 4*4 board
        nQueensProblem.noOfQueens = 4;
        nQueensProblem.printNoOfQueens();

        // 5*5 board
        nQueensProblem.noOfQueens = 5;
        nQueensProblem.printNoOfQueens();
    }
}
