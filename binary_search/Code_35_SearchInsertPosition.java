package binary_search;

/**
 * LeetCode 35. 搜索插入位置
 * 
 * 题目描述：
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 
 * 解题思路：
 * 使用二分查找的方法，在每次迭代中比较中间元素与目标值的大小，
 * 根据比较结果调整搜索范围，直到找到目标值或确定插入位置。
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 */

public class Code_35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
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
        return left;
    }

    public static void main(String[] args) {
        Code_35_SearchInsertPosition solver = new Code_35_SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(solver.searchInsert(nums, target)); // Output: 2

        target = 2;
        System.out.println(solver.searchInsert(nums, target)); // Output: 1

        target = 7;
        System.out.println(solver.searchInsert(nums, target)); // Output: 4

        target = 0;
        System.out.println(solver.searchInsert(nums, target)); // Output: 0
    }
}
