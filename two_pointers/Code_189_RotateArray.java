package two_pointers;

/**
 * 189. Rotate Array
 * 题目大意：
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * 解题思路：
 * 使用三次反转的方法。首先反转整个数组，然后反转前 k 个元素，最后反转剩下的元素。
 * 或者先反转前 n-k 个元素，再反转后 k 个元素，最后反转整个数组。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是数组的长度。需要遍历数组三次。
 * 空间复杂度：O(1)，只使用了常数级别的额外空间。
 */

public class Code_189_RotateArray {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
