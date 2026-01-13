package two_pointers;

/**
 * LeetCode 27. 移除元素
 * 
 * 题目描述：
 * 给你一个数组 nums 和一个值 val，
 * 你需要原地移除所有数值等于 val 的元素，
 * 并返回移除后数组的新长度。
 * 不要使用额外的数组空间，
 * 你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变，
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 解题思路：
 * 使用双指针法，一个指针遍历数组，另一个指针记录不等于 val 的元素位置。
 * 当遍历到不等于 val 的元素时，将其赋值到记录位置，并移动记录指针。
 * 最终记录指针的位置即为新数组的长度。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_27_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
}
