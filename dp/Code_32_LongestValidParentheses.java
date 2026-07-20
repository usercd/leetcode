package dp;

import java.util.Stack;

public class Code_32_LongestValidParentheses {

    // stack 时间复杂度：O(n) 空间复杂度：O(n)
    public int longestValidParenthesesStack(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    // DP 时间复杂度：O(n) 空间复杂度：O(n)
    public int longestValidParentheses2(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i >= 2 ? dp[i - 2] : 0);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (j >= 1) {
                            dp[i] += dp[j - 1];
                        }
                    }
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    // 双指针扫描 时间复杂度：O(n) 空间复杂度：O(1)
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

        // 仅从左向右扫描无法处理 "(()" 这种左括号过多的情况
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
}
