package basic;

/**
 * 题目大意：
 * 给定一个整数 n，判断它是否是 2 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 
 * 解题思路：
 * 2 的幂次方在二进制表示中只有一个位是 1，其余位都是 0。
 * 因此，可以利用 n & (n - 1) 来判断，如果结果为 0，则说明 n 是 2 的幂次方。
 * 同时需要确保 n 大于 0。
 * 
 * 复杂度分析：
 * 时间复杂度：O(1)，只进行了一次位运算。
 * 空间复杂度：O(1)，只使用了常数级别的额外空间。
 */

public class Code_231_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}