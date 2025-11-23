package basic;

/**
 * 137. Single Number II
 * Medium
 *
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * 解题思路:
 * 方法一: 遍历计数
 * 1. 使用一个长度为32的数组bitCount来记录每一位上1的个数
 * 2. 遍历数组，对于每个数字的每一位，如果该位是1，则对应的bitCount加1
 * 3. 最后对于bitCount中的每一位，如果该位的计数对3取模不为0，则说明单独出现的数字在该位上是1
 *
 * 方法二: 位运算
 * 1. 使用两个变量ones和twos来分别记录出现一次和出现两次的数字的状态
 * 2. 对于每个数字num，更新ones和twos的值
 * 3. 最终ones中存储的就是只出现一次的数字
 *
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class Code_137_SingleNumberII {
    // 遍历
    public int singleNumber(int[] nums) {
        int[] bitCount = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitCount[i] += (num >> i) & 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (bitCount[i] % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

    // 位运算
    public int singleNumberBitManipulation(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }
}