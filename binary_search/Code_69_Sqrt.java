package binary_search;

/**
 * @author CD
 * @date 7/1/2026
 */
public class Code_69_Sqrt {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        // left 和 right 代表可能的范围闭合区间
        int left = 1, right = x / 2 + 1, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
