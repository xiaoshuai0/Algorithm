package BackTrack;

import java.util.*;

public class Permute {
//    List<List<Integer>> ans = new LinkedList<>();
//
//    public List<List<Integer>> permute(int[] nums) {
//        /// 记录【路径】
//        LinkedList<Integer> track = new LinkedList<>();
//        // 【路径】中的元素会被标记为true，避免重复使用
//        boolean[] used = new boolean[nums.length];
//        backTrack(nums, track, used);
//        return ans;
//    }
//
//    void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
//        /// 触发结束条件
//        if (track.size() == nums.length) {
//            ans.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            // 排除不合法的选择
//            if (used[i]) {
//                /// nums[i] 已经在track中，跳过
//                continue;
//            }
//            // 做选择
//            track.add(nums[i]);
//            used[i] = true;
//            // 进入下一层决策树
//            backTrack(nums, track, used);
//            // 取消选择
//            track.removeLast();
//            used[i] = false;
//        }
//    }
    List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, track, used);
        return ans;
    }
    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backTrack(nums, track, used);
            track.removeLast();
            used[i] = false;
        }
    }
    List<List<Integer>> ans_subsets = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        subsetsBackTrack(nums, track, 0);
        return ans_subsets;
    }

    private void subsetsBackTrack(int[] nums, LinkedList<Integer> track, int start) {
        ans_subsets.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            subsetsBackTrack(nums, track, i + 1);
            track.removeLast();
        }
    }


    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        letterCombinationsBackTrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private void letterCombinationsBackTrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int start, StringBuffer sb) {
        if (sb.length() == digits.length()) {
            combinations.add(sb.toString());
            return;
        }
        char ch = digits.charAt(start);
        String letters = phoneMap.get(ch);
        for (int j = 0; j < letters.length(); j++) {
            sb.append(letters.charAt(j));
            letterCombinationsBackTrack(combinations, phoneMap, digits, start + 1, sb);
            sb.deleteCharAt(start);
        }
    }
    List<List<Integer>> combinationSum_res = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumBackTrack(candidates, target, 0, new LinkedList<Integer>());
        return combinationSum_res;
    }

    private void combinationSumBackTrack(int[] candidates, int target, int start, LinkedList<Integer> track) {
        if (target < 0) return;
        if (target == 0) {
            combinationSum_res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            track.add(candidates[i]);
            combinationSumBackTrack(candidates, target - candidates[i], i + 1, track);
            track.removeLast();
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateParenthesisBackTrack(combinations, new StringBuffer(), 0, 0, n);
        return combinations;
    }

    private void generateParenthesisBackTrack(List<String> combinations, StringBuffer sb, int left, int right, int max) {
        if (left == max && right == max) {
            combinations.add(sb.toString());
            return;
        }

        if (left < max) {
            sb.append('(');
            generateParenthesisBackTrack(combinations, sb, left+1, right, max);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right < max) {
            sb.append(')');
            generateParenthesisBackTrack(combinations, sb, left, right + 1, max);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
