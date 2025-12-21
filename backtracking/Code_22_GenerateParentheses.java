package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Code_22_GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;    
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // open 已经使用左括号的数量 close 已经使用右括号的数量
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
