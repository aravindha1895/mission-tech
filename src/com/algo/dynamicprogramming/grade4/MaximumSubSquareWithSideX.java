package com.algo.dynamicprogramming.grade4;

/**
 * Find maximum sub square in a matrix made up of Xs and Os such that all four sides of sub square are Xs.
 * It does not matter what is inside the sub square. All 4 sides should be made up entirely of Xs
 * <p>
 * e.g
 * 0 0 0 0 0 X         0,0  0,0  0,0  0,0  0,0  1,1
 * 0 X 0 X X X         0,0  1,1  0,0  1,1  1,2  2,3
 * 0 X 0 X 0 X         0,0  2,1  0,0  2,1  0,0  3,1
 * 0 X X X X X         0,0  3,1  1,2  3,3  1,4  4,5
 * 0 0 0 0 0 0         0,0  0,0  0,0  0,0  0,0  0,0
 * <p>
 * Output of above program should be 3
 * <p>
 * Solution
 * Have another matrix which is capable of holding 2 values horizontal and vertical.
 * vertical stores how far vertically you can see Xs. horizontal stores how far horizontally you can see Xs.
 * Once this matrix is build look for biggest sub square by getting min of horizontal and vertical at each point and checking
 * if sub square can be formed from value min to 1.
 * <p>
 * Test cases:
 * Matrix entirely made up of Xs
 * Matrix entirely made up of Os
 * Matrix with Xs and Os but maximum sub square is length 1
 * <p>
 * Time Complexity: Constructing matrix -> O(n^2), Finding max square  O(n^3)
 * Auxiliary Space: O(n^2)
 */
class Cell {
    int horizontal;
    int vertical;

    public Cell(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        return "(" + horizontal + ", " + vertical + ")";
    }
}

public class MaximumSubSquareWithSideX {
    private int findSubSquare(char[][] input) {
        Cell[][] tracker = new Cell[input.length][input[0].length];
        // Fill 1st row
        for (int i = 0; i < input.length; i++) {
            if (input[i][0] == 'X' || input[i][0] == 'x') {
                if (i == 0)
                    tracker[i][0] = new Cell(1, 1);
                else
                    // 1st row so horizontal is always 1 and vertical is prev row vertical plus 1
                    tracker[i][0] = new Cell(1, 1 + tracker[i - 1][0].vertical);
            } else {
                tracker[i][0] = new Cell(0, 0);
            }
        }
        // Fill 1st column
        for (int j = 1; j < input[0].length; j++) {
            if (input[0][j] == 'X' || input[0][j] == 'x') {
                // 1st column so vertical is always 1 and horizontal is prev column horizontal plus 1
                tracker[0][j] = new Cell(1 + tracker[0][j - 1].horizontal, 1);
            } else {
                tracker[0][j] = new Cell(0, 0);
            }
        }
        for (int i = 1; i < input.length; i++) {
            for (int j = 1; j < input[i].length; j++) {
                if (input[i][j] == 'X' || input[i][j] == 'x') {
                    // Update current cell by adding so far what we know from left (horizontal count) and top(vertical count) cells
                    tracker[i][j] = new Cell(1 + tracker[i][j - 1].horizontal, 1 + tracker[i - 1][j].vertical);
                } else
                    tracker[i][j] = new Cell(0, 0);
            }
        }

        System.out.println("Dynamic programming table visualization");
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                System.out.print(tracker[i][j].toString());
            }
            System.out.println();
        }

        int max = 1;
        for (int i = input.length - 1; i >= 0; i--) {
            for (int j = input[i].length - 1; j >= 0; j--) {
                // This if is just for optimization. Dont contribute to business logic
                // We have this condition as we have already initialized max as 1
                if (tracker[i][j].vertical == 0 || tracker[i][j].vertical == 1 || tracker[i][j].horizontal == 1) {
                    continue;
                }
                // We are looking at square so we get how far we can go left and top by equal distance, so we take min
                int min = Math.min(tracker[i][j].horizontal, tracker[i][j].vertical);
                int k;
                // Loop until k>1 because max is already initialized as 1
                for (k = min; k > 1; k--) {
                    /* Our aim is to find max square so break out as soon as this condition is satisfied */
                    if (tracker[i][j - k + 1].vertical >= k && tracker[i - k + 1][j].horizontal >= k) {
                        break;
                    }
                }
                // Update  max
                max = Math.max(max, k);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] input = {{'X', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'O'}};

        char[][] input1 = {{'O', 'O', 'O', 'O', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'X', 'X'},
                {'O', 'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O'},
        };

        char[][] input2 = {{'O', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'O', 'X', 'X', 'X', 'O'},
        };
        char[][] input3 = {{'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'X', 'X', 'X'}
        };

        MaximumSubSquareWithSideX ss = new MaximumSubSquareWithSideX();
        System.out.println("Test case 1");
        System.out.println("Max side possible = " + ss.findSubSquare(input));
        System.out.println("Test case 2");
        System.out.println("Max side possible = " + ss.findSubSquare(input1));
        System.out.println("Test case 3");
        System.out.println("Max side possible = " + ss.findSubSquare(input2));
        System.out.println("Test case 4");
        System.out.println("Max side possible = " + ss.findSubSquare(input3));
    }

}
