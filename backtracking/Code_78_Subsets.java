package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78. 子集
 * 
 * 题目描述：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。你可以按任意顺序返回解集。
 * 
 * 解题思路：
 * 使用回溯法生成所有子集
 * 1. 选择列表：当前可选的元素
 * 2. 路径：当前子集
 * 3. 结束条件：遍历完所有元素
 * 4. 回溯过程：对于每个元素，选择加入当前子集或跳过，递归处理剩余元素
 * 
 * 时间复杂度：O(2^n)，每个元素有两种选择（加入或不加入）
 * 空间复杂度：O(n)，用于存储当前子集
 */

public class Code_78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(res, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}