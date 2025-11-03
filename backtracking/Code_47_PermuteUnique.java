package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 * 解题思路：
 * 使用回溯法生成全排列，同时跳过重复数字以避免重复排列
 * 1. 选择列表：输入数组 nums 中的所有数字
 * 2. 路径：当前排列中的数字列表
 * 3. 结束条件：当路径长度等于 nums 长度时，添加当前排列到结果中
 * 4. 回溯过程：遍历选择列表，选择一个数字加入路径，递归生成剩余数字的排列，完成后移除该数字（回溯）
 *    - 在选择数字时，跳过与前一个数字相同且前一个数字未被使用的情况，以避免重复排列
 * 时间复杂度：O(n * n!)，其中 n 是数组长度，全排列的数量为 n!，每个排列的生成需要 O(n) 时间
 * 空间复杂度：O(n), 栈的最大深度为 n
 */

public class Code_47_PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue; // skip duplicates
            used[i] = true;
            tempList.add(nums[i]);
            backtrack(res, tempList, nums, used);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }
}