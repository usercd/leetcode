package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 组合总和 
 * 题目描述：
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 
 * 解题思路：
 * 1. 使用回溯算法进行深度优先搜索（DFS）。
 * 2. 对候选数组进行排序，以便在搜索过程中进行剪枝。
 * 3. 在递归过程中，维护当前路径和剩余目标值。
 * 4. 当剩余目标值为0时，说明找到一个有效组合，将当前路径添加到结果集中。
 * 5. 如果当前候选值大于剩余目标值，则跳过该候选值（剪枝）。
 * 6. 允许重复选择当前候选值，因此递归时仍从当前索引开始。
 * 7. 使用回溯撤销选择，移除路径中的最后一个元素。
 * 复杂度分析：
 * 时间复杂度：O(N^(T/M+1))，其中 N 是候选数组的长度，T 是目标值，M 是候选数组中的最小值。
 * 空间复杂度：O(T/M)，递归栈的最大深度。
 */

public class Code_39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }
    
    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> results) {
        // 如果目标值减到 0，说明当前路径是一个有效解
        if (target == 0) {
		    // results.add(path); // 添加的是一个引用，后续add,remove操作会改变此引用
            results.add(new ArrayList<>(path)); // 将当前路径添加到结果中
            return; // 结束当前递归分支
        }
        
        for (int i = start; i < candidates.length; i++) {
            // 如果当前候选值大于剩余目标值，则直接跳过（因为数组已排序）剪枝
            if (candidates[i] > target) break;
            path.add(candidates[i]);
            // 递归调用，继续寻找下一个候选值，允许重复选择当前候选值，因此递归时仍从 i 开始
            backtrack(candidates, target - candidates[i], i, path, results);        
            // 回溯：撤销当前选择，移除路径中的最后一个元素
            path.remove(path.size() - 1);
        }
    }
}
