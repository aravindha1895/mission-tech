package com.algo.graph.grade3;

/**
 * You will be given a 2D array which consists of only 0’s and 1’s.
 * You need to find the number of islands present in that array. A group of connected 1’s forms an island.
 * Two 1’s are said to be connected if they are vertically, horizontally or diagonally adjacent to each other.
 * <p>
 * Example:
 * Input:
 * arr[][] = {1, 1, 0, 1, 1, 0
 * 1, 0, 0, 0, 0, 1
 * 0, 0, 1, 1, 0, 0
 * 1, 0, 0, 0, 0, 1
 * 0, 0, 1, 0, 1, 0}
 * Output: 6
 * Logic: Number of islands is the number of DFS done. One DFS from a node will give all connected from that node.
 * And then we do DFS from another node tat is not yet visited. After doing DFS from all unvisited nodes,
 * Number of islands = No of DFS done.
 * <p>
 * Time complexity: O(n*n) because of adjacency matrix representation, can be done in O(V+E) for adj list representation
 * Space complexity : O(V).
 */
public class NumberOfIslands {
    public static void main(String args[]) {

        int arr1[][] = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        int arr2[][] = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};

        int arr3[][] = {{1, 1, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 0}};

        System.out.println(numberOfIslands(arr1));
        System.out.println(numberOfIslands(arr2));
        System.out.println(numberOfIslands(arr3));
    }

    // Method to find the number of islands
    static int numberOfIslands(int[][] arr) {
        // Write your code here
        int n = arr.length;
        int m = arr[0].length;
        int[][] visited = new int[n][m];
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && visited[i][j] == 0) {
                    findIsland(arr, i, j, n, m, visited);
                    count++;
                }
            }
        return count;
    }

    /* Check index bounds */
    static boolean isSafe(int i, int j, int n, int m) {
        if (i >= 0 && i < n && j >= 0 && j < m) return true;
        return false;
    }

    /* This is conventional DFS of graph for adjacency matrix representation */
    static void findIsland(int[][] arr, int i, int j, int n, int m, int visited[][]) {
        visited[i][j] = 1;
        /* Every rowNr, colNbr addition gives us the adjacent index from that node in all 8 directions.
         * rowNr[0]+colNbr[0] => left, rowNr[1]+colNbr[1] => Top and so on...
         * */
        int rowNbr[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int colNbr[] = {-1, 0, 1, -1, 1, -1, 0, 1};
        // For all adjacent nodes
        for (int k = 0; k < 8; k++) {
            int tempI = i + rowNbr[k];
            int tempJ = j + colNbr[k];
            // If within bounds && If there is a edge && If te node is not visited.
            if (isSafe(tempI, tempJ, n, m) && arr[tempI][tempJ] == 1 && visited[tempI][tempJ] != 1)
                findIsland(arr, tempI, tempJ, n, m, visited);
        }
    }
}
