package com.algo.graph.grade1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You will be given a simple undirected graph, and you have to find out whether the graph contains a loop or not.
 * If the graph contains a loop, print ‘Yes’, otherwise, print ‘No’.
 * <p>
 * A graph is said to have a loop if there is a series of nodes connected by edges in the graph such
 * that the starting and ending nodes of the series are the same. To get a clear understanding of loops,
 * please go through the examples given below.
 * <p>
 * Note:
 * <p>
 * The graph given could be disconnected. The graph given in example 3 below, is a disconnected graph.
 * <p>
 * Parallel edges will not exist between any two nodes of the graph given.
 * <p>
 * This means that there won’t be multiple edges between any two nodes.
 * <p>
 * The following image is an example of a graph containing parallel edges
 * <p>
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 */
public class LoopsInUndirectedGraph {

    //Adjacency list which is used to represent the graph
    public LinkedList[] adjacencyList;

    private static boolean isLoopFound(LinkedList<Integer> nodes[], List<Integer> visited, int currNode, int parent) {

        visited.add(currNode);
        for (Integer adjNode : nodes[currNode]) {
            if (!visited.contains(adjNode))
                return isLoopFound(nodes, visited, adjNode, currNode);
            else if (adjNode != parent) return true;
        }
        return false;
    }

    public static void main(String args[]) {


        LoopsInUndirectedGraph obj = new LoopsInUndirectedGraph();
        obj.adjacencyList = new LinkedList[4];
        obj.initLinkedList(obj.adjacencyList, 4);
        obj.addEdge(1, 3);
        obj.addEdge(2, 3);
        obj.addEdge(1, 2);

        boolean isLoop = obj.isLoopFound(4);
        System.out.println("Test case 1. Is loop found = " + isLoop);

        obj.initLinkedList(obj.adjacencyList, 4);
        obj.addEdge(0, 1);
        obj.addEdge(1, 3);
        obj.addEdge(2, 3);
        isLoop = obj.isLoopFound(4);
        System.out.println("Test case 2. Is loop found = " + isLoop);

        obj.initLinkedList(obj.adjacencyList, 4);
        obj.addEdge(0, 1);
        obj.addEdge(1, 3);
        obj.addEdge(0, 3);
        obj.addEdge(2, 3);
        isLoop = obj.isLoopFound(4);
        System.out.println("Test case 3. Is loop found = " + isLoop);


    }

    private void addEdge(int v1, int v2) {
        adjacencyList[v1].add(v2);
        adjacencyList[v2].add(v1);
    }

    private boolean isLoopFound(int n) {
        List<Integer> visited = new ArrayList<>();
        boolean isLoop = false;
        for (int i = 0; i < n; ++i) {
            visited.clear();
            if (!visited.contains(i))
                isLoop = isLoopFound(adjacencyList, visited, i, i);
            if (isLoop) break;
        }
        return isLoop;
    }

    private void initLinkedList(LinkedList<Integer>[] adjacencyList, int n) {
        for (int i = 0; i < n; ++i) {
            adjacencyList[i] = new LinkedList();
        }
    }
}
