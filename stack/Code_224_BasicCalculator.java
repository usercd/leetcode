package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author CD
 * @date 7/10/2026
 * 处理包含加减和()的表达式
 */
public class Code_224_BasicCalculator {

    public int calculate(String s) {
        Deque<Long> stack = new ArrayDeque<>();

        long result = 0;
        long num = 0;
        long sign = 1; // 1 表示加，-1 表示减

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                // 读取多位数字
                num = num * 10 + (current - '0');
            } else if (current == '+') {
                // 先处理前面的数字
                result += sign * num;
                num = 0;

                // 后面的数字使用正号
                sign = 1;
            } else if (current == '-') {
                // 先处理前面的数字
                result += sign * num;
                num = 0;

                // 后面的数字使用负号
                sign = -1;
            } else if (current == '(') {
                // 保存进入括号前的计算结果和符号
                stack.push(result);
                stack.push(sign);

                // 重新计算括号内部
                result = 0;
                sign = 1;
            } else if (current == ')') {
                // 先处理括号内最后一个数字
                result += sign * num;
                num = 0;

                // 恢复括号外的符号和结果
                long previousSign = stack.pop();
                long previousResult = stack.pop();

                // 括号整体相当于一个数字
                result = previousResult + previousSign * result;

                // 防止后续出现连续计算时受到影响
                sign = 1;
            }
            // 空格不需要处理
        }

        // 处理表达式末尾的数字
        result += sign * num;

        return (int) result;
    }
}
