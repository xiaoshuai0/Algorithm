package DP;

public class DPSolution {

    public int fib(int n) {
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        int [] memory = new int[n + 1];
        return helper(n, memory);
    }

    private int helper(int n, int[] memory) {
        if (n < 2) return n;
        if (memory[n] != 0) return memory[n];
        memory[n] = helper(n-1, memory) + helper(n - 2, memory);
        return memory[n];
    }


    public int fib3(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib4(int n) {
        if (n < 2) return n;
        int dp_i_1 = 1, dp_i_2 = 0;
        for (int i = 2; i <= n; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
