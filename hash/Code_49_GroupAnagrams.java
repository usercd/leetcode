package hash;

import java.util.*;

/**
 * LeetCode 49. 字母异位词分组
 * 
 * 题目描述：
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 解题思路：
 * 使用哈希表将字母异位词分组
 * 1. 遍历每个字符串，将其转换为字符数组并排序，得到一个标准形式的键
 * 2. 使用排序后的字符串作为键，将原字符串添加到对应的列表中
 * 3. 最后返回哈希表中所有值的集合
 * 时间复杂度：O(n * k log k)，其中 n 是字符串数组的长度，k 是字符串的最大长度（排序的时间复杂度）
 * 空间复杂度：O(n * k)，用于存储哈希表
 */

public class Code_49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
}
