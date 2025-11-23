package basic;

/**
 * 136. Single Number
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * 解题思路:
 * 使用异或运算的性质：a ^ a = 0 和 a ^ 0 = a。遍历数组，对每个元素进行异或运算，最终结果即为只出现一次的元素。
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */

public class Code_136_SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
