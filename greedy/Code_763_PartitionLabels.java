package greedy;

/**
 * LeetCode 763. 划分字母区间
 * 
 * 题目描述：
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 使得每个字母最多出现在一个片段中，返回一个表示每个字符串片段的长度的列表。
 * 
 * 解题思路：
 * 首先记录每个字符最后出现的位置，然后遍历字符串，动态更新当前片段的结束位置。
 * 当遍历到当前片段的结束位置时，划分出一个片段，并开始新的片段。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

import java.util.List;
import java.util.ArrayList;

public class Code_763_PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // Record the last occurrence of each character
        int[] lastOccurrence = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            // Update the end of the current partition
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            if (i == end) {
                partitions.add(end - start + 1);
                start = i + 1;
            }
        }
        return partitions;
    }
}
