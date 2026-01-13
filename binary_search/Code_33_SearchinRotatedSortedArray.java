package binary_search;

/**
 * LeetCode 33. 搜索旋转排序数组
 * 
 * 题目描述：
 * 给定一个升序排列的整数数组 nums ，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] （下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 
 * 解题思路：
 * 使用二分查找的方法，在每次迭代中确定哪一半是有序的，然后判断目标值是否在有序半部分中，
 * 如果在，则继续在该半部分搜索；否则，在另一半部分搜索。
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 */

public class Code_33_SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判断哪一半是有序的
            if (nums[left] <= nums[mid]) { // 左半部分有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 目标在左半部分
                } else {
                    left = mid + 1; // 目标在右半部分
                }
            } else { // 右半部分有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // 目标在右半部分
                } else {
                    right = mid - 1; // 目标在左半部分
                }
            }
        }

        return -1; // 未找到目标
    }

    public static void main(String[] args) {
        Code_33_SearchinRotatedSortedArray solver = new Code_33_SearchinRotatedSortedArray();
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(solver.search(nums, target)); // Output: 4

        target = 3;
        System.out.println(solver.search(nums, target)); // Output: -1

        nums = new int[]{1};
        target = 0;
        System.out.println(solver.search(nums, target)); // Output: -1
    }
}
