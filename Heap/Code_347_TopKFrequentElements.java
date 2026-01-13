package heap;

/**
 * LeetCode 347. 前 K 个高频元素
 * 
 * 题目描述：
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 
 * 解题思路：
 * 使用哈希表统计每个元素的频率，然后使用最小堆（Min-Heap）来维护前 k 个高频元素。
 * 遍历频率表，将每个元素及其频率加入最小堆中；
 * 当堆的大小超过 k 时，移除堆顶元素（即当前频率最低的元素）。
 * 最终，堆中的元素即为前 k 个高频元素。
 * 
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(n)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Code_347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
