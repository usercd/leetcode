package dp;

import java.util.Arrays;

public class Code_91_DecodeWays {

    // 空间优化的动态规划
    public int numDecodings(String s) {
        int n = s.length();
        // int[] dp = new int[n+1];
        int f0 = 1;
        int f1 = 0;
        for (int i=n-1, cur; i>=0; i--) {
            if (s.charAt(i) != '0') {
                cur = f0;
                if (i+1 < s.length() && (s.charAt(i) - '0') * 10 + (s.charAt(i+1) - '0') < 27) {
                    f0 += f1;
                }
            } else {
                cur = 0;
            }
            f1 = f0;
            f0 = cur;
        }
        return f0;
    }

    // 递推
    public int numDecodings2(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = 1;
        for (int i=n-1; i>=0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i+1];
                if (i+1 < s.length() && (s.charAt(i) - '0') * 10 + (s.charAt(i+1) - '0') < 27) {
                    dp[i] += dp[i+2];
                }
            } else {
                dp[i] = 0;
            }        
        }
        return dp[0];
    }

    public int numDecodings1(String s) {
        // dp[i] : 从i下标开始，后续的解码方法数
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return dfs(s, dp, 0);
    }

    // 记忆化搜索 i下标 : s[i...] 的解码方法数
    private int dfs(String s, int[] dp, int i) {
        // 后续已经无字符了，说明之前的字符都成功解码了，算一种方法
        if (i == s.length()) return 1;
        if (dp[i] != -1) return dp[i];
        int ans = 0;
        // s[i]不能单独解码成0，所以s[i]必须是1-9
        if (s.charAt(i) != '0') {
            // s[i]单独解码成1-9，后续的解码方法数是dfs(s, dp, i+1)
            ans += dfs(s, dp, i+1);
            // s[i]和s[i+1]一起解码成10-26，后续的解码方法数是dfs(s, dp, i+2)
            if (i+1 < s.length() && (s.charAt(i) - '0') * 10 + (s.charAt(i+1) - '0') < 27) {
                ans += dfs(s, dp, i+2);
            }
        }
        dp[i] = ans;
        return ans;
    }
}
