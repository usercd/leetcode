package sliding_window;

/**
 * LeetCode 209. 长度最小的子数组
 * 
 * 题目描述：
 * 给定一个含有 n 个正整数的数组和一个正整数 target ，找出该数组中满足其和 ≥ target 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 * 
 * 解题思路：
 * 使用滑动窗口算法求解
 * 1. 使用两个指针 left 和 right 定义一个窗口，初始时都指向数组的开头
 * 2. 扩展右指针 right，直到窗口内的元素和大于等于 target
 * 3. 收缩左指针 left，尝试找到更小的满足条件的窗口
 * 4. 重复步骤 2 和 3，直到右指针到达数组的末尾
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0;
        int sum = 0;
        int minLength = nums.length + 1;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLength == nums.length + 1 ? 0 : minLength;
    }
}
