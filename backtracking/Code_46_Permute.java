package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46. 全排列
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 解题思路：
 * 使用回溯法生成全排列
 * 1. 选择列表：输入数组 nums 中的所有数字
 * 2. 路径：当前排列中的数字列表
 * 3. 结束条件：当路径长度等于 nums 长度时，添加当前排列到结果中
 * 4. 回溯过程：遍历选择列表，选择一个数字加入路径，递归生成剩余数字的排列，完成后移除该数字（回溯）
 * 时间复杂度：O(n * n!)，其中 n 是数组长度，全排列的数量为 n!，每个排列的生成需要 O(n) 时间
 * 空间复杂度：O(n), 栈的最大深度为 n
 */

public class Code_46_Permute {

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(result, tempList, nums);
            tempList.remove(tempList.size() - 1);
        }
    }
}