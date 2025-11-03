package dp;

/**
 * LeetCode 322. 零钱兑换
 * 
 * 题目描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成该金额，返回 -1。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示组成金额 i 所需的最少硬币个数
 * 2. 状态转移：对于每个金额 i，尝试每种硬币面值 coin
 *    如果 i - coin >= 0，则 dp[i] = min(dp[i], dp[i - coin] + 1)
 *    意思是：金额 i 可以由 (i - coin) 加上一个硬币 coin 组成
 * 3. 初始状态：dp[0] = 0，dp[i] 初始化为 amount + 1（不可能的值）
 * 4. 返回结果：如果 dp[amount] 仍为初始值，表示无法组成该金额，返回 -1；否则返回 dp[amount]
 * 
 * 时间复杂度：O(amount * n)，其中 n 是硬币种类数
 * 空间复杂度：O(amount)
 */
public class Code_322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        // dp[i] 表示组成金额 i 所需的最少硬币个数
        int[] dp = new int[amount + 1];
        
        // 初始化 dp 数组，初始值设为 amount + 1（不可能的值）
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        
        // 遍历每个金额
        for (int i = 1; i <= amount; i++) {
            // 尝试每种硬币面值
            for (int coin : coins) {
                if (i - coin >= 0) {
                    // 状态转移：选择使用当前硬币
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 如果 dp[amount] 仍为初始值，表示无法组成该金额
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
