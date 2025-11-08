package basic;

public class Code_53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < n; i++) {
            // 如果 currentSum 为负数，重新开始计算子数组和
            if (currentSum < 0) {
                currentSum = nums[i];
            } else {
                currentSum += nums[i];
            }
            // 更新最大子数组和
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
