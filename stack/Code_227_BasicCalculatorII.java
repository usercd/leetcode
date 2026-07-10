package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author CD
 * @date 7/10/2026
 * 处理包含加减乘除的表达式
 */
public class Code_227_BasicCalculatorII {
    public int calculate(String s) {
        Deque<Long> stack = new ArrayDeque<>();

        long num = 0;
        char sign = '+';

        for (int i = 0; i <= s.length(); i++) {
            char current;

            // i == s.length() 时，使用一个虚拟运算符，用于处理表达式最后的数字
            if (i == s.length()) {
                current = '+';
            } else {
                current = s.charAt(i);
            }

            if (Character.isDigit(current)) {
                num = num * 10 + (current - '0');
            }

            // 遇到运算符或已经到达字符串末尾，处理前面读取到的数字
            if (i == s.length()
                    || (!Character.isDigit(current) && current != ' ')) {

                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;

                    case '-':
                        stack.push(-num);
                        break;

                    case '*':
                        stack.push(stack.pop() * num);
                        break;

                    case '/':
                        stack.push(stack.pop() / num);
                        break;

                    default:
                        break;
                }

                // 当前运算符留给下一个数字使用
                sign = current;
                num = 0;
            }
        }

        long result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return (int) result;
    }
}
