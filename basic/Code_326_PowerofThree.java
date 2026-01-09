package basic;

/**
 * 题目大意：
 * 给定一个整数 n，判断它是否是 3 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 
 * 解题思路：
 * 3 的幂次方在不断除以 3 后最终会变为 1。
 * 因此，可以通过循环将 n 不断除以 3，直到 n 不再能被 3 整除。
 * 最后检查 n 是否等于 1，如果是，则说明 n 是 3 的幂次方。
 * 
 * 复杂度分析：
 * 时间复杂度：O(log n)，每次循环将 n 除以 3。
 * 空间复杂度：O(1)，只使用了常数级别的额外空间。
 */
public class Code_326_PowerofThree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
