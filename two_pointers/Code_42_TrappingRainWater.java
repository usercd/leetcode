package two_pointers;

import java.util.Stack;

/**
 * LeetCode 42. 接雨水
 * 
 * 题目描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 解题思路：每个位置能接收的雨水量取决于该位置左侧最高柱子和右侧最高柱子中的较小值与该位置柱子高度的差值。
 * 1. DP：预先计算每个位置左侧和右侧的最高柱子高度，然后遍历计算总的雨水量。
 * 2. 双指针：使用两个指针分别从数组两端向中间移动，更新左右最高柱子高度并计算雨水量。
 * 3. 单调栈：使用栈存储柱子的索引，当遇到比栈顶元素高的柱子时，计算能接的雨水量并更新结果。
 */

public class Code_42_TrappingRainWater {


    // DP 纵向逐列位置计算接雨水量
    public int trap1(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int waterTrapped = 0;

        // 计算每个位置左侧的最高柱子，包括当前位置
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 计算每个位置右侧的最高柱子，包括当前位置
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

    // 双指针法计算接雨水量
    public int trap2(int[] height) {
        int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, waterTrapped = 0;
        
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                waterTrapped += leftMax - height[left];
                left++;
            } else {
                waterTrapped += rightMax - height[right];
                right--;
            }
        }

        return waterTrapped;
    }

    

    // 单调栈 横向逐层计算接雨水量
    public int trap3(int[] height) {
        int n = height.length;
        int waterTrapped = 0;
        // 使用栈存储柱子的索引
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 当遇到比栈顶元素高的柱子时，计算能接的雨水量
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                // 如果栈空了，说明没有左边界了，无法形成容器
                if (stack.isEmpty()) {
                    break;
                }
                // 计算当前柱子与栈顶元素之间的距离
                int distance = i - stack.peek() - 1;
                // 计算能接的雨水量，取决于当前柱子和栈顶元素的较小值与被弹出元素的高度差
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];

                waterTrapped += distance * boundedHeight;
            }
            stack.push(i);
        }

        return waterTrapped;
    }
}
