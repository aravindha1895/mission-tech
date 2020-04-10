package com.algo.datastructures.heaps;

/**
 * Full implementation of heap data structure.
 * A Min-Heap is a complete binary tree in which the value in each internal node is
 * smaller than or equal to the values in the children of that node.
 * Mapping the elements of a heap into an array is trivial: if a node is stored a index k,
 * then its left child is stored at index 2k + 1 and its right child at index 2k + 2.
 * Example of Min Heap:
 *
 *             5                      13
 *          /      \               /       \
 *        10        15           16         31
 *       /                      /  \        /  \
 *     30                     41    51    100   41
 * <p>
 * <p>
 * Time complexities:
 * 1. Insert - O(log(n))
 * 2. getMin - O(1)
 * 3. deleteMin - O(log(n))
 * 4. delete (At specific position) - O(log(n))
 * 5. Update - O(log(n))
 */
class Heap {
    private int[] heap;
    private int size;
    private int heapSize;

    public Heap(int heapSize) {
        this.heap = new int[heapSize];
        this.size = 0;
        this.heapSize = heapSize;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return heapSize == size;
    }

    public int size() {
        return this.size;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    public void insert(int ele) throws Exception {
        if (isFull()) {
            throw new Exception("Heap full");
        }
        int currentPos = size;
        // Insert the element and increase heap size
        this.heap[size++] = ele;
        // Maintain heap property
        heapifyUp(currentPos);
    }

    public int getMin() {
        return heap[0];
    }

    /*Minimum element is present at root of the heap, so deleteMin is same as delete(0)*/
    public void deleteMin() throws Exception {
        delete(0);
    }

    public void update(int index, int ele) throws Exception {
        int currentValue = heap[index];
        heap[index] = ele;
        /* Children may be greater than ele so heapify down . As ele>currentValue so we can
         safely say that parent is lesser that ele, so heapify up not necessary*/
        if (ele > currentValue)
            heapifyDown(index);
        else
        /* Inverse of other case. Parent may be greater than ele so heapify up . As ele<currentValue so we can
         safely say that every child of this is lesser that ele, so heapify down not necessary*/
            heapifyUp(index);
    }

    public void delete(int pos) throws Exception {
        if (isEmpty())
            throw new Exception("Heap is empty");
        // Replace last element in current pos and reduce size of heap by 1
        heap[pos] = heap[--size];
        /* Children may be greater than ele at pos so heapify down*/
        heapifyDown(pos);

    }

    private void heapifyDown(int currentPos) {
        int pos = currentPos;
        int left = leftChild(currentPos);
        int right = rightChild(currentPos);
        /*Check if left child less than current ele*/
        if (left < size && heap[left] < heap[pos])
            pos = left;
        /*Check if right child less than current ele*/
        if (right < size && heap[right] < heap[pos])
            pos = right;
        /*If there is a need to swap, swap and heapify at that position*/
        if (pos != currentPos) {
            swap(currentPos, pos);
            heapifyDown(pos);
        }
    }

    /*In heapify up we compare wit parent all the way upto index 0*/
    private void heapifyUp(int currentPos) {
        int pos = currentPos;
        int parent = parent(currentPos);
        if (heap[parent] > heap[pos]) {
            swap(pos, parent);
            heapifyUp(parent);
        }
    }

    private void swap(int p1, int p2) {
        int temp = heap[p1];
        heap[p1] = heap[p2];
        heap[p2] = temp;
    }

    public void printHeap() {
        System.out.print("Heap = ");
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

}

public class HeapImpl {
    public static void main(String[] args) throws Exception {
        // Initialize eap of size 10
        Heap minHeap = new Heap(10);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.insert(11);
        System.out.println("After insertion:");
        minHeap.printHeap();
        System.out.println("Is heap full ? " + minHeap.isFull());
        System.out.println("Insert element after eap is full");
        /* We initialized heap of size 10. Inserting 11 th element will give Exception*/
        try {
            minHeap.insert(15);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        System.out.println("Delete at specific index:" + 1);
        minHeap.delete(1);
        minHeap.printHeap();

        System.out.println("Extract min = " + minHeap.getMin()); // Extract min will not delete element

        System.out.println("Delete min:"); // Delete min is same as delete(0)
        minHeap.deleteMin();
        minHeap.printHeap();

        System.out.println("Is heap empty ? " + minHeap.isEmpty());
        System.out.println("Number of elements in heap after 2 deletion = " + minHeap.size());

        System.out.println("Update operation with ele greater existing element");
        minHeap.update(2, 100);
        minHeap.printHeap();

        System.out.println("Update operation with ele less than existing element");
        minHeap.update(1, 3);
        minHeap.printHeap();

        /*Delete  elements to make heap empty*/
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        minHeap.deleteMin();
        System.out.println("Delete elements after heap empty");
        try {
            minHeap.deleteMin();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }

    }
}
