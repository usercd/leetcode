package handwriting;

import java.util.Random;

public class QuickSort {
    // 快速排序
    private static final Random random = new Random();

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        // 随机选择基准，防止最坏情况
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);  // 将基准放到最后

        int pivot = arr[right];
        int i = left;   // 小于等于区域的右边界

        // Lomuto分区方案（或称单指针分区）
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {      // < pivot 的放左边
                swap(arr, i, j);
                i++;
            }
        }
        // 把基准放到正确位置
        swap(arr, i, right);

        // 递归左右两部分
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
