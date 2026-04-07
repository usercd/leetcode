package dp;

import java.util.Arrays;

/**
 * LeetCode 198. 打家劫舍
 * 
 * 题目描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，
 * 一夜之内能够偷窃到的最高金额。
 * 
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示偷窃前 i 个房屋能获得的最大金额
 * 2. 状态转移：对于第 i 个房屋，有两种选择：
 *    - 偷第 i 个房屋：dp[i] = dp[i-2] + nums[i] (不能偷 i-1)
 *    - 不偷第 i 个房屋：dp[i] = dp[i-1]
 *    取两者最大值：dp[i] = max(dp[i-2] + nums[i], dp[i-1])
 * 3. 初始状态：dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
 * 4. 空间优化：只需要保存前两个状态，使用滚动变量代替数组
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Code_198_HouseRobber {

    // 递归 + 记忆化搜索
    public int rob0(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        return dfs(nums.length - 1, nums, arr);
    }

    private int dfs(int n, int[] nums, int[] arr) {
        if (n < 0) return 0;
        if (arr[n] != -1) return arr[n];
        int res = Math.max(dfs(n - 2, nums, arr) + nums[n], dfs(n - 1, nums, arr));
        arr[n] = res;
        return res;
    }

    // 递推
    public int rob1(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        // arr[i] 表示偷窃前 i-1 个房屋能获得的最大金额
        int[] arr = new int[n+2];
        // 从第 2 个房屋开始遍历
        for (int i = 0; i < n; i++) {
            arr[i+2] = Math.max(arr[i] + nums[i], arr[i+1]);
        }

        return arr[n+1];
    }

    public int rob2(int[] nums) {
        // 边界条件处理
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        
        // first 表示 dp[i-2]，second 表示 dp[i-1]
        int first = nums[0];  // 只偷第 0 个房屋
        int second = Math.max(nums[0], nums[1]);  // 偷前两个房屋的最大值
        
        for (int i = 2; i < n; i++) {
            // current 表示 dp[i]
            int current = Math.max(first + nums[i], second);
            
            // 滚动更新：为下一轮做准备
            first = second;
            second = current;
        }
        
        return second;
    }

    public int rob3(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) return 0;
        if (n == 1) return nums[0];
        
        int first = 0, second = 0;
        
        for (int i = 0; i < n; i++) {
            int current = Math.max(first + nums[i], second);
            first = second;
            second = current;
        }
        
        return second;
    }
}
