package two_pointers;

/**
 * LeetCode 11. 盛最多水的容器
 * 
 * 题目描述：
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 
 * 解题思路：
 * 使用双指针法
 * 1. 定义两个指针：left 指向数组开头，right 指向数组结尾
 * 2. 计算当前容器的面积，并更新最大面积
 * 3. 移动较短的指针，尝试找到更高的边界以增加面积
 * 4. 重复步骤 2 和 3，直到两个指针相遇
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
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
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
