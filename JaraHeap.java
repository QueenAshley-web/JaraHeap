import java.util.ArrayList;

public class JaraHeap {
    private ArrayList<Integer> heap;

    // Constructor
    public JaraHeap() {
        heap = new ArrayList<>();
    }

    // Get the index of the parent node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the index of the left child
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Get the index of the right child
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Insert a value into the heap
    public void insert(int value) {
        heap.add(value);
        int currentIndex = heap.size() - 1;

        // Bubble up
        while (currentIndex > 0 && heap.get(currentIndex) < heap.get(parent(currentIndex))) {
            swap(currentIndex, parent(currentIndex));
            currentIndex = parent(currentIndex);
        }
    }

    // Remove and return the minimum element
    public int removeMin() {
        if (heap.size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (heap.size() > 0) {
            heap.set(0, lastElement);
            heapify(0); // Restore heap property
        }

        return min;
    }

    // Heapify down to maintain the heap property
    private void heapify(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Print the heap
    public void printHeap() {
        System.out.println(heap);
    }

    // Main method for testing
    public static void main(String[] args) {
        JaraHeap minHeap = new JaraHeap();
        
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(7);
        minHeap.insert(2);

        System.out.println("Heap:");
        minHeap.printHeap();

        System.out.println("Removed Min: " + minHeap.removeMin());
        System.out.println("Heap after removal:");
        minHeap.printHeap();
    }
}