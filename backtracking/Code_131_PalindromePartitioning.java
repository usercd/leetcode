package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131. 分割回文串
 * 题目描述：
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回所有可能的分割方案。
 * 解题思路：
 * 使用回溯法生成所有可能的分割
 * 1. 选择列表：当前可选的切割位置
 * 2. 路径：当前分割的子串
 * 3. 结束条件：遍历完所有字符
 * 4. 回溯过程：对于每个切割位置，选择切割或不切割，递归处理剩余字符
 * 时间复杂度：O(N * 2^N)，N 是字符串长度
 * 空间复杂度：O(N)，用于存储当前分割方案
 */

class Code_131_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (isPalindrome(substring)) {
                current.add(substring);
                backtrack(s, end, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Code_131_PalindromePartitioning solver = new Code_131_PalindromePartitioning();
        String s = "aab";
        List<List<String>> partitions = solver.partition(s);
        System.out.println(partitions); // Output: [["a","a","b"],["aa","b"]]
    }
}