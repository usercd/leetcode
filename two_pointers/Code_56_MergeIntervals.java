package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code_56_MergeIntervals {

    // 时间复杂度：O(n log n) 空间复杂度：O(n)
    public int[][] merge1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
