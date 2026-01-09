package basic;

/**
 * 题目大意：
 * 给定一个整数 n，判断它是否是 4 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 
 * 解题思路：
 * 4 的幂次方在二进制表示中只有一个位是 1，其余位都是 0（与 2 的幂次方相同）。
 * 因此，首先可以利用 n & (n - 1) 来判断 n 是否是 2 的幂次方。
 * 然后，4 的幂次方满足 n - 1 能被 3 整除的特性。
 * 综合这两点，可以判断 n 是否是 4 的幂次方。
 * 
 * 复杂度分析：
 * 时间复杂度：O(1)，只进行了几次位运算和取模运算。
 * 空间复杂度：O(1)，只使用了常数级别的额外空间。
 */

public class Code_342_PowerOfFour {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return (n - 1) % 3 == 0;
    }
}
