package String;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 =  palindrome(s, i, i);
            String s2 =  palindrome(s, i, i + 1);
            if (s1.length() > ans.length()) ans = s1;
            if (s2.length() > ans.length()) ans =s2;
        }
        return ans;
    }

    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return s.substring(left + 1, right);
    }

    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char ch1 = s.charAt(i);
            for (int j = i + 1; j < length; j++) {
                char ch2 = s.charAt(j);
                if (ch1 == ch2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int num = prices[i];
            if (num <= min) {
                min = num;
            } else {
                maxProfit = Math.max(maxProfit, num - min);
            }
        }
        return maxProfit;
    }
}
