package stack;

import java.util.Stack;

/**
 * LeetCode 394. 字符串解码
 * 
 * 题目描述：
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 
 * 解题思路：
 * 使用两个栈来处理嵌套的编码字符串：一个栈用于存储重复次数，另一个栈用于存储当前构建的字符串。
 * 遍历输入字符串，根据字符类型进行不同处理：
 * - 数字：计算完整的重复次数并存入次数栈。
 * - '['：将当前字符串存入字符串栈，并重置当前字符串。
 * - ']'：从次数栈和字符串栈中弹出对应值，构建新的字符串。
 * - 字母：直接添加到当前字符串中。
 * 最终返回构建好的字符串。
 * 
 * 时间复杂度：O(n) 遍历输入字符串一次
 * 空间复杂度：O(n) 使用栈存储中间状态
 */

public class Code_394_DecodeString {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();

        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // 存储k
                countStack.push(k);
                // 暂存之前的encoded_string
                stringStack.push(currentString.toString());
                // 指向新的字符构造
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = new StringBuilder(stringStack.pop());
                int currentK = countStack.pop();
                for (int i = 0; i < currentK; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}
