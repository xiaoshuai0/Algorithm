package SlideWindow;

import java.util.HashMap;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end >= 0; end--) {
                sum += nums[end];
                if (sum == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
    /// pre[i] 为 [0..i][0..i][0..i] 里所有数的和，则 pre[i]可以由 pre[i−1] 递推而来，即：
    ///  pre[i]=pre[i−1]+nums[i]
    /// 那么「[j..i] 这个子数组和为 k 」这个条件我们可以转化为
    /// pre[i]−pre[j−1]==k
    /// 简单移项可得符合条件的下标 j 需要满足
    /// pre[j−1]==pre[i]−k
    public int subarraySum2(int[] nums, int k) {
        int ans = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                ans += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        SubarraySum s = new SubarraySum();
        System.out.println(s.subarraySum(new int[]{-1, -1, 1}, 0));
    }
}
