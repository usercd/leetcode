package stack;

import java.util.Stack;

/**
 * LeetCode 84. 柱状图中最大的矩形
 * 
 * 题目描述：
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 解题思路：
 * 使用单调栈来计算每个柱子向左和向右扩展的边界，从而计算以每个柱子为高度的最大矩形面积。
 * 具体步骤如下：
 * 1. 遍历每个柱子，使用栈记录柱子的索引，确保栈内柱子的高度单调递增。
 * 2. 当遇到一个比栈顶柱子高度小的柱子时，弹出栈顶柱子，计算以该柱子为高度的最大矩形面积。
 * 3. 重复上述过程直到遍历完所有柱子，并处理栈中剩余的柱子。
 * 
 * 时间复杂度：O(n) 遍历柱子两次
 * 空间复杂度：O(n) 栈空间
 */

public class Code_84_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate left limits
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Calculate right limits
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Calculate the maximum area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
