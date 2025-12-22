package jz;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组里的所有数字都在 0 ~ n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为 7 的数组 {2, 3, 1, 0, 2, 5, 3}, 那么对应的输出是重复的数字 2 或者 3 
 * 要求时间复杂度 O(N)，空间复杂度 O(1)。
 * 
 * 解题思路：
 * 利用数组下标和数字范围的特点，将数字放到对应的下标位置上。
 * 遍历数组，对于每个数字 nums[i]，如果它不在正确的位置（即 nums[i] != i），
 * 则将它与它应该在的位置 nums[nums[i]] 进行交换。
 * 如果在交换过程中发现 nums[i] 已经等于 nums[nums[i]]，则说明找到了重复的数字，直接返回即可。
 * 时间复杂度为 O(N)，因为每个数字最多只会被交换一次。
 * 空间复杂度为 O(1)，因为只使用了常数级别的额外空间。
 */
public class JZ003_FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }

    // 如果不允许修改数组，而且空间复杂度为 O(1) 的话，可以使用二分查找的方法，时间复杂度为 O(N log N)
    public int findRepeatNumberWithoutModify(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = countRange(nums, left, mid);
            if (left == right) {
                if (count > 1) {
                    return left;
                } else {
                    break;
                }
            }
            if (count > (mid - left + 1)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int countRange(int[] nums, int left, int right) {
        int count = 0;
        for (int num : nums) {
            if (num >= left && num <= right) {
                count++;
            }
        }
        return count;
    }
}