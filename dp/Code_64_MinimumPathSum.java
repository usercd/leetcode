package dp;

/**
 * 题目描述：
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 解题思路：
 * 使用动态规划，创建一个二维数组 dp，其中 dp[i][j] 表示到达位置 (i, j) 的最小路径和。
 * 初始化 dp[0][0] 为 grid[0][0]，然后初始化第一行和第一列，因为这些位置只能从一个方向到达。
 * 对于其他位置 (i, j)，最小路径和等于从上方位置 (i-1, j) 和左方位置 (i, j-1) 到达该位置的最小路径和中的较小值加上当前格子的值 grid[i][j]。
 * 最终，dp[m-1][n-1] 即为从起点到达终点的最小路径和。
 * 时间复杂度为 O(m*n)，空间复杂度为 O(m*n)。
 */

public class Code_64_MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        // Fill the rest of the dp array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
