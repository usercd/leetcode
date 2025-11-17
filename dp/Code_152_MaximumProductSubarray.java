package dp;

/**
 * LeetCode 152. 乘积最大子数组
 * 题目描述：
 * 给你一个整数数组 nums ，
 * 请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示以 nums[i] 结尾的子数组的最大乘积
 * 2. 状态转移：
 *    由于乘积可能为负数，我们还需要维护一个最小乘积 minProduct[i]
 *    dp[i] = max(nums[i], nums[i] * maxProduct[i-1], nums[i] * minProduct[i-1])
 *    minProduct[i] = min(nums[i], nums[i] * maxProduct[i-1], nums[i] * minProduct[i-1])
 * 3. 初始状态：maxProduct[0] = nums[0], minProduct[0] = nums[0]
 * 4. 返回结果： 遍历 maxProduct 数组，取最大值
 * 空间优化：只需要保存前一个状态，使用滚动变量代替数组
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_152_MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) {
                // 交换最大和最小乘积
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }
            
            // 更新最大和最小乘积
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);
            
            // 更新结果
            result = Math.max(result, maxProduct);
        }
        
        return result;
    }
}
