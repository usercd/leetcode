package binary_search;

public class Code_4_MedianOfTwoSortedArrays {

    // 双指针 时间O(m+n) 空间O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int total = m + n;

        int i = 0;
        int j = 0;

        int pre = 0;
        int cur = 0;

        // 只遍历到中位数位置
        for (int k = 0; k <= total / 2; k++) {
            pre = cur;

            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                cur = nums1[i++];
            } else {
                cur = nums2[j++];
            }
        }

        if ((total & 1) == 1) {
            return cur;
        }

        return (pre + cur) / 2.0;
    }

    // 时间复杂度：O(log(min(m, n))) 空间复杂度：O(1)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 保证 nums1 是较短数组
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0, right = m;

        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = (m + n + 1) / 2 - i;

            int Aleft = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int Bleft = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if (((m + n) & 1) == 1) {
                    return Math.max(Aleft, Bleft);
                } else {
                    return (Math.max(Aleft, Bleft)
                            + Math.min(Aright, Bright)) / 2.0;
                }
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        return 0.0;
    }
}
