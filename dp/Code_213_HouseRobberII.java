package dp;

/**
 * LeetCode 213. 打家劫舍 II
 * 
 * 题目描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，
 * 一夜之内能够偷窃到的最高金额。所有的房屋都围成了一圈，这意味着第一个房屋和最后一个房屋是相邻的。
 * 
 * 解题思路：
 * 将问题分解为两个子问题：
 * 1. 偷窃前 n-1 个房屋（不包括最后一个房屋）
 * 2. 偷窃后 n-1 个房屋（不包括第一个房屋）
 * 分别计算这两个子问题的最大金额，取两者中的最大值即为最终结果。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Code_213_HouseRobberII {
   public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int first = 0, second = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        return second;
    }

    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int first = helper(nums, 0, nums.length - 1);
        int second = helper(nums, 1, nums.length);
        return first > second ? first : second;
    }

    private static int helper(int[] nums, int left, int right) {
        int first = nums[left];
        int second = Math.max(nums[left], nums[left+1]);
        int cur = 0;
        for (int i=left+2; i<right; i++) {
            cur = Math.max(nums[i] + first, second);
            first = second;
            second = cur;
        }
        return second;
    }
}
