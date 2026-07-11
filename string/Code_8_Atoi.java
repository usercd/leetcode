package string;

/**
 * @author CD
 * @date 7/11/2026
 */
public class Code_8_Atoi {
    // 时间O(n) 空间O(1)
    public int myAtoi(String s) {
        int n = s.length();
        int index = 0;

        // 1. 跳过前导空格
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // 2. 处理符号，默认正数。
        int sign = 1;
        if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        // 负数允许的绝对值比正数多 1。
        long limit = sign == 1 ? Integer.MAX_VALUE : -(long) Integer.MIN_VALUE;
        long value = 0;

        // 3. 连续读取数字。
        while (index < n) {
            char ch = s.charAt(index);

            if (ch < '0' || ch > '9') {
                break;
            }

            int digit = ch - '0';

            // 在 value * 10 + digit 前判断是否超过对应边界。
            if (value > (limit - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            value = value * 10 + digit;
            index++;
        }

        return sign == 1 ? (int) value : (int) -value;
    }
}
