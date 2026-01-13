package two_pointers;

/**
 * LeetCode 977. 有序数组的平方
 * 
 * 题目描述：
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 
 * 解题思路：
 * 使用双指针法
 * 1. 初始化两个指针，left 指向数组开头，right 指向数组结尾
 * 2. 创建一个结果数组，从后向前填充
 * 3. 比较 left 和 right 指针所指元素的平方值，将较大的平方值放入结果数组的当前索引位置
 * 4. 移动相应的指针，并将结果数组的索引向前移动
 * 5. 重复上述过程直到 left 超过 right
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)（用于存储结果数组）
 */

public class Code_977_SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = n - 1;

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[index--] = leftSquare;
                left++;
            } else {
                result[index--] = rightSquare;
                right--;
            }
        }

        return result;            
    }
}
