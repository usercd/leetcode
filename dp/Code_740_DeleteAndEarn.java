package dp;

/**
 * LeetCode 740. 删除并获得点数
 * 
 * 题目描述：
 * 给你一个整数数组 nums，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i]，删除它并获得 nums[i] 的点数。之后，你必须删除所有等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * 
 * 解题思路：
 * 将问题转化为打家劫舍问题
 * 1. 首先统计每个数字出现的总点数，存储在一个数组中，其中索引表示数字，值表示该数字的总点数。
 * 2. 然后使用动态规划求解这个新的数组，类似于打家劫舍问题，因为选择一个数字会影响相邻的数字。
 * 
 * 时间复杂度：O(n + m)，其中 n 是输入数组的长度，m 是最大数字的值。
 * 空间复杂度：O(m)，用于存储每个数字的总点数。
 */

public class Code_740_DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] arr = new int[max + 1];
        for (int num : nums) {
            arr[num] += num;
        }
        int first = 0, second = arr[0];
        for (int i = 1; i <= max; i++) {
            int temp = Math.max(first + arr[i], second);
            first = second;
            second = temp;
        }
        return second;
    }
}
