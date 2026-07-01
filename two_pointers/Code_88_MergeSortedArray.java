package two_pointers;

/**
 * @author CD
 * @date 7/1/2026
 */
public class Code_88_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = nums1.length - 1;
        m--;
        n--;
        while (n >= 0) {
            while (m >= 0 && nums1[m] >= nums2[n]) {
                nums1[i--] = nums1[m--];
            }
            nums1[i--] = nums2[n--];
        }
    }
}
