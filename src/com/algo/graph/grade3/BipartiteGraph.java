package com.algo.graph.grade3;
/**
 * A graph (directed or undirected) is bipartite iff the node set V can be partitioned into two disjoint sets – V1 and V2,
 * such that there are no edges between nodes in set V1 or in set V2.
 * <p>
 * Mathematically, this can be expressed as follows:
 * A graph G: (V,E) (may be directed or undirected) is bipartite iff the node set V can be partitioned into
 * two disjoint parts V_1, V_2 where
 * <p>
 * V_1 cap V_2 = emptyset and V_1 cup V_2 = V, and
 * <p>
 * any edge in the graph goes from a node in V_1 to a node in V_2 or vice-versa.
 * <p>
 * <p>
 * <p>
 * In other words, if the total number of nodes in a graph is divided into two independent sets, A and B,
 * such that for every edge (a, b), either ‘a’ belongs to set A and ‘b’ belongs to set B, or ‘b’
 * belongs to set A and ‘a’ belongs to set B, such a graph is called a bipartite graph.
 * <p>
 * Time complexity: O(n*n) because of adjacency matrix representation,  can be done in O(V+E) for adj list representation
 * Space complexity : O(V).
 */

import java.util.LinkedList;
import java.util.Queue;

class BipartiteQueueNode {
    int node;
    int colour;

    public BipartiteQueueNode(int n, int c) {
        this.node = n;
        this.colour = c;
    }
}

public class BipartiteGraph {
    public static void main(String[] args) {
        BipartiteGraph bipartiteGraph = new BipartiteGraph();


        // Adjacency matrix used to represent the graph
        int adjMatrix[][] = {{0, 1, 1, 0, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0}};

        // Write your code here.
        System.out.println(bipartiteGraph.isBipartiteGraph(adjMatrix));

        int adjMatrix1[][] = {{0, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}};

        System.out.println(bipartiteGraph.isBipartiteGraph(adjMatrix1));

        int adjMatrix2[][] = {{0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}};

        System.out.println(bipartiteGraph.isBipartiteGraph(adjMatrix2));


    }

    /* Here we use visited array itself to track colour in addition to track visited node.
     * If visited[node] = 0, ten it is unvisited.
     * If visited[node] = +1 or -1, then the node is visited and the colour is either +1 or -1
     *  */
    private boolean isBipartiteGraph(int[][] adjMatrix) {
        int j;
        int noOfNodes = adjMatrix.length;
        Queue<BipartiteQueueNode> queue = new LinkedList();
        int[] visited = new int[noOfNodes];
        // We indicate 2 colours by +1 and -1.
        int sign = 1;
        // Add source node with color +1
        queue.add(new BipartiteQueueNode(0, sign));
        visited[0] = sign;
        sign = sign * -1;
        while (!queue.isEmpty()) {
            BipartiteQueueNode currentNode = queue.remove();
            for (j = currentNode.node; j < noOfNodes; j++) {
                // NO edge
                if (adjMatrix[currentNode.node][j] == 0) continue;
                if (visited[j] == 0) {
                    // All adj nodes of the currentNode have opposite sign
                    queue.add(new BipartiteQueueNode(j, sign));
                    visited[j] = sign;
                } else {
                    int colour = visited[j];
                    // If adjacent nodes have same colour, ten it is not bipartite graph
                    if (colour == currentNode.colour) {
                        return false;
                    }
                }
            }
            // Invert sign for next set of nodes
            sign = sign * -1;
        }
        // If we reach this point then it means the graph is a bipartite graph
        return true;
    }
}
