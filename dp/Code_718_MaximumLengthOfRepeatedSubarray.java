package dp;

/**
 * @author CD
 * @date 7/8/2026
 */
public class Code_718_MaximumLengthOfRepeatedSubarray {
    // 时间O(mn) 空间O(mn)
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // dp[i][j] 代表nums1 以 i-1 结尾、nums2 以 j-1 结尾的最长公共连续子数组长度。
        int[][] dp = new int[m+1][n+1];
        int result = 0;
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    // 空间优化 空间O(n)
    // 由于状态只依赖左上角 dp[i-1][j-1]，可以将二维 DP 优化为一维 DP
    // 状态定义，令dp[j]表示当前处理到 nums1[i-1] 时，以 nums1[i-1] 和 nums2[j-1] 结尾的最长公共连续子数组长度。
    // dp[j-1] 必须是上一行（即 i-1 时）的值，而不是当前行已经更新后的值。因此，内层循环必须从右向左遍历，避免覆盖上一行的数据。
    public int findLength1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] dp = new int[n + 1];
        int ans = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    ans = Math.max(ans, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }

        return ans;
    }
}
