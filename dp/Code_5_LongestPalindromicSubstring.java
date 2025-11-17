package dp;

/**
 * 题目描述：
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 
 * 解题思路：
 * 使用动态规划，创建一个二维数组 dp，其中 dp[i][j] 表示子串 s[i..j] 是否为回文串。
 * 初始化时，所有长度为 1 的子串都是回文串，即 dp[i][i] = true。
 * 然后检查长度为 2 的子串，如果两个字符相同，则该子串为回文串。
 * 对于长度大于 2 的子串 s[i..j]，如果 s.charAt(i) == s.charAt(j) 且 dp[i+1][j-1] 为真，则
 * dp[i][j] 为真。
 * 在填充 dp 数组的过程中，记录最长回文子串的起始位置和长度。
 * 最终返回最长回文子串。
 * 时间复杂度为 O(n^2)，空间复杂度为 O(n^2)。
 */
public class Code_5_LongestPalindromicSubstring {

    // DP
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0)
            return "";

        boolean[][] dp = new boolean[n][n];
        int maxLength = 1;
        int start = 0;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for substrings of length greater than 2
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i < n - length + 1; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (length > maxLength) {
                        start = i;
                        maxLength = length;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    // Expand Around Center
    public String longestPalindrome2(String s) {
        int n = s.length();
        if (n == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(s, i, i); // Odd length palindromes
            int len2 = expandAroundCenter(s, i, i + 1); // Even length palindromes
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // Manacher
    public String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) return "";
        // Transform s into T.
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        String T = sb.toString();
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int mirr = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[mirr]);
            } else {
                P[i] = 0;
            }
            // Attempt to expand palindrome centered at i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }
            // If palindrome centered at i expand past R, adjust center based on expanded palindrome.
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
}