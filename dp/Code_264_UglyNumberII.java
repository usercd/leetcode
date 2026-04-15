package dp;

public class Code_264_UglyNumberII {
    
    public int nthUglyNumber(int n) {
        // dp[i] : 第i个丑数
        int[] dp = new int[n];
        dp[0] = 1;
        // i2 i3 i5 : 乘以2 3 5的丑数的下标
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i=1; i<n; i++) {
            // 乘以2 3 5的丑数的下一个丑数
            int num2 = dp[i2] * 2;
            int num3 = dp[i3] * 3;
            int num5 = dp[i5] * 5;
            // 取三个数的最小值作为下一个丑数
            dp[i] = Math.min(num2, Math.min(num3, num5));
            // 如果下一个丑数是乘以2的丑数，那么i2++，下一个乘以2的丑数就是dp[i2] * 2
            if (dp[i] == num2) i2++;
            // 如果下一个丑数是乘以3的丑数，那么i3++，下一个乘以3的丑数就是dp[i3] * 3
            if (dp[i] == num3) i3++;
            // 如果下一个丑数是乘以5的丑数，那么i5++，下一个乘以5的丑数就是dp[i5] * 5
            if (dp[i] == num5) i5++;
        }
        return dp[n-1];
    }
}
