package DP;

public class Fib {
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fibMemory(int n) {
        int[] memory = new int[n + 1];
        return fibMemoryHelper(n, memory);
    }
    /// 带备忘录进行递归
    public int fibMemoryHelper(int n, int[] memory) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }
        // 已经计算过，不用再计算了,剪枝优化
        if (memory[n] != 0) {
            return memory[n];
        }
        memory[n] = fibMemoryHelper(n - 1, memory) + fibMemoryHelper(n - 2, memory);
        return memory[n];
    }

    public int fibArray(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib_dp(int n) {
        if (n == 0) {
            return 0;
        }
        int dp_i_1 = 1, dp_i_2 = 0;
        for (int i = 2; i <= n ; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }
}
