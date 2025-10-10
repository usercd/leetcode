package backtracking;

import java.util.ArrayList;
import java.util.List;

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