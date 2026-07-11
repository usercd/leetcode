package string;

/**
 * @author CD
 * @date 7/11/2026
 */
public class Code_415_AddStrings {
    // 时间复杂度：O(max(m, n))，其中 m、n 是两个字符串的长度 空间复杂度：O(max(m, n))
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }
        return result.reverse().toString();
    }

    public String addStrings1(String num1, String num2) {
        int len1 = num1.length() - 1, len2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (len1 >= 0 && len2 >= 0) {
            int p1 = num1.charAt(len1) - '0';
            int p2 = num2.charAt(len2) - '0';
            int sum = (p1 + p2 + carry) % 10;
            carry = (p1 + p2 + carry) / 10;
            sb.append(sum);
            len1--;
            len2--;
        }

        while (len1 >= 0) {
            int p1 = num1.charAt(len1) - '0';
            int sum = (p1 + carry) % 10;
            carry = (p1 + carry) / 10;
            sb.append(sum);
            len1--;
        }

        while (len2 >= 0) {
            int p2 = num2.charAt(len2) - '0';
            int sum = (p2 + carry) % 10;
            carry = (p2 + carry) / 10;
            sb.append(sum);
            len2--;
        }

        if (carry != 0) sb.append(1);

        return sb.reverse().toString();
    }
}
