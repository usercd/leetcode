package dp;

/**
 * @author CD
 * @date 7/10/2026
 */
public class Code_2585_NumberOfWaysToEarnPoints {
    private static final int MOD = 1_000_000_007;

    // 时间O(n * target * count) 空间O(target)
    public int waysToReachTarget(int target, int[][] types) {
        // dp[score] 表示处理完前面若干种题型后，得到 score 分的方案数
        int[] dp = new int[target + 1];

        // 不选择任何题目，得到 0 分，有 1 种方案
        dp[0] = 1;

        // 依次处理每一种题型
        for (int[] type : types) {
            int count = type[0];
            int marks = type[1];

            // next 表示加入当前题型后的方案数
            int[] next = new int[target + 1];

            // 枚举处理当前题型之前已经得到的分数
            for (int score = 0; score <= target; score++) {
                if (dp[score] == 0) {
                    // 之前无法到达score分，如果从score开始计算无意义
                    continue;
                }

                // 当前题型选择 0 到 count 道
                for (int used = 0; used <= count; used++) {
                    int newScore = score + used * marks;

                    // 分数超过 target，就没有继续计算的必要
                    if (newScore > target) {
                        break;
                    }

                    next[newScore] =
                            (int) ((next[newScore] + (long) dp[score]) % MOD);
                }
            }

            // 当前题型处理完成，进入下一轮
            dp = next;
        }

        return dp[target];
    }
}
