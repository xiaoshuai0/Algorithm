package BackTrack;

import java.util.LinkedList;
import java.util.List;

public class Subsets {
    List<List<Integer>> subsets_res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets_backTrack(nums, 0);
        return subsets_res;
    }

    public void subsets_backTrack(int[] nums, int start) {
        /// 前序位置添加
        subsets_res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.addLast(nums[i]);
            subsets_backTrack(nums, start++);
            track.removeLast();
        }
    }
}
