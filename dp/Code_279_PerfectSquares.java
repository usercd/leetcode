package dp;

/**
 * LeetCode 279. 完全平方数
 * 
 * 题目描述：
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示数字 i 最少需要多少个完全平方数的和
 * 2. 状态转移：对于每个数字 i，尝试所有小于等于 i 的完全平方数 j*j
 *    dp[i] = min(dp[i], dp[i - j*j] + 1)
 *    意思是：数字 i 可以由 (i - j*j) 加上一个完全平方数 j*j 组成
 * 3. 初始状态：dp[0] = 0，dp[i] 初始化为 i（最坏情况是 i 个 1 相加）
 * 4. 返回结果：dp[n]
 * 
 * 时间复杂度：O(n * sqrt(n))
 * 空间复杂度：O(n)
 */
public class Code_279_PerfectSquares {
    public int numSquares(int n) {
        // dp[i] 表示组成数字 i 所需的最少完全平方数个数
        int[] dp = new int[n + 1];
        
        // 遍历每个数字
        for (int i = 1; i <= n; i++) {
            // 初始值设为最坏情况：i 个 1 相加 (1*1 + 1*1 + ... + 1*1)
            dp[i] = i;
            
            // 尝试所有可能的完全平方数 j*j
            for (int j = 1; j * j <= i; j++) {
                // 状态转移：i 可以由 (i - j*j) 加上一个完全平方数 j*j 组成
                // 取所有可能中的最小值
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
}
