package dp;

/**
 * LeetCode 53. 最大子数组和
 * 
 * 题目描述：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 解题思路：
 * 使用动态规划的方法，遍历数组时维护当前子数组的和，如果当前和为负数则重新开始计算子数组和。
 * 同时更新最大子数组和。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_53_MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int n = nums.length, result = nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    // 空间优化
    public static int maxSubArray1(int[] nums) {
        int n = nums.length, result = nums[0];
        int last_dp = nums[0], dp = nums[0];
        for (int i = 1; i < n; i++) {
            dp = Math.max(nums[i], last_dp + nums[i]);
            result = Math.max(result, dp);
            last_dp = dp;
        }
        return result;
    }

    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) {
                currentSum = 0; // 如果当前和为负数，重新开始计算子数组和
            }
        }

        return maxSum;
    }
}
