package dp;

/**
 * 题目描述：
 * 一个机器人位于一个 m x n 网格的左上角（起始点）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（终点）。
 * 问总共有多少条不同的路径？
 * 
 * 解题思路：
 * 使用动态规划，创建一个二维数组 dp，其中 dp[i][j] 表示到达位置 (i, j) 的唯一路径数。
 * 初始化第一行和第一列的路径数为 1，因为从起点到达这些位置只有一种方式（一直向右或一直向下）。
 * 然后，对于其他位置 (i, j)，路径数等于从上方位置 (i-1, j) 和左方位置 (i, j-1) 到达该位置的路径数之和。
 * 最终，dp[m-1][n-1] 即为从起点到达终点的唯一路径数。
 * 时间复杂度为 O(m*n)，空间复杂度为 O(m*n)。
 */

public class Code_62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
