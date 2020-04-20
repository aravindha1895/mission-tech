package com.algo.graph.grade1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You will be given a directed graph and a source node. You need to print the breadth-first traversal of the graph.
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 */
public class BreadthFirstTraversal {
    // Adjacency list which is used to represent the graph
    public LinkedList<Integer> adjacencyList[];

    private void initLinkedList(LinkedList<Integer>[] adjacencyList, int n) {
        for (int i = 0; i < n; ++i) {
            adjacencyList[i] = new LinkedList();
        }
    }

    public static void main(String[] args) {
        BreadthFirstTraversal obj = new BreadthFirstTraversal();
        obj.adjacencyList = new LinkedList[5];
        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[1].add(0);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[1].add(3);
        obj.adjacencyList[2].add(3);
        obj.printBFS(obj.adjacencyList, 1);

        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[1].add(3);
        obj.adjacencyList[2].add(3);
        obj.printBFS(obj.adjacencyList, 0);


        obj.initLinkedList(obj.adjacencyList, 5);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[0].add(3);
        obj.adjacencyList[1].add(4);
        obj.adjacencyList[4].add(3);
        obj.printBFS(obj.adjacencyList, 0);
    }

    private static void printBFS(LinkedList<Integer> adjacencyList[], int source) {
        Queue<Integer> queue = new LinkedList();
        List<Integer> visited = new ArrayList<Integer>();
        queue.add(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            int currentNode = queue.remove();

            for (Integer adj : adjacencyList[currentNode]) {
                if (!visited.contains(adj)) {
                    visited.add(adj);
                    queue.add(adj);
                }

            }
            System.out.print(currentNode + " ");
        }
        System.out.println();
    }
}
