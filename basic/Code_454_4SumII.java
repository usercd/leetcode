package basic;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 * 题目大意：
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度均为 n ，
 * 请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 
 * 解题思路：
 * 使用哈希表存储前两个数组所有可能的和及其出现的次数，
 * 然后遍历后两个数组的所有可能的和，检查其相反数是否存在于哈希表中，
 * 如果存在，则将其出现的次数累加到结果中。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n^2)，其中 n 是数组的长度。我们需要两次嵌套循环来计算前两个数组和后两个数组的所有可能和。
 * 空间复杂度：O(n^2)，用于存储前两个数组所有可能和的哈希表。
 */

public class Code_454_4SumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        // 计算 nums1 和 nums2 的所有可能和，并存储在哈希表中
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        int count = 0;
        // 计算 nums3 和 nums4 的所有可能和，检查其相反数是否在哈希表中
        for (int c : nums3) {
            for (int d : nums4) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }
        return count;
    }
}
