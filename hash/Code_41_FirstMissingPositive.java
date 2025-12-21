package hash;

/**
 * 缺失的第一个正数
 * 题目描述：
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。要求算法的时间复杂度为 O(n)，并且只能使用常数级别的额外空间。
 * 解题思路：
 * 1. 使用原地哈希的思想，将每个正整数放到其正确的位置上，即数字 n 应该放在下标为 n-1 的位置上。
 * 2. 遍历数组，对于每个元素 nums[i]，如果它在范围内（1 到 n），且不在正确的位置上，就将其与正确位置上的元素交换。
 * 3. 交换后继续检查新的 nums[i]，直到该位置上的元素不满足交换条件。
 * 4. 最后再遍历一遍数组，找到第一个位置 i 上的元素不等于 i+1 的位置，i+1 即为缺失的最小正整数。
 * 5. 如果所有位置都正确，则缺失的最小正整数为 n+1。
 * 复杂度分析：
 * 时间复杂度：O(n)，每个元素最多交换一次。
 * 空间复杂度：O(1)，使用了常数级别的额外空间。
 */

public class Code_41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 满足范围且没有在正确位置，进行交换
            while (nums[i] > 0 && nums[i] < len + 1 && nums[i] != nums[nums[i] - 1]) {
                // 把nums[i]交换到合适位置，即下标为nums[i] - 1的位置
                swap(nums, nums[i] - 1, i);
            }
        }

        // 进行一次遍历，找到满足题意的答案
        for (int i = 0; i < len; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return len + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
