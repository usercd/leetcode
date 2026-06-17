package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560. 和为 K 的子数组
 * 
 * 题目描述：
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 
 * 解题思路：
 * 使用前缀和与哈希表结合的方法求解
 * 1. 定义前缀和 sum，表示从数组开头到当前位置的元素和
 * 2. 使用哈希表 prefixSumMap 存储每个前缀和出现的次数
 * 3. 遍历数组，计算当前前缀和 sum
 * 4. 检查 prefixSumMap 中是否存在 sum - k，如果存在，说明存在一个子数组的和为 k
 * 5. 更新 prefixSumMap 中当前前缀和 sum 的出现次数
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */

public class Code_560_SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        int count = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            if (prefixSumMap.containsKey(sum - k)) {
                count += prefixSumMap.get(sum - k);
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
