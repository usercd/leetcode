package dp;

/**
 * LeetCode 121. 买卖股票的最佳时机
 * 
 * 题目描述：
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。返回你可以获得的最大利润。如果你不能获取任何利润，返回 0 。
 * 
 * 解题思路：
 * 使用一次遍历，维护当前的最低价格和最大利润。
 * 对于每个价格，更新最低价格，并计算当前价格与最低价格的差值以更新最大利润。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
