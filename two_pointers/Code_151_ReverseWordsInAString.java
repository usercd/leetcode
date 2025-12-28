package two_pointers;

/**
 * 151. Reverse Words in a String
 * 题目大意：
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s 中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 
 * 解题思路：
 * 方法一：使用内置函数，先去除多余空格，然后使用 split 分割字符串，最后反转单词顺序并拼接成结果字符串。
 * 方法二：使用双指针法，从字符串末尾开始查找单词，找到单词后添加到结果字符串中，同时跳过多余空格。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。需要遍历字符串一次。
 * 空间复杂度：O(n)，用于存储结果字符串。
 */
public class Code_151_ReverseWordsInAString {

    public String reverseWords(String s) {
        // 去除多余空格
        s = s.trim();
        String[] words = s.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    public String reverseWords1(String s) {
        String[] strs = s.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) continue;
            
            res.append(strs[i] + " ");             
        }
        return res.toString().trim();    
    }

    // 双指针法
    public String reverseWords2(String s) {
        StringBuilder res = new StringBuilder();
        int right = s.length() - 1;
        int left = right;
        // s = "  hello world  "
        while (left >= 0) {
            // 找到单词的开头
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            // 添加单词
            res.append(s.substring(left + 1, right + 1) + " ");
            // 跳过空格
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            right = left;
        }
        return res.toString().trim();
    }
}