package dp;

/**
 * @author CD
 * @date 7/8/2026
 */
public class Code_221_MaximalSquare {
    // 时间O(mn) 空间O(mn)
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];

        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // dp[i][j]表示以 (i-1,j-1) 为右下角的最大正方形边长。
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                } else {
                    dp[i][j] = 0;
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        return maxSide * maxSide;
    }

    // 空间优化
    // 时间O(mn) 空间O(n)
    public int maximalSquare1(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[] dp = new int[n + 1];

        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            int prev = 0; // 左上角dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // prev需要保存的是下一列需要的左上角
                if (matrix[i - 1][j - 1] == '1') {
                    // dp[j]相当于二维的dp[i-1][j]，同一个数组里左边是当前行，右边代表上一行的值
                    // Math.min(dp[j - 1], Math.min(dp[j], prev))
                    // dp[j-1]已经更新，代表当前行左边的值
                    // dp[j]还没有更新，代表上一行当前值即dp[i-1][j]
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], prev)) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }

        return maxSide * maxSide;
    }
}
