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
    List<List<Integer>> subsets_res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets_backTrack(nums, 0);
        return subsets_res;
    }

    public void subsets_backTrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        subsets_res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 通过 start 控制树枝遍历，避免重复
            subsets_backTrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        BackTrackSolution s = new BackTrackSolution();
        System.out.println(s.subsets(new int[] {1, 2, 3}));
    }
}
