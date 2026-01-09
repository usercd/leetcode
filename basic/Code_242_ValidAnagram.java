package basic;

/**
 * 题目大意：
 * 给定两个字符串 s 和 t，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 解题思路：
 * 使用一个长度为 26 的数组来统计每个字母的出现次数。
 * 遍历字符串 s 时，对应字母的计数加一；遍历字符串 t 时，对应字母的计数减一。
 * 最后检查数组中是否所有计数都为零，如果是，则 t 是 s 的字母异位词。
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是字符串的长度。需要遍历两个字符串。
 * 空间复杂度：O(1)，使用固定大小的计数数组。
 */


public class Code_242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}
