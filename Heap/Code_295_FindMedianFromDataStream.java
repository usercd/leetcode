package heap;

import java.util.PriorityQueue;

public class Code_295_FindMedianFromDataStream {
    // Implementing a MedianFinder class to find the median from a data stream
    class MedianFinder {
        // Two heaps: maxHeap for the lower half, minHeap for the upper half
        private PriorityQueue<Integer> maxHeap; // Max-Heap (inverted min-heap)
        private PriorityQueue<Integer> minHeap; // Min-Heap

        /** Initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-Heap
            minHeap = new PriorityQueue<>(); // Min-Heap
        }

        /** Adds a number into the data structure. */
        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance the heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        /** Returns the median of current data stream */
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
}
