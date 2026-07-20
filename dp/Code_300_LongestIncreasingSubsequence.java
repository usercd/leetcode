package dp;

/**
 * LeetCode 300. 最长递增子序列
 * 
 * 题目描述：
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 解法二（进阶）：
 * 使用贪心算法结合二分查找优化到 O(n log n)
 * 1. 维护一个数组 tails，其中 tails[k] 表示长度为 k+1 的递增子序列的最小结尾元素
 * 2. 遍历 nums，对于每个元素 num，使用二分查找在 tails 中找到第一个大于等于 num 的位置 idx
 *    - 如果 idx 等于 tails 的长度，说明 num 比 tails 中所有元素都大，直接将 num 添加到 tails 末尾
 *    - 否则，将 tails[idx] 更新为 num
 * 3. 返回 tails 的长度即为最长递增子序列的长度
 */

public class Code_300_LongestIncreasingSubsequence {
    // 解法一：动态规划 时间复杂度：O(n^2) 空间复杂度：O(n)
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // 每个元素至少可以组成长度为 1 的递增子序列
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    // 解法二：DP + 二分查找  时间复杂度：O(nlog n) 空间复杂度：O(n)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // tails[k] 表示长度为 k+1 的递增子序列的最小结尾元素
        int[] tails = new int[n];
        // 当前递增子序列的长度
        int size = 0;

        for (int num : nums) {
            int left = 0;
            int right = size;
            // 在 tails 中使用二分查找找到第一个大于等于 num 的位置
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 直接替换，对于相同长度的递增子序列，我们始终保留结尾最小的那个
            // 贪心策略，更小意味着后面添加的元素有可能更多
            tails[left] = num;
            if (left == size) {
                size++;
            }
        }

        return size;
    }
}
