package dp;

import java.util.Stack;

/**
 * LeetCode 32. 最长有效括号
 * 题目描述：
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 解题思路：
 * 1. 使用双指针扫描
 * 
 * 2. 使用栈
 * 
 * 3. 使用动态规划
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */

public class Code_32_LongestValidParentheses {
    // 双指针扫描
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        int left = 0, right = 0;

        // 从左到右扫描
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        // 从右到左扫描
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return maxLength;
    }

    // stack
    public int longestValidParenthesesStack(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化栈底索引

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 更新基准索引
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    // DP
    public int longestValidParenthesesDP(String s) {
        int maxLength = 0;
        int n = s.length();
        // dp[i] : 以i结尾的最长有效括号子串的长度
        int[] dp = new int[n];

        for (int i = 1; i < n; i++) {
            // 只有当s[i]是')'时，才可能形成有效括号
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 形如 "....()"
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 形如 "....))"
                    // i - 1 - dp[i - 1] 的含义是：在i之前的最长有效括号子串的前一个字符
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        return maxLength;
    }
}
