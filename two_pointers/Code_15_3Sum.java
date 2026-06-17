package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15. 三数之和
 * 
 * 题目描述：
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c 使得 a + b + c = 0 ?
 * 找到所有满足条件且不重复的三元组。
 * 
 * 解题思路：排序固定一个然后双指针，去重永远是跟"已经处理过的"比较，而不是跟"还没处理的"比较。
 * 
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */

public class Code_15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicate values for the first number
            }
            if (nums[i] > 0) {
                break; // Since the array is sorted, no need to continue if the first number is greater than 0
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values for the second number
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicate values for the third number
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
