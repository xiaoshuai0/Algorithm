package DP;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    int[] memory;
    public int coinChangeMemory(int[] coins, int amount) {
        memory = new int[amount + 1];
        Arrays.fill(memory, -666);
        return coinChangeMemoryHelper(coins, amount);
    }

    public int coinChangeMemoryHelper(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memory[amount] != -666) {
            return memory[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        memory[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memory[amount];
    }

    public int coinChange_dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin:
                 coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[amount - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(c.coinChange(new int[]{2}, 3));
        System.out.println(c.coinChange(new int[]{1}, 0));
    }
}
