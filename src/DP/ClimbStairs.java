package DP;

import java.util.*;

public class ClimbStairs {
    public int climbStairs(int n) {
//        if (n == 1) return 1;
//        if (n == 2) return 2;
//        return climbStairs(n - 1) + climbStairs(n - 2);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i -  1).get(j - 1) + ans.get(i -  1).get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        System.out.println(c.climbStairs(2));
        System.out.println(c.climbStairs(3));
    }
}
