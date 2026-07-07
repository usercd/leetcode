package dp;

/**
 * @author CD
 * @date 7/1/2026
 */
public class Code_122_BestTimeToBuyAndSellStockII {
    // 贪心
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            result += Math.max(prices[i + 1] - prices[i], 0);
        }
        return result;
    }

    // DP
    public int maxProfit1(int[] prices) {
        int cash = 0, hold = -prices[0];
        for (int i=1; i<prices.length; i++) {
            // 空仓或者卖出
            cash = Math.max(cash, prices[i] + hold);
            // 继续持仓或者买入
            hold = Math.max(hold, cash - prices[i]);
        }

        return cash;
    }
}
