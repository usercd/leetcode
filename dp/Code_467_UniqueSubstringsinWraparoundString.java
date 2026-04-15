package dp;

public class Code_467_UniqueSubstringsinWraparoundString {
    public int findSubstringInWraproundString(String p) {
        // dp[i] : 以i结尾的最长连续子串的长度
        int[] dp = new int[26];
        int maxLength = 0;
        for (int i=0, cur=0; i<p.length(); i++) {
            // cur : 以p[i]结尾的最长连续子串的长度
            if (i > 0 && (p.charAt(i) - p.charAt(i-1) + 26) % 26 == 1) {
                cur++;
            } else {
                cur = 1;
            }
            // 更新以p[i]结尾的最长连续子串的长度
            // index : p[i]在字母表中的下标
            int index = p.charAt(i) - 'a';
            dp[index] = Math.max(dp[index], cur);
        }
        for (int len : dp) {
            maxLength += len;
        }
        return maxLength;
    }
}
