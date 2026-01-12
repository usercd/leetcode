package two_pointers;

/**
 * LeetCode 541. 反转字符串 II
 * 
 * 题目描述：
 * 给定一个字符串 s 和一个整数 k，从字符串的开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符大于等于 k 个但少于 2k 个，则反转前 k 个字符，其余字符保持原样。
 * 
 * 解题思路：
 * 使用双指针法
 * 1. 将字符串转换为字符数组以便修改
 * 2. 遍历字符数组，每次跳过 2k 个字符
 * 3. 对每个区间的前 k 个字符使用双指针进行反转
 * 4. 最后将修改后的字符数组转换回字符串并返回
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)（用于存储字符数组）
 */

public class Code_541_ReverseStringII {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        for (int i = 0; i < n; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);

            while (left < right) {
                char temp = arr[left];
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }

        return new String(arr);
    }
}
