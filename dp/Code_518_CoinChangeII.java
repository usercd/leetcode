package dp;

/**
 * @author CD
 * @date 7/9/2026
 * 完全背包（正序） + 统计组合（物品外层） = 外层遍历硬币，内层正序遍历金额。
 */
public class Code_518_CoinChangeII {
    public int change(int amount, int[] coins) {
        // dp[i] 代表组成金额i的方案总数
        int[] dp = new int[amount + 1];

        // 凑成金额0有一种方法：什么也不选
        dp[0] = 1;

        // 外层遍历硬币，内层正序遍历金额（完全背包）
        // 为什么要这个顺序？能不能反过来？
        // 金额放外层会统计排列，硬币放外层代表每种组合中的硬币按照固定顺序（面额递增）加入。
        for (int coin : coins) {
            // 每个硬币可以无限次使用，后边的要根据前面进行更新，所以j要从小到大
            for (int j = coin; j <= amount; j++) {
                // dp[j] = dp[j] + dp[j - coin];
                // dp[j] 代表不选择当前coin方案总数，dp[j-coin]代表使用当前coin总数
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }
}
