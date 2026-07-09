package dp;

/**
 * @author CD
 * @date 7/9/2026
 */
public class Code_494_TargetSum {
    // 回溯+DFS
    private int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                ans++;
            }
            return;
        }

        // 当前数字前放 '+'
        dfs(nums, target, index + 1, sum + nums[index]);

        // 当前数字前放 '-'
        dfs(nums, target, index + 1, sum - nums[index]);
    }

    // 转化为01背包问题
    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (Math.abs(target) > sum) return 0;
        if ((sum + target) % 2 == 1) return 0;

        int p = (sum + target) / 2;

        // dp[i] 代表结果为i的方案数
        int[] dp = new int[p + 1];
        dp[0] = 1;
        int n = nums.length;
        // 容量 0~target
        // 物品 1~n
        for (int i = 0; i < n; i++) {
            for (int j = p; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[p];
    }
}
