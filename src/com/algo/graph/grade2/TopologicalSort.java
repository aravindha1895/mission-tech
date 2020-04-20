package com.algo.graph.grade2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * The topological sort of a direct acyclic graph is the ordering of all the nodes of a graph
 * such that if there is an edge directed from node ‘a’ to node ‘b’, node ‘a’ comes before node ‘b’.
 * <p>
 * Note: If the graph is not a directed acyclic graph, the topological sort of the graph is not possible.
 * Let's prove this with the simplest cycle. Let's assume that there exists a cycle between two nodes - a and b.
 * Thus, the edges will be (a, b) (i.e., a→b)  and (b, a) (i.e., b→a).
 * Now, based on the condition of the topological sort, with the edge (a, b),
 * the node a must appear before the node b in the topological sort order.
 * Now, considering the other edge (b, a), the node ‘b’ must appear before the node ‘a’ in the topological sort order.
 * Thus, this is a contradiction of the above statement, therefore, making the topological sort not existing
 * for a graph containing any cycle.
 * <p>
 * Time complexity: O(V+E)
 * Space complexity: O(V+E)
 */
public class TopologicalSort {
    // Adjacency list which is used to represent the graph
    public LinkedList<Integer> adjacencyList[];

    LinkedList<Integer> returnTopologicalSort(int noOfNodes) {
        LinkedList<Integer> list = new LinkedList<>();

        // Write your code here.
        boolean visited[] = new boolean[noOfNodes];
        Stack<Integer> s = new Stack();
        for (int i = 0; i < noOfNodes; i++) {
            if (!visited[i])
                topologicalSort(i, visited, s);
        }
        while (!s.isEmpty()) {
            list.add(s.pop());
        }


        return list;
    }

    private void topologicalSort(int currNode, boolean visited[], Stack s) {
        visited[currNode] = true;
        for (Integer adj : adjacencyList[currNode]) {
            if (!visited[adj])
                topologicalSort(adj, visited, s);
        }
        // After all neighbours are explored, push neighbours first and ten the source
        s.push(currNode);
    }

    private void initLinkedList(LinkedList<Integer>[] adjacencyList, int n) {
        for (int i = 0; i < n; ++i) {
            adjacencyList[i] = new LinkedList();
        }
    }

    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort();
        obj.adjacencyList = new LinkedList[5];
        LinkedList<Integer> topologicalSortResult;
        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[1].add(0);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[1].add(3);
        obj.adjacencyList[2].add(3);
        topologicalSortResult = obj.returnTopologicalSort(4);
        verifyTopologicalSort(obj.adjacencyList, topologicalSortResult, 4);

        obj.initLinkedList(obj.adjacencyList, 4);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[1].add(3);
        obj.adjacencyList[2].add(3);
        topologicalSortResult = obj.returnTopologicalSort(4);
        verifyTopologicalSort(obj.adjacencyList, topologicalSortResult, 4);


        obj.initLinkedList(obj.adjacencyList, 5);
        obj.adjacencyList[0].add(1);
        obj.adjacencyList[0].add(2);
        obj.adjacencyList[0].add(3);
        obj.adjacencyList[1].add(4);
        obj.adjacencyList[4].add(3);
        topologicalSortResult = obj.returnTopologicalSort(5);
        verifyTopologicalSort(obj.adjacencyList, topologicalSortResult, 5);


    }

    private static void verifyTopologicalSort(LinkedList<Integer>[] adjacencyList, LinkedList<Integer> topologicalList, int noOfNodes) {
        int k;
        Boolean visited[] = new Boolean[noOfNodes];
        for (int i = 0; i < noOfNodes; i++) {
            visited[i] = false;
        }
        for (int i : topologicalList) {
            visited[i] = true;
            Iterator<Integer> it = adjacencyList[i].iterator();

            while (it.hasNext()) {
                k = it.next();
                if (visited[k]) {
                    System.out.println("Wrong");
                    return;
                }
            }
        }
        System.out.println("Correct");
    }
}
