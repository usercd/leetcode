package basic;

/**
 * 1. 从后向前找到第一个降序的位置i，即nums[i] < nums[i + 1]
 * 2. 如果找到了这样的i，再从后向前找到第一个大于nums[i]的位置j，交换nums[i]和nums[j]
 * 3. 最后将i之后的部分反转
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */

public class Code_31_NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}