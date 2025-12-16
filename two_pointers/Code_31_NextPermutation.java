package two_pointers;

/**
 * Next Permutation
 * 
 * Given an array of integers nums, find the next permutation of nums.
 * The replacement must be in place and use only constant extra memory.
 * 
 * 解题思路：
 * 排序依据字典序，寻找下一个更大的排列。
 * 1. 从后向前找到第一个降序位置 i，即 nums[i] < nums[i + 1]。
 * 2. 如果找到了这样的 i，再从后向前找到第一个大于 nums[i] 的位置 j，交换 nums[i] 和 nums[j]。
 * 3. 最后将 i 位置之后的元素反转，得到下一个排列。
 * 4. 如果没有找到降序位置 i，说明数组是最大排列，直接反转整个数组得到最小排列。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Code_31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
