package sliding_window;

/**
 * LeetCode 643. 子数组最大平均数 I
 * 
 * 题目描述：
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k，请你找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 
 * 解题思路：
 * 使用滑动窗口技术来解决这个问题。维护一个长度为 k 的窗口，计算窗口内的元素和，并在每次移动窗口时更新最大和。
 * 1. 初始化窗口和当前和。
 * 2. 遍历数组，扩展窗口并更新当前和。
 * 3. 当窗口大小超过 k 时，收缩窗口并更新当前和。
 * 4. 在每次调整窗口时，更新最大和。
 * 5. 最后返回最大和除以 k 的结果。
 * 
 * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。
 * 空间复杂度：O(1)，只使用了常数空间来存储当前和和最大和。
 */

public class Code_643_MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return (double) maxSum / k;
    }
}
