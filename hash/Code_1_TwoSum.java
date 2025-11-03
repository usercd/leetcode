package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和
 * 
 * 题目描述：
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 
 * 解题思路：
 * 使用哈希表存储已经遍历过的数字及其索引
 * 1. 遍历数组，对于每个数字 nums[i]，计算其补数 complement = target - nums[i]
 * 2. 检查补数是否在哈希表中：
 *    - 如果存在，返回补数的索引和当前数字的索引
 *    - 如果不存在，将当前数字和其索引存入哈希表
 * 3. 如果遍历结束仍未找到答案，抛出异常
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class Code_1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 使用哈希表存储已经遍历过的数字及其索引
        Map<Integer, Integer> map = new HashMap<>();
        
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 计算补数
            
            // 检查补数是否在哈希表中
            if (map.containsKey(complement)) {
                // 找到答案，返回补数和当前数字的索引
                return new int[] { map.get(complement), i };
            }
            
            // 将当前数字和其索引存入哈希表
            map.put(nums[i], i);
        }
        
        // 如果没有找到答案，返回空数组（根据题意不会到达这里）
        return new int[0];
    }
}
