package stack;

import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 20. 有效的括号
 * 
 * 题目描述：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 
 * 解题思路：
 * 使用栈（Stack）数据结构来匹配括号。
 * 遍历字符串，对于每个左括号，将对应的右括号压入栈中；
 * 对于每个右括号，检查栈顶元素是否与之匹配，若不匹配或栈为空则返回 false。
 * 最终检查栈是否为空，若为空则表示所有括号均匹配成功。
 * 
 * 时间复杂度：O(n) 遍历字符串一次
 * 空间复杂度：O(n) 最坏情况下栈中存储所有左括号
 */

public class Code_20_ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // map
    public boolean isValid2(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
