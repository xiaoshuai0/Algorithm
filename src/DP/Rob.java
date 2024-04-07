package DP;

import java.util.Arrays;

public class Rob {
    public int rob(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sub = _helper(nums, i);
            res = Math.max(res, sub);
        }
        return res;
    }
    public int _helper(int[] nums, int start) {
        if (start >= nums.length) return 0;
        int sum = nums[start] + _helper(nums, start + 2);
        return sum;
    }

    public int rob_dp(int[] nums) {
        if (nums.length == 0) return 0;
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int length = nums.length;
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[length];
    }

    int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length ; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    static public void main(String[] args) {
        Rob rob = new Rob();
        System.out.println(rob.rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob.rob(new int[]{2,7,9,3,1}));
    }
}
