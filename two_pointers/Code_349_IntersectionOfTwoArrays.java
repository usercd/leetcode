package two_pointers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目大意：
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 
 * 解题思路：
 * 首先对两个数组进行排序，然后使用双指针方法遍历两个数组。
 * 当两个指针指向的元素相等时，将该元素添加到结果集中，并同时移动两个指针。
 * 如果一个指针指向的元素较小，则移动该指针以寻找匹配元素。
 * 最终将结果集转换为数组返回。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n log n + m log m)，其中 n 和 m 分别是两个数组的长度，排序操作的时间复杂度为 O(n log n) 和 O(m log m)。
 * 空间复杂度：O(min(n, m))，用于存储结果集的空间，最坏情况下结果集的大小为较小数组的长度。
 */

public class Code_349_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int pointer1 = 0;
        int pointer2 = 0;
        Set<Integer> resultSet = new HashSet<>();

        while (pointer1 < nums1.length && pointer2 < nums2.length) {
            if (nums1[pointer1] < nums2[pointer2]) {
                pointer1++;
            } else if (nums1[pointer1] > nums2[pointer2]) {
                pointer2++;
            } else {
                resultSet.add(nums1[pointer1]);
                pointer1++;
                pointer2++;
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }

    // 两个Set解法
    public int[] intersectionWithSets(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }
        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}