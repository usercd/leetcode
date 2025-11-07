package binary_search;

public class Code_35_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Code_35_SearchInsertPosition solver = new Code_35_SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(solver.searchInsert(nums, target)); // Output: 2

        target = 2;
        System.out.println(solver.searchInsert(nums, target)); // Output: 1

        target = 7;
        System.out.println(solver.searchInsert(nums, target)); // Output: 4

        target = 0;
        System.out.println(solver.searchInsert(nums, target)); // Output: 0
    }
}
