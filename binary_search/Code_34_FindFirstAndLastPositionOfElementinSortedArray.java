package binary_search;

/**
 * LeetCode 34. 在排序数组中查找元素的第一个和最后一个位置
 * 
 * 题目描述：
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 
 * 解题思路：
 * 使用二分查找的方法分别查找目标值的左边界和右边界。
 * 查找左边界时，当 mid 位置的值大于等于 target 时，收缩右边界；
 * 查找右边界时，当 mid 位置的值小于等于 target 时，收缩左边界。
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 */

public class Code_34_FindFirstAndLastPositionOfElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 查找左边界
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return result;
        }
        result[0] = left;

        // 查找右边界
        right = nums.length - 1; // 重置右指针
        while (left < right) {
            int mid = left + (right - left + 1) / 2; // 注意这里的取中位数方式
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        result[1] = right;

        return result;
    }

    public static void main(String[] args) {
        Code_34_FindFirstAndLastPositionOfElementinSortedArray solver = new Code_34_FindFirstAndLastPositionOfElementinSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 6;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 5;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 10;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");
    }
}
