package dp;

import java.util.Arrays;

public class Code_983_MinimumCostForTickets {
    public static final int[] duration = {1, 7, 30};
    
    // 空间优化的动态规划
    public static int mincostTickets1(int[] days, int[] costs) {
        int n = days.length;
        // dp[i] : 从第i天开始，后续的最少花费
	    int[] dp = new int[n+1];
		Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
        // 后续已经无旅行了
		dp[n] = 0;
        // 从后往前遍历，dp[i]依赖于dp[j]，j>i
		for (int i = n - 1; i >= 0; i--) {
            // 枚举三种方案
			for (int k = 0, j = i; k < 3; k++) {
                // k是方案编号 : 0 1 2
                // j是下标，枚举方案k持续的天数，找到第一个不受方案k覆盖的旅行
				while (j < days.length && days[i] + duration[k] > days[j]) {
					j++;
				}
                // 方案k的花费 + 后续的花费
				dp[i] = Math.min(dp[i], costs[k] + dp[j]);
			}
		}
		return dp[0];
	}

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, -1);
        return dfs(days, costs, 0, dp);
    }

    // 记忆化搜索 
    private int dfs(int[] days, int[] costs, int i, int[] dp) {
        // 后续已经无旅行了
        if (i == days.length) return 0;
        // i下标 : 第days[i]天，有一场旅行
		// i.... 最少花费是多少 
        if (dp[i] != -1) {
            return dp[i];
        }
		int ans = Integer.MAX_VALUE;
        // 枚举三种方案
		for (int k = 0, j = i; k < 3; k++) {
			// k是方案编号 : 0 1 2
            // j是下标，枚举方案k持续的天数，找到第一个不受方案k覆盖的旅行
			while (j < days.length && days[i] + duration[k] > days[j]) {
				// 因为方案2持续的天数最多，30天
				// 所以while循环最多执行30次
				// 枚举行为可以认为是O(1)
				j++;
			}
            // 方案k的花费 + 后续的花费
			ans = Math.min(ans, costs[k] + dfs(days, costs, j, dp));
		}
		dp[i] = ans;
		return ans;
    }

    // 暴力解法会超时 时间复杂度是O(3^n)，n是旅行的天数
    // days[i..... 最少花费是多少 
    private int dfs1(int[] days, int[] costs, int i) {
        // 后续已经无旅行了
        if (i == days.length) return 0;
        // i下标 : 第days[i]天，有一场旅行
		// i.... 最少花费是多少 
		int ans = Integer.MAX_VALUE;
        // 枚举三种方案
		for (int k = 0, j = i; k < 3; k++) {
			// k是方案编号 : 0 1 2
            // j是下标，枚举方案k持续的天数，找到第一个不受方案k覆盖的旅行
			while (j < days.length && days[i] + duration[k] > days[j]) {
				// 因为方案2持续的天数最多，30天
				// 所以while循环最多执行30次
				// 枚举行为可以认为是O(1)
				j++;
			}
            // 方案k的花费 + 后续的花费
			ans = Math.min(ans, costs[k] + dfs1(days, costs, j));
		}
		return ans;
    }
    
}
