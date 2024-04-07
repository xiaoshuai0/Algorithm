package BackTrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermuteUnique {
    List<List<Integer>> permuteUnique_res = new LinkedList<>();
    LinkedList<Integer> permuteUnique_track = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backTrack(nums, used);
        return permuteUnique_res;
    }

    private void backTrack(int[] nums, boolean[] used) {
        if (nums.length == permuteUnique_track.size()) {
            permuteUnique_res.add(new LinkedList<>(permuteUnique_track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            permuteUnique_track.addLast(nums[i]);
            used[i] = true;

            backTrack(nums, used);

            permuteUnique_track.removeLast();
            used[i] = false;
        }
    }
    void println(List<List<Integer>> nums) {
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                System.out.print(nums.get(i).get(j));
                System.out.print("      ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        PermuteUnique p = new PermuteUnique();
        p.println(p.permuteUnique(new int[]{1, 1, 2}));

    }
}
