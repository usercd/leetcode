package greedy;

/**
 * LeetCode 45. 跳跃游戏 II
 * 
 * 题目描述：
 * 给定一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 
 * 解题思路：
 * 使用贪心算法，维护当前跳跃的边界和能够到达的最远位置。
 * 每当遍历到当前跳跃的边界时，增加跳跃次数，并更新边界为能到达的最远位置。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_45_JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // If we have reached the end of the current jump
            if (i == currentEnd) {
                jumps++;
                // Update the end for the next jump
                currentEnd = farthest;

                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}
