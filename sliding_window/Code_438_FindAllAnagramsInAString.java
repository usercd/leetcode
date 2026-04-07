package sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 438. 找到字符串中所有字母异位词
 * 
 * 题目描述：
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的字母异位词的起始索引。
 * 字母异位词指字母相同，但排列不同的字符串。
 * 
 * 解题思路：
 * 使用滑动窗口技术求解
 * 1. 使用两个频率数组分别记录模式串 p 和当前窗口内的字符频率
 * 2. 移动右指针扩展窗口，同时更新窗口内字符频率
 * 3. 当窗口大小超过 p 的长度时，移动左指针缩小窗口，并更新频率数组
 * 4. 每次调整窗口后，比较两个频率数组是否相等，相等则记录起始索引
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1) 使用固定大小的频率数组
 */

public class Code_438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        int pLength = p.length();

        // 统计模式串 p 的字符频率
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // 使用滑动窗口遍历字符串 s
        for (int i = 0; i < s.length(); i++) {
            // 添加当前字符到窗口
            sCount[s.charAt(i) - 'a']++;

            // 移除窗口外的字符
            if (i >= pLength) {
                sCount[s.charAt(i - pLength) - 'a']--;
            }

            // 比较窗口内的字符频率与模式串的频率
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - pLength + 1);
            }
        }

        return result;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() < p.length()) return result;

        int[] freq = new int[256];
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            int indexOfRight = s.charAt(right) - 'a';
            // 当前字母是P中的字母
            if (freq[indexOfRight] > 0) {
                count--;
            }
            freq[indexOfRight]--;
            right++;

            if (count == 0) {
                result.add(left);
            }

            if (right - left == p.length()) {
                int indexOfLeft = s.charAt(left) - 'a';
                if (freq[indexOfLeft] >= 0) {
                    count++;
                }
                freq[indexOfLeft]++;
                left++;
            }
        }

        return result;
    }
}
