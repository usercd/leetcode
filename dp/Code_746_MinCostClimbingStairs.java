package dp;

/**
 * LeetCode 746. 使用最小花费爬楼梯
 * 
 * 题目描述：
 * 数组的每个元素代表了爬到该阶楼梯需要的花费，一旦你支付了相应的花费，
 * 你就可以选择爬一个或者两个台阶。你需要找到达到楼顶的最低花费。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示爬到第 i 阶楼梯的最低花费
 * 2. 状态转移：要爬到第 i 阶，可以从两个位置到达：
 *    - 从第 i-1 阶爬 1 步，花费为 dp[i-1] + cost[i-1]
 *    - 从第 i-2 阶爬 2 步，花费为 dp[i-2] + cost[i-2]
 *    所以：dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
 * 3. 初始状态：
 *    - dp[0] = 0 (不需要花费)
 *    - dp[1] = 0 (不需要花费)
 * 4. 空间优化：只需要保存前两个状态，使用滚动变量代替数组
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_746_MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (cost == null || n == 0) return 0;
        if (n == 1) return cost[0];
        
        // first 表示 dp[i-2]，second 表示 dp[i-1]
        int first = 0;  // 爬到第 0 阶的最低花费
        int second = 0; // 爬到第 1 阶的最低花费
        
        // 从第 2 阶开始计算
        for (int i = 2; i <= n; i++) {
            // current 表示 dp[i]
            // dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
            int current = Math.min(second + cost[i - 1], first + cost[i - 2]);
            
            // 滚动更新：为下一轮做准备
            first = second;
            second = current;
        }
        
        return second;
    } 
}
