package basic;

/**
 * 169. Majority Element
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * 解题思路:
 * 使用Boyer-Moore投票算法。遍历数组，维护一个候选元素和计数器。
 * 当计数器为0时，更新候选元素为当前元素。
 * 对于每个元素，如果它等于候选元素，计数器加1，否则减1。最终候选元素即为众数。
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */

public class Code_169_MajorityElement {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
