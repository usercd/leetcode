package dp;

/**
 * LeetCode 416. 分割等和子集
 * 题目描述：
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[j] 表示是否存在子集，其元素和为 j
 * 2. 状态转移：
 *    对于每个数字 num，遍历 dp 数组：
 *    dp[j] = dp[j] || dp[j - num]
 *    意思是：如果之前存在一个子集和为 j - num，那么加上 num 后就存在一个子集和为 j
 * 3. 初始状态：dp[0] = true（和为 0 的子集总是存在，即空集）
 * 4. 返回结果：dp[target]，其中 target 是总和的一半
 * 时间复杂度：O(n * target)
 * 空间复杂度：O(target)
 */

public class Code_416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        // If total sum is odd, cannot partition into two equal subsets
        if (totalSum % 2 != 0) {
            return false;
        }
        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: zero sum is always achievable

        for (int num : nums) {
            // Traverse dp array in reverse order
            for (int j = target; j >= num; j--) {
                // Update dp array in reverse to avoid using the same number multiple times
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
