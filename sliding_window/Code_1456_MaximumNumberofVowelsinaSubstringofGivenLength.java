package sliding_window;

/**
 * LeetCode 1456. 定长子串中元音的最大数目
 * 
 * 题目描述：
 * 给你一个字符串 s 和一个整数 k，请你找出 s 中长度为 k 的单个子字符串中包含的最大元音字母数。
 * 元音字母包括 'a', 'e', 'i', 'o', 'u'。
 * 
 * 解题思路：
 * 使用滑动窗口技术来解决这个问题。维护一个长度为 k 的窗口，计算窗口内的元音字母数量，并在每次移动窗口时更新最大值。
 * 1. 初始化窗口和计数器。
 * 2. 遍历字符串，扩展窗口并更新元音字母计数。
 * 3. 当窗口大小超过 k 时，收缩窗口并更新计数器。
 * 4. 在每次调整窗口时，更新最大元音字母数量。
 * 
 * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
 * 空间复杂度：O(1)，只使用了常数空间来存储计数器和最大值。
 */

public class Code_1456_MaximumNumberofVowelsinaSubstringofGivenLength {
    public int maxVowels(String s, int k) {
        int maxCount = 0;
        int currentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                currentCount++;
            }
            if (i >= k) {
                if (isVowel(s.charAt(i - k))) {
                    currentCount--;
                }
            }
            maxCount = Math.max(maxCount, currentCount);
            if (maxCount == k) return maxCount;
        }
        return maxCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
