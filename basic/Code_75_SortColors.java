package basic;

/**
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * 解题思路:
 * 使用三指针法（Dutch National Flag算法）。维护三个指针low、mid和high。
 * low指向下一个0应该放置的位置，mid用于遍历数组，high指向下一个2应该放置的位置。
 * 遍历数组时，根据nums[mid]的值进行交换和指针移动，直到mid超过high。
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class Code_75_SortColors {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
