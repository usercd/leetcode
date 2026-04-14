package dp;

/**
 * LeetCode 2466. 统计构造好字符串的方案数
 * 
 * 题目描述：
 * 给你整数 zero ，one ，low 和 high ，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
 * 将 '0' 在字符串末尾添加 zero  次。
 * 将 '1' 在字符串末尾添加 one 次。
 * 以上操作可以执行任意次。
 * 如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。
 * 请你返回满足以上要求的 不同 好字符串数目。由于答案可能很大，请将结果对 109 + 7 取余 后返回。
 * 
 * 解题思路：

 * 
 * 时间复杂度：O(n)，其中 n 是 high 的值
 * 空间复杂度：O(n)，用于存储 dp 数组
 */

public class Code_2466_CountWaysToBuildGoodStrings {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int mod = 1_000_000_007;
        // dp[i] 表示长度为 i 的字符串的构造方案数
        long[] dp = new long[high + 1];
        dp[0] = 1; // 空字符串的构造方案数为 1
        // 通过动态规划计算长度为 1 到 high 的字符串的构造方案数
        for (int i = 1; i <= high; i++) {
            // 如果长度 i 大于等于 zero，则可以在长度为 i - zero 的字符串末尾添加 '0' 来构造长度为 i 的字符串
            if (i >= zero) {
                // dp[i] 的计算基于两种情况：
                // 1. 不使用 '0'：dp[i] = dp[i]
                // 2. 使用 '0'：dp[i] = dp[i - zero]
                dp[i] = (dp[i] + dp[i - zero]) % mod;
            }
            // 如果长度 i 大于等于 one，则可以在长度为 i - one 的字符串末尾添加 '1' 来构造长度为 i 的字符串
            if (i >= one) {
                // dp[i] 的计算基于两种情况：
                // 1. 不使用 '1'：dp[i] = dp[i]
                // 2. 使用 '1'：dp[i] = dp[i - one]
                dp[i] = (dp[i] + dp[i - one]) % mod;
            }
        }
        long result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % mod;
        }
        return (int) result;
    }
}
