package basic;

/**
 * 题目大意：
 * 给定一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 
 * 解题思路：
 * 使用两个遍历来计算每个位置左侧和右侧的乘积。
 * 第一次遍历计算每个位置左侧的乘积并存储在结果数组中。
 * 第二次遍历从右向左计算每个位置右侧的乘积，并将其与结果数组中的左侧乘积相乘。
 * 这样可以在不使用除法的情况下得到最终结果。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，需要两次遍历数组。
 * 空间复杂度：O(1)，不使用额外的数组存储，结果数组不计入空间复杂度。
 */

public class Code_238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Calculate left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Calculate right products and combine with left products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }
}
