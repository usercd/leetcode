package sliding_window;

/**
 * LeetCode 76. 最小覆盖子串
 * 题目描述：
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 解题思路：
 * 使用滑动窗口算法求解
 * 1. 使用两个指针 left 和 right 定义一个窗口，初始时都指向字符串 S 的开头
 * 2. 使用一个数组 need 记录字符串 T 中每个字符需要的数量
 * 3. 扩展右指针 right，直到窗口包含了 T 中的所有字符
 * 4. 收缩左指针 left，尝试找到更小的满足条件的窗口
 * 5. 重复步骤 3 和 4，直到右指针到达字符串 S 的末尾
 *
 *
 */

public class Code_76_MinimumWindowSubstring {

    // 时间复杂度：O(m+n) m是字符串s的长度，n是字符串n的长度
    // 空间复杂度：O(1)
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int left = 0, right = 0, count = t.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            char rChar = s.charAt(right);
            if (need[rChar] > 0) {
                count--;
            }
            need[rChar]--;
            right++;

            while (count == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }
                char lChar = s.charAt(left);
                need[lChar]++;
                if (need[lChar] > 0) {
                    count++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
