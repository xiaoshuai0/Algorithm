package SlideWindow;

import java.util.LinkedList;

public class MaxSlidingWindow {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int length = nums.length;
//        int[] ans = new int[length - k + 1];
//
//        for (int i = 0; i < length - k + 1; i++) {
//            int max = nums[i];
//            for (int j = i + 1; j < k + i; j++) {
//                max = Math.max(max, nums[j]);
//            }
//            ans[i] = max;
//        }
//        return ans;
//    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值从大到小排序
        LinkedList<Integer> queue = new LinkedList();

        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if(i+1 >= k){
                ans[i+1-k] = nums[queue.peek()];
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        MaxSlidingWindow m = new MaxSlidingWindow();
        System.out.println(m.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }
}
