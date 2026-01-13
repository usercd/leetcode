package stack;

import java.util.Stack;

/**
 * LeetCode 739. 每日温度
 * 
 * 题目描述：
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果之后没有更高的温度，答案为 0 。
 * 
 * 解题思路：
 * 使用单调栈来存储温度的索引，从后向前遍历温度数组。
 * 对于每个温度，弹出栈中所有小于或等于当前温度的索引，
 * 然后计算当前温度到下一个更高温度的天数，并将当前索引压入栈中。
 * 
 * 时间复杂度：O(n) 遍历温度数组一次
 * 空间复杂度：O(n) 使用栈存储索引
 */

public class Code_739_DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return result;
    }
}
