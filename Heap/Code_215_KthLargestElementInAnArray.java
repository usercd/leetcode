package heap;

import java.util.PriorityQueue;

/**
 * LeetCode 215. 数组中的第K个最大元素
 * 
 * 题目描述：
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 
 * 解题思路：
 * 使用最小堆（Min-Heap）来维护数组中最大的 k 个元素。
 * 遍历数组，将每个元素加入最小堆中；
 * 当堆的大小超过 k 时，移除堆顶元素（即当前最小的元素）。
 * 最终，堆顶元素即为第 k 个最大的元素。
 * 
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(k)
 */

public class Code_215_KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        
        // 1. 构建最大堆：O(n)
        // 从最后一个非叶子节点开始，自底向上堆化
        buildMaxHeap(nums, heapSize);
        
        // 2. 执行 k-1 次"弹出堆顶"操作：O(k log n)
        // 每次将堆顶（最大值）与末尾元素交换，然后缩小堆范围并重新堆化
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);      // 堆顶与末尾交换
            --heapSize;            // 缩小堆的有效范围
            maxHeapify(nums, 0, heapSize);  // 重新堆化
        }
        
        // 3. 此时堆顶即为第 k 大元素
        return nums[0];
    }

    /**
     * 构建最大堆
     * 从最后一个非叶子节点 (heapSize/2 - 1) 开始，自底向上堆化
     * 时间复杂度：O(n)
     */
    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        } 
    }

    /**
     * 维护最大堆性质：父节点 >= 子节点
     * @param a 数组
     * @param i 当前节点索引
     * @param heapSize 堆的有效大小
     * 时间复杂度：O(log n)
     */
    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1;      // 左子节点
        int r = i * 2 + 2;      // 右子节点
        int largest = i;        // 假设当前节点最大
        
        // 找出当前节点与左右子节点中的最大值
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        } 
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        
        // 如果最大值不是当前节点，交换并继续向下堆化
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);  // 递归堆化
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // QuickSelect approach
    public int findKthLargest1(int[] nums, int k) {
        int n = nums.length;
        // 第 k 大 = 升序排列后索引为 n - k 的元素
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];

        // 【关键优化】随机选择基准，避免最坏情况 O(n^2)
        int randomIndex = l + (int)(Math.random() * (r - l + 1));
        swap1(nums, l, randomIndex);
        
        int x = nums[l]; // 基准值
        int i = l - 1, j = r + 1;
        
        while (i < j) {
            // 从左找第一个 >= x 的数
            do i++; while (nums[i] < x);
            // 从右找第一个 <= x 的数
            do j--; while (nums[j] > x);
            
            if (i < j) {
                swap1(nums, i, j);
            }
        }
        
        // 此时 j 是左半部分的右边界（所有 <= x 的元素都在 [l, j]）
        if (k <= j) return quickSelect(nums, l, j, k);
        else return quickSelect(nums, j + 1, r, k);
    }

    private void swap1(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}