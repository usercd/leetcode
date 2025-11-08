package two_pointers;

/**
 * LeetCode 283. 移动零
 * 
 * 题目描述：
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 * 
 * 解题思路：
 * 使用双指针法
 * 1. 定义两个指针：left（慢指针）和 right（快指针）
 * 2. 快指针遍历数组，当遇到非零元素时，将其与慢指针位置的元素交换，并移动慢指针
 * 3. 最终所有非零元素会被移动到数组前部，零元素自然会被移动到末尾
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0; // 慢指针，指向下一个非零元素应放置的位置

        // 快指针遍历数组
        for (int right = 0; right < n; right++) {
            if (nums[right] != 0) {
                // 交换非零元素到左侧
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++; // 移动慢指针
            }
        }
    }
}
