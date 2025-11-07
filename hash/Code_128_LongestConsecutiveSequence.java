package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128. 最长连续序列
 * 
 * 题目描述：
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 
 * 解题思路：
 * 使用哈希集合存储数组中的元素，然后遍历每个元素，寻找以该元素为起点的最长连续序列。
 * 1. 将所有数字存入哈希集合，方便 O(1) 时间内查找。
 * 2. 遍历每个数字，检查该数字是否为一个序列的起点（即 num-1 不在集合中）。
 * 3. 如果是起点，则向后查找连续的数字，计算序列长度。
 * 4. 更新最长序列长度。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */

public class Code_128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // 使用哈希集合存储所有数字，方便快速查找
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 遍历每个数字，寻找最长的连续序列
        for (int num : numSet) {
            // 只从序列的起点开始查找
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 向后查找连续的数字
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 更新最长序列长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
