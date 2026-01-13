package greedy;

/**
 * LeetCode 55. 跳跃游戏
 * 
 * 题目描述：
 * 给定一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * 
 * 解题思路：
 * 使用贪心算法，维护当前能够到达的最远位置。
 * 遍历数组时，如果当前位置超过了最远可达位置，则无法到达最后一个位置。
 * 否则，更新最远可达位置，并检查是否已经能够到达最后一个位置。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_55_JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
