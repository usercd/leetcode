package dp;

/**
 * LeetCode 70. 爬楼梯
 * 
 * 题目描述：
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 解题思路：
 * 使用动态规划求解，本质上是斐波那契数列
 * 1. 状态定义：dp[i] 表示爬到第 i 阶楼梯的方法数
 * 2. 状态转移：要爬到第 i 阶，可以从两个位置到达：
 *    - 从第 i-1 阶爬 1 步
 *    - 从第 i-2 阶爬 2 步
 *    所以：dp[i] = dp[i-1] + dp[i-2]
 * 3. 初始状态：
 *    - dp[1] = 1 (只有 1 种方法)
 *    - dp[2] = 2 (两种方法：1+1 或 2)
 * 4. 空间优化：只需要保存前两个状态，使用滚动变量代替数组
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Code_70_ClimbingStairs {
    public int climbStairs(int n) {
        // 边界条件：n <= 2 时，方法数等于 n
        if (n <= 2) {
            return n;
        }
        
        // first 表示 dp[i-2]，second 表示 dp[i-1]
        int first = 1;   // dp[1] = 1
        int second = 2;  // dp[2] = 2
        
        // 从第 3 阶开始计算
        for (int i = 3; i <= n; i++) {
            // third 表示 dp[i]
            // dp[i] = dp[i-1] + dp[i-2]
            int third = first + second;
            
            // 滚动更新：为下一轮做准备
            first = second;
            second = third;
        }
        
        return second;
    }
}
