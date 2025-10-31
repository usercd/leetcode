package heap;

import java.util.PriorityQueue;

public class Code_215_KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap approach
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

}