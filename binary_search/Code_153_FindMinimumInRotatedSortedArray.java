package binary_search;

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
