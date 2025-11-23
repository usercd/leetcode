package basic;

/**
 * 287. Find the Duplicate Number
 * Medium
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * 解题思路:
 * 方法一: 快慢指针 (Floyd's Tortoise and Hare)
 * 1. 使用两个指针，慢指针每次走一步，快指针每次走两步，直到它们相遇
 * 2. 将慢指针移到起点，然后两个指针每次都走一步，直到它们再次相遇，相遇点即为重复的数字
 *
 * 方法二: 二分查找
 * 1. 使用二分查找在范围[1, n]内查找重复的数字
 * 2. 对于每个中间值mid，计算数组中小于等于mid的数字的数量count
 * 3. 如果count > mid，说明重复的数字在[left, mid]范围内，否则在[mid + 1, right]范围内
 * 时间复杂度: O(n) 或 O(n log n)
 * 空间复杂度: O(1)
 */

public class Code_287_FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[0];
        // Phase 1: Finding the intersection point in the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // Phase 2: Finding the entrance to the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // binary search method
    public int findDuplicateBinarySearch(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}