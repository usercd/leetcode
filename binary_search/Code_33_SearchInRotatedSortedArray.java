package binary_search;

public class Code_33_SearchInRotatedSortedArray {

    // 时间复杂度：O(log n) 空间复杂度：O(1)
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

        return -1;
    }

}
