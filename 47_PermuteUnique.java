public class 47_PermuteUnique {
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