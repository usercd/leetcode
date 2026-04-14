package dp;

/**
 * LeetCode 377. 组合总和 IV
 * 
 * 题目描述：
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示组成目标数 i 的方法数
 * 2. 状态转移：对于每个目标数 i，遍历 nums 中的每个数字 num，如果 num <= i，则 dp[i] += dp[i - num]
 * 3. 初始状态：dp[0] = 1（组成目标数为 0 的方法只有一种，就是不选任何数字）
 * 
 * 时间复杂度：O(n * m)，其中 n 是目标数 target 的值，m 是数组 nums 的长度
 * 空间复杂度：O(n)，用于存储 dp 数组
 */

public class Code_377_CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        // dp[i] 表示组成目标数 i 的方法数
        int[] dp = new int[target + 1];
        dp[0] = 1; // base case: 组成目标数为 0 的方法只有一种，就是不选任何数字
        // 通过动态规划计算组成目标数 1 到 target 的方法数
        for (int i = 1; i <= target; i++) {
            // 遍历 nums 中的每个数字 num，如果 num <= i，则 dp[i] += dp[i - num]
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num]; // 状态转移：dp[i] += dp[i - num]
                }
            }
        }
        return dp[target];
    }
}
