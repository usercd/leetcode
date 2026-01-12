package binary_search;

/**
 * LeetCode 704. 二分查找
 * 
 * 题目描述：
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 
 * 解题思路：
 * 使用二分查找算法
 * 1. 初始化两个指针 left 和 right，分别指向数组的起始和结束位置
 * 2. 计算中间位置 mid，并比较 nums[mid] 与 target 的大小
 * 3. 如果 nums[mid] 等于 target，返回 mid
 * 4. 如果 nums[mid] 小于 target，说明目标值在右半部分，更新 left 为 mid + 1
 * 5. 如果 nums[mid] 大于 target，说明目标值在左半部分，更新 right 为 mid - 1
 * 6. 重复上述过程直到 left 超过 right
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 */

public class Code_704_BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // 未找到目标值
    }
}
