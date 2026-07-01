package handwriting;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) return;
        int mid = left + ((right - left) >> 1);
        sort(arr, left, mid, temp);
        sort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    public static void mergeSortIter(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];

        for (int size = 1; size < n; size *= 2) {
            // left需要小于n-size，因为size是子数组的大小，如果left超过n-size，就没有足够的元素组成一个完整的子数组了
            for (int left = 0; left < n - size; left += 2 * size) {
                // left 移动是2倍size，因为每次合并两个size大小的子数组
                // mid是左子数组的最后一个元素，右子数组的第一个元素是mid + 1
                int mid = left + size - 1;
                // right是右子数组的最后一个元素，可能会越界，所以需要取min
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, left, mid, right, temp);
            }
        }
    }

    private static void merge(int[] arr, int left, int mid,
                              int right, int[] temp) {

        int i = left;      // 左数组起点
        int j = mid + 1;   // 右数组起点
        int t = 0;

        // 比较两个有序数组
        while (i <= mid && j <= right) {
            temp[t++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        // 左边剩余元素
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        // 右边剩余元素
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 拷贝回原数组
        t = 0;
        int start = left;

        while (start <= right) {
            arr[start++] = temp[t++];
        }
    }
}
