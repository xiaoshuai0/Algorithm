package BackTrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BackTrackSolution {
//    List<List<Integer>> res = new LinkedList<>();
//    public List<List<Integer>> permute(int[] nums) {
//        LinkedList<Integer> track = new LinkedList<>();
//        boolean[] used = new boolean[nums.length];
//        backTrack(nums, track, used);
//        return res;
//    }
//
//    public void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
//        if (track.size() == nums.length) {
//            res.add(new LinkedList(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                // nums[i] 已经在track 中，跳过
//                continue;
//            }
//            track.add(nums[i]);
//            used[i] = true;
//            // 进入下一层决策树
//            backTrack(nums, track, used);
//            // 取消选择
//            track.removeLast();
//            used[i] = false;
//        }
//    }
//    List<List<Integer>> subsets_res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        subsets_backTrack(nums, 0);
//        return subsets_res;
//    }
//
//    public void subsets_backTrack(int[] nums, int start) {
//        // 前序位置，每个节点的值都是一个子集
//        subsets_res.add(new LinkedList<>(track));
//
//        for (int i = start; i < nums.length; i++) {
//            // 做选择
//            track.addLast(nums[i]);
//            // 通过 start 控制树枝遍历，避免重复
//            subsets_backTrack(nums, i + 1);
//            // 撤销选择
//            track.removeLast();
//        }
//    }
//    List<List<Integer>> combine_res = new LinkedList<>();
//    LinkedList<Integer> combine_track = new LinkedList<>();
//    public List<List<Integer>> combine(int n, int k) {
//        combine_backTrack(1, n, k);
//        return combine_res;
//    }
//
//    public void combine_backTrack(int start, int n, int k) {
//        if (k == combine_track.size()) {
//            combine_res.add(new LinkedList<>(combine_track));
//            return;
//        }
//        for (int i = start; i <= n; i++) {
//            combine_track.add(i);
//            combine_backTrack(i + 1, n, k);
//            combine_track.removeLast();
//        }
//    }
//
//
    List<List<Integer>> subsetsWithDup_res = new LinkedList<>();
    LinkedList<Integer> subsetsWithDup_track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        subsetsWithDup_backTrack(nums, 0);
        return subsetsWithDup_res;
    }

    public void subsetsWithDup_backTrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        subsetsWithDup_res.add(new LinkedList<>(subsetsWithDup_track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 做选择
            subsetsWithDup_track.addLast(nums[i]);
            // 通过 start 控制树枝遍历，避免重复
            subsetsWithDup_backTrack(nums, i + 1);
            // 撤销选择
            subsetsWithDup_track.removeLast();
        }
    }

//    List<List<Integer>> combinationSum_res = new LinkedList<>();
//    LinkedList<Integer> combinationSum_track = new LinkedList<>();
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        if (candidates.length == 0) return combinationSum_res;
//        combinationSum_backTrack(candidates, 0, target);
//        return combinationSum_res;
//    }
//
//    public void combinationSum_backTrack(int[] candidates, int start ,int target) {
//        if (target < 0) {
//            return;
//        }
//        if (target == 0) {
//            combinationSum_res.add(new LinkedList<>(combinationSum_track));
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            combinationSum_track.add(candidates[i]);
//            combinationSum_backTrack(candidates, i, target - candidates[i]);
//            combinationSum_track.removeLast();
//        }
//    }

    List<List<Integer>> combinationSum_res = new LinkedList<>();
    LinkedList<Integer> combinationSum_track = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) return combinationSum_res;
        // 先排序，让相同的元素靠在一起
        Arrays.sort(candidates);
        combinationSum_backTrack(candidates, 0, target);
        return combinationSum_res;
    }

    public void combinationSum_backTrack(int[] candidates, int start ,int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            combinationSum_res.add(new LinkedList<>(combinationSum_track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combinationSum_track.add(candidates[i]);
            combinationSum_backTrack(candidates, i + 1, target - candidates[i]);
            combinationSum_track.removeLast();
        }
    }
    public static void main(String[] args) {
//        BackTrackSolution s = new BackTrackSolution();
//        System.out.println(s.subsets(new int[] {1, 2, 3}));
    }
}
