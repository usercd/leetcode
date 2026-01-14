package sliding_window;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目大意：
 * 给你一个正整数数组 nums ，请你找出并返回能获得的 最大分数 。
 * 数组的 分数 是指：选出一个 子数组 ，使得该子数组中的 元素互不相同 ，且该子数组的和最大。
 * 请注意，子数组 是数组中的一个 连续 部分。
 * 
 * 解题思路：
 * 使用滑动窗口技巧维护一个当前子数组，使用一个布尔数组或哈希集合来记录当前子数组中是否包含某个元素。当遇到重复元素时，移动左指针直到子数组中不再包含该重复元素。不断更新最大和。
 * 
 * 复杂度分析：
 * 时间复杂度：O(N)，其中 N 是数组 nums 的长度。每个元素最多被访问两次（一次由右指针，一次由左指针）。
 * 空间复杂度：O(M)，其中 M 是 nums 中不同元素的数量。用于存储布尔数组或哈希集合。
 */

public class Code_1695_MaximumErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int currentSum = 0;
        int maxSum = 0;
        boolean[] seen = new boolean[10001]; // Given constraint: 1 <= nums[i] <= 10^4

        while (right < n) {
            if (!seen[nums[right]]) {
                seen[nums[right]] = true;
                currentSum += nums[right];
                maxSum = Math.max(maxSum, currentSum);
                right++;
            } else {
                while (nums[left] != nums[right]) {
                    seen[nums[left]] = false;
                    currentSum -= nums[left];
                    left++;
                }
                // Move left pointer one step further to remove the duplicate
                seen[nums[left]] = false;
                currentSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }

    // set
    public int maximumUniqueSubarray1(int[] nums) {
        int result = 0;
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();
        int windowsSum = 0;
        while (right < nums.length) {
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                windowsSum -= nums[left];
                left++;
            }
            set.add(nums[right]);
            windowsSum += nums[right];
            right++;
            result = Math.max(result, windowsSum);
        }

        return result;
    }
}
