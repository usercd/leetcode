package dp;

/**
 * LeetCode 72. 编辑距离
 * 
 * 题目描述：
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最少操作数
 * 2. 状态转移：
 *    - 如果 word1[i-1] == word2[j-1]，则 dp[i][j] = dp[i-1][j-1]
 *    - 否则，dp[i][j] = min(
 *          dp[i][j-1] + 1,   // 插入操作
 *          dp[i-1][j] + 1,   // 删除操作
 *          dp[i-1][j-1] + 1  // 替换操作
 *      )
 * 3. 初始状态：
 *    - dp[0][j] = j（将空字符串转换为 word2 的前 j 个字符需要 j 次插入）
 *    - dp[i][0] = i（将 word1 的前 i 个字符转换为空字符串需要 i 次删除）
 * 4. 填充 dp 数组，最终结果为 dp[m][n]
 * 
 * 时间复杂度：O(m*n)
 * 空间复杂度：O(m*n) 或 O(n)（空间优化版本）
 */

public class Code_72_EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最少操作数
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化边界条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 将 word1 的前 i 个字符转换为空字符串需要 i 次删除操作
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 将空字符串转换为 word2 的前 j 个字符需要 j 次插入操作
        }
        
        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 字符相等，不需要额外操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 字符不相等，考虑三种操作：插入、删除、替换
                    int insertOp = dp[i][j - 1] + 1;   // 插入操作 word2的最后一个字符通过插入操作匹配，j要减一，word1的当前字符还没处理i不变
                    int deleteOp = dp[i - 1][j] + 1;   // 删除操作 删除word1当前字符，word2不变
                    int replaceOp = dp[i - 1][j - 1] + 1; // 替换操作 word1的第i个字符和word2的第j个字符替换保持一致，所以需要dp[i-1][j-1]
                    dp[i][j] = Math.min(insertOp, Math.min(deleteOp, replaceOp));
                }
            }
        }
        
        // 返回将 word1 转换为 word2 所需的最少操作数
        return dp[m][n];
    }

    // 空间优化版本
    public int minDistance1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 创建一个长度为 n + 1 的一维数组
        int[] dp = new int[n + 1];

        // 初始化 dp 数组
        for (int j = 0; j <= n; j++) {
            dp[j] = j;  // 将空字符串转换为 word2 的前 j 个字符需要 j 次插入
        }

        // 遍历 word1 的每个字符
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];  // 保存上一个 dp[i-1][j] 的值
            dp[0] = i;  // 将 word1 的前 i 个字符转换为空字符串需要 i 次删除
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];  // 保存当前 dp[i][j] 的值
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;  // 字符相同，不需要操作
                } else {
                    dp[j] = 1 + Math.min(dp[j], Math.min(dp[j - 1], prev));  // 计算删除、插入和替换
                }
                prev = temp;  // 更新 prev 为当前的 dp[i][j]
            }
        }

        return dp[n];  // 返回将 word1 转换为 word2 的最小操作次数
    }
}
