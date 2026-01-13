package heap;

import java.util.PriorityQueue;

/**
 * LeetCode 215. 数组中的第K个最大元素
 * 
 * 题目描述：
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 解题思路：
 * 使用最小堆（Min-Heap）来维护数组中最大的 k 个元素。
 * 遍历数组，将每个元素加入最小堆中；
 * 当堆的大小超过 k 时，移除堆顶元素（即当前最小的元素）。
 * 最终，堆顶元素即为第 k 个最大的元素。
 * 
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(k)
 */

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