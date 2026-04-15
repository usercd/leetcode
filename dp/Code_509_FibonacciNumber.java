package dp;

import java.util.Arrays;

public class Code_509_FibonacciNumber {
    public int fib(int n) {
        if (n<=1) return n;
        int f0 = 0, f1 = 1;
        for (int i=2, cur; i<=n; i++) {
            cur = f0 + f1;
            f0 = f1;
            f1 = cur;
            // dp[i] = dp[i-1] + dp[i-2];
        }

        return f1;
    }    

    public int fib3(int n) {
        if (n<=1) return n;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    public int fib2(int n) {
        if (n<=1) return n;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return f1(n, dp);
    }

    private int f1(int n, int[] dp) {
        if (n<=1) return n;
        if (dp[n] != -1) return dp[n];
        dp[n] = f1(n-1, dp) + f1(n-2, dp);
        return dp[n];
    }

    public int fib1(int n) {
        if (n<=1) return n;
        return fib(n-1) + fib(n-2);
    }
}
