package twopointers;

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
