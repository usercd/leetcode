package heap;

/**
 * LeetCode 295. 数据流的中位数
 * 
 * 题目描述：
 * 中位数是有序数据集合的中间值。如果数据集合的大小是偶数，则中位数是中间两个数的平均值。
 * 设计一个支持以下两种操作的数据结构：
 * 1. void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * 2. double findMedian() - 返回目前所有元素的中位数。
 * 
 * 解题思路：
 * 使用两个堆（优先队列）来维护数据流中的元素：
 * - 最大堆（maxHeap）存储较小的一半元素。
 * - 最小堆（minHeap）存储较大的一半元素。
 * 通过保持两个堆的大小平衡，可以在 O(log n) 时间内添加元素，并在 O(1) 时间内找到中位数。
 * 
 * 时间复杂度：addNum O(log n)，findMedian O(1)
 * 空间复杂度：O(n)
 */

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
