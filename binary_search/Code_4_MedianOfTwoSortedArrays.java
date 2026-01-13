package binary_search;

/**
 * LeetCode 4. 寻找两个正序数组的中位数
 * 
 * 题目描述：
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 
 * 解题思路：
 * 使用二分查找的方法，将寻找中位数的问题转化为寻找第 k 小的数的问题。
 * 每次比较两个数组的第 k/2 个元素，排除掉较小元素所在数组的前 k/2 个元素，缩小搜索范围。
 * 
 * 时间复杂度：O(log(min(m, n)))
 * 空间复杂度：O(1)
 */

public class Code_4_MedianOfTwoSortedArrays {
    /**
     * 寻找两个正序数组的中位数
     * 时间复杂度要求：O(log(m+n))
     * 核心思路：将问题转化为寻找两个数组中的第k小的数
     * 
     * @param nums1 第一个正序数组
     * @param nums2 第二个正序数组
     * @return 返回两个数组合并后的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        
        // 如果总长度是奇数，直接返回第(totalLength/2 + 1)小的数
        if (totalLength % 2 == 1) {
            return getKthElement(nums1, nums2, (totalLength / 2) + 1);
        } 
        // 如果总长度是偶数，返回第(totalLength/2)小和第(totalLength/2 + 1)小的数的平均值
        else {
            double num1 = getKthElement(nums1, nums2, totalLength / 2);
            double num2 = getKthElement(nums1, nums2, totalLength / 2 + 1);
            return (num1 + num2) / 2.0;
        }
    }
    
    /**
     * 寻找两个有序数组中的第k小的元素
     * 使用二分查找的思想，每次排除k/2个元素
     */
    private double getKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0;    // nums1数组的起始位置
        int index2 = 0;    // nums2数组的起始位置
        
        while (true) {
            // 如果数组1已经遍历完，直接返回数组2中的第k小元素
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            // 如果数组2已经遍历完，直接返回数组1中的第k小元素
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            // 如果k=1，返回两个数组当前位置较小的那个数
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况，取k/2进行比较
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            
            // 比较两个数组的第k/2个元素，较小的那个数组的前k/2个元素可以排除
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        Code_4_MedianOfTwoSortedArrays solver = new Code_4_MedianOfTwoSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solver.findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(solver.findMedianSortedArrays(nums1, nums2)); // Output: 2.5
    }
}
