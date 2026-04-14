package dp;
/**
 * LeetCode 3693. 爬楼梯 II
 * 
 * 题目描述：
 * 你正在爬一个有 n + 1 级台阶的楼梯，台阶编号从 0 到 n。你还得到了一个长度为 n 的 下标* 从 1 开始 的整数数组 costs，其中 costs[i] 是第 i 级台阶的成本。
 * 从第 i 级台阶，你只能跳到第 i + 1、i + 2 或 i + 3 级台阶。从第 i 级台阶跳到第 j 
 * 级台阶的成本定义为： costs[j] + (j - i)^2。你从第 0 级台阶开始，初始 cost = 0。
 * 
 * 返回到达第 n 级台阶所需的 最小 总成本。
 * 
 * 解题思路：
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Code_3693_ClimbingStairsII {
	public int climbStairs(int n, int[] costs) {
        if (n == 1) return costs[0] + 1;
        if (n == 2) return Math.min(costs[1]+4, costs[0] + costs[1] + 2);
        int first = 0, second = 1 + costs[0], third = Math.min(costs[1]+4, costs[0] + costs[1] + 2);
        int cost = 0;
        for (int i=2; i<n; i++) {
            cost = Math.min(Math.min(first + 9, second + 4), third + 1) + costs[i];
            first = second;
            second = third;
            third = cost;
        }

        return cost;
        
    }
}