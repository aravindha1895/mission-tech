package com.algo.graph.grade1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You will be given a directed graph, and you have to check whether the graph contains a loop or not.
 * If the graph contains a loop print ‘Yes’; otherwise, print ‘No’.
 * A graph is said to have a loop if there is a series of nodes in the graph such that
 * the starting and ending nodes of the series are the same.
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
public class LoopInDirectedGraph {

    //Adjacency list which is used to represent the graph
    public LinkedList<Integer> adjacencyList[];

    private static boolean isLoopFound(LinkedList<Integer> nodes[], List<Integer> visited, int currNode) {

        visited.add(currNode);
        for (Integer adjNode : nodes[currNode]) {
            if (!visited.contains(adjNode))
                return isLoopFound(nodes, visited, adjNode);
            else
                return true;
        }
        return false;
    }

    public static void main(String args[]) {


        LoopInDirectedGraph obj = new LoopInDirectedGraph();
        obj.adjacencyList = new LinkedList[4];
        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[1].add(2);
        obj.adjacencyList[2].add(3);
        obj.adjacencyList[3].add(0);

        boolean isLoop = obj.isLoopFound(4);
        System.out.println("Test case 1. Is loop found = " + isLoop);

        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[2].add(3);
        obj.adjacencyList[3].add(1);
        isLoop = obj.isLoopFound(4);
        System.out.println("Test case 2. Is loop found = " + isLoop);


    }

    private boolean isLoopFound(int n) {
        List<Integer> visited = new ArrayList<>();
        boolean isLoop = false;
        for (int i = 0; i < n; ++i) {
            visited.clear();
            if (!visited.contains(i))
                isLoop = isLoopFound(adjacencyList, visited, i);
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
