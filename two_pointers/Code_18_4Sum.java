package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 18. 4Sum
 * 题目描述：
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。 请你找出并返回满足下述全部条件且不重复的四元组 [numsa, numsb, numsc, numsd] ：  
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * numsa + numsb + numsc + numsd == target
 * 你可以按 任意顺序 返回答案 。
 * 
 * 解题思路：
 * 使用排序和双指针法来解决四数之和问题。首先对数组进行排序，然后使用两层循环固定前两个数，接着使用双指针在剩余部分寻找另外两个数，使得四个数的和等于目标值。为了避免重复结果，在每一步选择数字时都要跳过相同的数字。
 * 时间复杂度：O(n^3)，其中 n 是数组的长度。排序需要 O(n log n)，双指针部分在最坏情况下需要 O(n^2)。
 * 空间复杂度：O(1)，不考虑返回结果所需的空间。
 */

public class Code_18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        // 外层循环，固定第一个数字
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            // 如果当前最小和大于目标值，直接退出
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;

            // 如果当前最大和小于目标值，跳过当前循环
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            // 内层循环，固定第二个数字
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重
                // 如果当前最小和大于目标值，直接退出
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                // 如果当前最大和小于目标值，跳过当前循环
                if ((long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;
                
                // 双指针寻找剩余的两个数字
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++; // 去重
                        while (left < right && nums[right] == nums[right - 1]) right--; // 去重
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}