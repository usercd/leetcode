package dp;

/**
 * @author CD
 * @date 7/10/2026
 */
public class Code_1155_NumberOfDiceRollsWithTargetSum {
    private static final int MOD = 1_000_000_007;

    // 时间O(n * target * k)，空间O(target)
    public int numRollsToTarget(int n, int k, int target) {

        if (target < n || target > n * k) {
            return 0;
        }

        // dp代表处理当前骰子之前的结果
        // dp[i][j] 代表使用i个骰子得到点数和为j的方案数
        // dp[i] 代表当前已经处理的骰子，得到i的方案数
        int[] dp = new int[target + 1];
        // 表示不使用骰子且总和为 0 时，有一种方案。
        dp[0] = 1;

        for (int dice = 1; dice <= n; dice++) {
            // next代表处理当前骰子的结果
            // 如果是边计算边处理后面的状态可能使用了前面刚刚由“当前骰子”计算出来的结果，相当于一个骰子被使用了多次。
            // 使用 next 可以保证当前骰子的计算只使用上一个骰子的结果。
            int[] next = new int[target + 1];

            // 第dice个骰子处理完后，sum范围为[dice, dice*k]
            int maxSum = Math.min(target, dice * k);

            // 枚举当前骰子最终结果数
            for (int sum = dice; sum <= maxSum; sum++) {
                long ways = 0;

                // 当前骰子最小的点可能的情况发生在前面所有骰子都是最大的点数
                int minFace = Math.max(1, sum - (dice - 1) * k);
                int maxFace = Math.min(k, sum - (dice - 1));

                // 如果当前骰子点数是face，对应的方案数是dp[i][sum] =dp[i - 1][sum - 1]+ dp[i - 1][sum - 2]+...+dp[i-1][sum-k]
                for (int face = minFace; face <= maxFace; face++) {
                    ways += dp[sum - face];
                }

                next[sum] = (int) (ways % MOD);
            }

            dp = next;
        }

        return dp[target];
    }
}
