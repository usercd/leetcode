package two_pointers;

import java.util.Stack;

/**
 * LeetCode 42. 接雨水
 * 
 * 题目描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 解题思路：
 * 使用双指针法
 * 1. 定义两个指针：left 指向数组开头，right 指向数组结尾
 * 2. 使用 leftMax 和 rightMax 分别记录左右两侧的最高柱子
 * 3. 移动较低的一侧指针，并计算该位置能接的雨水量
 * 4. 重复步骤 2 和 3，直到两个指针相遇
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * 
 * 另外还提供了动态规划和单调栈的解法
 * 动态规划：
 * 1. 预处理数组，计算每个位置左侧和右侧的最高柱子高度
 * 2. 遍历数组，计算每个位置能接的雨水量
 * 3. 累加总的雨水量
 * 单调栈：
 * 1. 使用栈存储柱子的索引
 * 2. 遍历数组，当遇到比栈顶元素高的柱子时，计算能接的雨水量
 * 3. 将当前柱子索引入栈
 */

public class Code_42_TrappingRainWater {

    // 双指针法计算接雨水量
    public int trap1(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int waterTrapped = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    waterTrapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }

    // DP
    public int trap2(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int waterTrapped = 0;

        // 计算每个位置左侧的最高柱子
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 计算每个位置右侧的最高柱子
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算总的接雨水量
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return waterTrapped;
    }

    // 单调栈
    public int trap3(int[] height) {
        int n = height.length;
        int waterTrapped = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                waterTrapped += distance * boundedHeight;
            }
            stack.push(i);
        }

        return waterTrapped;
    }
}
