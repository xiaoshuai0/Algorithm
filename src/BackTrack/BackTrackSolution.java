package BackTrack;

import java.util.LinkedList;
import java.util.List;

public class BackTrackSolution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, track, used);
        return res;
    }

    public void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                // nums[i] 已经在track 中，跳过
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backTrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }
}
