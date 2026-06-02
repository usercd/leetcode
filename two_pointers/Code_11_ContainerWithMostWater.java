package two_pointers;

/**
 * LeetCode 11. 盛最多水的容器
 * 
 * 题目描述：
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 
 * 解题思路：每次移动短板，是因为短板的"历史使命"已经完成；保留长板，是因为它还有可能与内侧更高的柱子配对获得更大面积。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短的那一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
