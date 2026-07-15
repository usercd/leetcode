package heap;

/**
 * LeetCode 215. 数组中的第K个最大元素
 * <p>
 * 题目描述：
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 解题思路：
 * 使用最小堆（Min-Heap）来维护数组中最大的 k 个元素。
 * 遍历数组，将每个元素加入最小堆中；
 * 当堆的大小超过 k 时，移除堆顶元素（即当前最小的元素）。
 * 最终，堆顶元素即为第 k 个最大的元素。
 * <p>
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(k)
 */

public class Code_215_KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        // 建立大小为k的小根堆
        for (int i = k / 2 - 1; i >= 0; i--) {
            siftDown(nums, i, k);
        }

        // 维护大小为 k 的小根堆
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                nums[0] = nums[i];
                siftDown(nums, 0, k);
            }
        }

        return nums[0];
    }

    private void siftDown(int[] heap, int i, int size) {
        while (true) {
            int left = i * 2 + 1;
            int right = left + 1;
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            int t = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = t;
            i = smallest;
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
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];

        int lt = left;     // < pivot 的右边界
        int i = left;      // 当前扫描位置
        int gt = right;    // > pivot 的左边界

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt, i);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        // k 落在 = pivot 区域
        if (k >= lt && k <= gt) {
            return nums[k];
        }

        // 去左边找
        if (k < lt) {
            return quickSelect(nums, left, lt - 1, k);
        }

        // 去右边找
        return quickSelect(nums, gt + 1, right, k);
    }
}