package greedy;

/**
 * LeetCode 3487. 删除一个元素以后全为唯一元素的子数组最大和
 * 
 * 题目描述：
 * 给你一个整数数组 nums 。你可以从数组 nums 中删除任意数量的元素，
 * 但不能将其变为 空 数组。执行删除操作后，选出 nums 中满足下述条件的一个子数组
 * - 子数组中的所有元素 互不相同 。
 * - 最大化 子数组的元素和。
 * 返回子数组的 最大元素和 。子数组 是数组的一个连续、非空 的元素序列。 
 * 
 * 解题思路：
 * 1. 遍历数组，计算所有正数的和，并使用布尔数组记录出现过的正数。
 * 2. 如果存在正数，则返回所有正数的和；否则返回数组中的最大负数。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。需要遍历数组一次。
 * 空间复杂度：O(1)，使用了固定大小的布尔数组来记录出现过的数字。
 */

public class Code_3487_MaximumUniqueSubarraySumAfterDeletion {
    public int maxSum(int[] nums) {
        int maxNumber = -101;
        int sumOfSubarray = 0;
        boolean[] seen = new boolean[101];
        for (int num : nums) {
            maxNumber = Math.max(maxNumber, num);
            if (num > 0 && !seen[num]) {
                sumOfSubarray += num;
                seen[num] = true;
            }
        }

        return maxNumber >= 0 ? sumOfSubarray : maxNumber;
    }
}
