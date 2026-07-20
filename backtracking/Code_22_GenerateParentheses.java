package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22. 括号生成
 * 
 * 题目描述：
 * 给出 n 对括号，请写一个函数来生成所有可能的并且有效的括号组合。
 * 
 * 解题思路：
 * 使用回溯法（Backtracking）生成所有可能的括号组合。
 * 维护当前使用的左括号和右括号的数量，确保在任何时候右括号的数量不超过左括号的数量。
 * 当使用的左括号和右括号数量都达到 n 时，记录当前组合。
 * 
 * 时间复杂度：O(4^n / sqrt(n))，卡特兰数复杂度
 * 空间复杂度：O(n)
 */

public class Code_22_GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;    
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == 2 * max) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append("(");
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(")");
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }

    }
}
