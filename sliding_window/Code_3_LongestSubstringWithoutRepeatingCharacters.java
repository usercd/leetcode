package sliding_window;

/**
 * LeetCode 3. 无重复字符的最长子串
 * 
 * 题目描述：
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 解题思路：
 * 使用滑动窗口技术求解
 * 1. 定义两个指针 left 和 right，表示当前窗口的左右边界
 * 2. 使用一个布尔数组 charSet 来记录当前窗口中字符的出现情况
 * 3. 移动右指针扩展窗口，遇到重复字符时，移动左指针缩小窗口直到不重复
 * 4. 在每次扩展窗口后，更新最大长度 maxLength
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1) 使用固定大小的布尔数组
 */

public class Code_3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, right = 0;
        int maxLength = 0;
        boolean[] charSet = new boolean[128]; // ASCII 字符集大小

        while (right < n) {
            char currentChar = s.charAt(right);
            // 如果当前字符已经在窗口中，移动左指针直到不重复
            while (charSet[currentChar]) {
                charSet[s.charAt(left)] = false;
                left++;
            }
            // 将当前字符加入窗口
            charSet[currentChar] = true;
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
