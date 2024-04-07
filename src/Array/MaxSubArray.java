package Array;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        int ans = dp[0];
        for (int i = 1; i < len; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSubArray m = new MaxSubArray();
        System.out.println(m.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
