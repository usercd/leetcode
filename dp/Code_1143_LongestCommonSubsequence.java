package dp;

/**
 * LeetCode 1143. 最长公共子序列
 * 
 * 题目描述：
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 如果不存在公共子序列，返回 0。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
 * 2. 状态转移：
 *    - 如果 text1[i-1] == text2[j-1]，则 dp[i][j] = dp[i-1][j-1] + 1
 *    - 否则，dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 3. 初始状态：dp[0][j] = 0 和 dp[i][0] = 0（空字符串的情况）
 * 4. 填充 dp 数组，最终结果为 dp[m][n]
 * 
 * 时间复杂度：O(m*n)
 * 空间复杂度：O(m*n)
 */

public class Code_1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果当前字符相等，最长公共子序列长度加 1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 否则，取不包含当前字符的两种情况的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 返回最长公共子序列长度
        return dp[m][n];
    }
}
