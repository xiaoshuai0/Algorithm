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

}
