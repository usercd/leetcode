package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139. 单词拆分
 * 题目描述：
 * 给定一个字符串 s 和一个字符串字典 wordDict，
 * 判断 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 解题思路：
 * 使用动态规划求解
 * 1. 状态定义：dp[i] 表示字符串 s 的前 i 个字符能否被拆分成字典中的单词
 * 2. 状态转移：对于每个位置 i，检查所有可能的拆分点 j (0 <= j < i)
 *    如果 dp[j] 为 true 且 s[j:i] 在字典中，则 dp[i] 为 true
 *    即：dp[i] = dp[j] && wordSet.contains(s.substring(j, i))
 * 3. 初始状态：dp[0] = true (空字符串可以被拆分)
 * 4. 返回结果：dp[n]，其中 n 是字符串 s 的长度
 * 时间复杂度：O(n^2)/O(ml + nl^2) ml是创建
 * 空间复杂度：O(ml + n) m 是wordDict的长度，n是字符串s的长度，l是wordDict中元素最长长度
 */

public class Code_139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空字符串可以被分割

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    // 时间复杂度优化
    public boolean wordBreak1(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        Set<String> words = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
