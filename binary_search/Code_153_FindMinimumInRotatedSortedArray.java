package binary_search;

/**
 * LeetCode 153. 寻找旋转排序数组中的最小值
 * 
 * 题目描述：
 * 已知一个长度为 n 的数组，预先按照升序排列，
 * 经由 1 到 n 次旋转后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 请找出其中最小的元素。
 * 假设数组中不存在重复元素。
 * 
 * 解题思路：
 * 使用二分查找的方法，通过比较中间元素与右端元素的大小关系，
 * 确定最小值所在的区间，逐步缩小搜索范围，直到找到最小值。
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(1)
 */

public class Code_153_FindMinimumInRotatedSortedArray {
    /**
     * 寻找旋转排序数组中的最小值
     * 假设数组中不存在重复元素
     * 
     * @param nums 旋转排序数组
     * @return 返回数组中的最小值
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 如果数组未旋转，直接返回第一个元素
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 判断mid所在的部分是有序还是无序
            if (nums[mid] > nums[right]) {
                // mid在左半部分，最小值在右半部分
                left = mid + 1;
            } else {
                // mid在右半部分，最小值在左半部分（包括mid）
                right = mid;
            }
        }

        // 循环结束时，left == right，指向最小值
        return nums[left];
    }

    public static void main(String[] args) {
        Code_153_FindMinimumInRotatedSortedArray solver = new Code_153_FindMinimumInRotatedSortedArray();
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(solver.findMin(nums1)); // Output: 1

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solver.findMin(nums2)); // Output: 0

        int[] nums3 = {11, 13, 15, 17};
        System.out.println(solver.findMin(nums3)); // Output: 11
    }
}
