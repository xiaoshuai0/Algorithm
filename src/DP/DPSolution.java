package DP;

import java.lang.reflect.Array;
import java.util.Arrays;

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

    int coinChange(int[] coins, int amount) {
        coinMemory = new int[amount + 1];
        Arrays.fill(coinMemory, -666);
        return coinChange_helper(coins, amount);
    }

    int coinChange_helper(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int sub = coinChange_helper(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    int[] coinMemory;
    int coinChange_helper2(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (coinMemory[amount] != -666) return coinMemory[amount];
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int sub = coinChange_helper(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }
        coinMemory[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return coinMemory[amount];
    }
    int coinChange_helper3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin: coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
