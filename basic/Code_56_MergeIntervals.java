package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56. 合并区间
 * 
 * 题目描述：
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 
 * 解题思路：
 * 1. 先对区间按起始位置排序
 * 2. 遍历排序后的区间列表，使用一个变量 currentInterval 来跟踪当前合并的区间
 * 3. 如果当前区间与下一个区间有重叠，合并它们
 * 4. 否则，将当前区间加入结果列表，并更新当前区间为下一个区间
 * 5. 最后添加最后一个区间到结果列表
 * 
 * 时间复杂度：O(n log n) 主要是排序的时间复杂度
 * 空间复杂度：O(n) 用于存储合并后的区间
 */

public class Code_56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // 1. 先对区间按起始位置排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            // 2. 如果当前区间与下一个区间有重叠，合并它们
            if (currentInterval[1] >= intervals[i][0]) {
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            } else {
                // 3. 否则，将当前区间加入结果列表，并更新当前区间
                merged.add(currentInterval);
                currentInterval = intervals[i];
            }
        }
        // 添加最后一个区间
        merged.add(currentInterval);

        return merged.toArray(new int[merged.size()][]);
    }
}
