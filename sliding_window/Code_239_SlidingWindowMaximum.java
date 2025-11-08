package sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 239. 滑动窗口最大值
 * 
 * 题目描述：
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 解题思路：
 * 使用双端队列（Deque）来维护当前窗口的最大值索引
 * 1. 使用一个双端队列存储当前窗口内可能成为最大值的元素索引
 * 2. 遍历数组，对于每个元素：
 *    - 移除队列中不在当前窗口范围内的元素索引
 *    - 移除队列中所有小于当前元素的索引，因为它们不可能成为最大值
 *    - 将当前元素索引添加到队列末尾
 *    - 当窗口形成时（i >= k - 1），将队列头部的索引对应的元素添加到结果数组中
 * 时间复杂度：O(n)
 * 空间复杂度：O(k)
 */

public class Code_239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 移除不在窗口内的元素索引
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 移除所有小于当前元素的索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // 添加当前元素索引
            deque.offerLast(i);
            // 记录窗口最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
