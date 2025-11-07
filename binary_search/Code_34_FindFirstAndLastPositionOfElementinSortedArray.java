package binary_search;

public class Code_34_FindFirstAndLastPositionOfElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 查找左边界
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return result;
        }
        result[0] = left;

        // 查找右边界
        right = nums.length - 1; // 重置右指针
        while (left < right) {
            int mid = left + (right - left + 1) / 2; // 注意这里的取中位数方式
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        result[1] = right;

        return result;
    }

    public static void main(String[] args) {
        Code_34_FindFirstAndLastPositionOfElementinSortedArray solver = new Code_34_FindFirstAndLastPositionOfElementinSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 6;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 5;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");

        target = 10;
        range = solver.searchRange(nums, target);
        System.out.println("First and Last Position of " + target + ": [" + range[0] + ", " + range[1] + "]");
    }
}
