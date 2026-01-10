package basic;

/**
 * 459. Repeated Substring Pattern
 * 题目大意：
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 
 * 解题思路：
 * 遍历可能的子串长度，从 1 到 s.length() / 2，检查该长度是否能整除字符串长度。
 * 如果能整除，则构造一个由该子串重复组成的新字符串，并与原字符串比较。
 * 如果相等，则返回 true；否则继续检查下一个可能的子串长度。
 * 另外，可以使用字符串匹配法，将字符串与自身连接后，去掉首尾字符，检查原字符串是否为新字符串的子串。
 * 还可以使用 KMP 算法，通过构建部分匹配表来判断字符串是否由重复子串组成。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n^2) 对于每个可能的子串长度，构造新字符串需要 O(n) 时间，最坏情况下需要检查 O(n) 个长度。
 * 空间复杂度：O(n) 用于存储新字符串或部分匹配表。
 */

public class Code_459_RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int len = 1; len <= n / 2; len++) {
            if (n % len == 0) {
                StringBuilder sb = new StringBuilder();
                int repeatCount = n / len;
                String substring = s.substring(0, len);
                for (int i = 0; i < repeatCount; i++) {
                    sb.append(substring);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 字符串匹配法
    public boolean repeatedSubstringPattern2(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
    
    // KMP算法
    public boolean repeatedSubstringPattern3(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int j = 0; // length of previous longest prefix suffix
        for (int i = 1; i < n; ) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        int len = lps[n - 1];
        return len > 0 && n % (n - len) == 0;
    }

    public boolean repeatedSubstringPattern4(String s) {
        if (s == null || s.isEmpty()) return false;
        return checkRepeatedPattern(s);
    }

    private boolean checkRepeatedPattern(String pattern) {
        int length = pattern.length();
        int[] next = buildNextArray(pattern);

        // 获取最后一个位置的next值，表示最长相同前缀后缀长度
        int lastMatchLength = next[length - 1];
        // 判断条件：
        // 1. lastMatchLength != -1：存在相同前缀后缀
        // 2. length % (length - lastMatchLength - 1) == 0：字符串长度能被子串长度整除
        return lastMatchLength != -1 && length % (length - lastMatchLength - 1) == 0;
    }

    private int[] buildNextArray(String pattern) {
        int length = pattern.length();
        int[] next = new int[length];
        next[0] = -1; // 第一个字符无前缀后缀匹配

        int prefixIndex = -1; // 前缀指针
        for (int i = 1; i < length; i++) {
            // 当字符不匹配时，回溯前缀指针
            while (prefixIndex != -1 && pattern.charAt(prefixIndex + 1) != pattern.charAt(i)) {
                prefixIndex = next[prefixIndex];
            }
            // 字符匹配，前缀指针前移
            if (pattern.charAt(prefixIndex + 1) == pattern.charAt(i)) {
                prefixIndex++;
            }
            next[i] = prefixIndex;
        }
        return next;
    }
}