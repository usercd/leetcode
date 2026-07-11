package string;

/**
 * @author CD
 * @date 7/11/2026
 */
public class Code_43_MultiplyStrings {
    // 时间复杂度：O(m * n)，需要计算两个字符串的所有数位组合
    // 空间复杂度：O(m + n)，使用长度为 m + n 的结果数组
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        // 乘积最多有 m + n 位
        int[] product = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';

            for (int j = n - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                // product[i + j + 1]：保存当前两位相乘得到的个位部分
                int low = i + j + 1;
                // product[i + j]：保存向更高位产生的进位
                int high = i + j;

                int sum = product[low] + digit1 * digit2;
                product[low] = sum % 10;
                product[high] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();

        for (int digit : product) {
            // 跳过结果前导零，但至少保留一个数字。
            if (result.isEmpty() && digit == 0) {
                continue;
            }
            result.append(digit);
        }

        return result.isEmpty() ? "0" : result.toString();
    }
}
