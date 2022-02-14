import org.jetbrains.annotations.NotNull;

public class LongestPalindrome {
    /**********************************暴力解法**********************************/

    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
    public String longestPalindrome1(String s) {
        String res = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String temp = s.substring(i, j);
                if (isPalindromic(temp) && temp.length() > res.length()) {
                    res = temp;
                }
            }
        }
        return res;
    }

    /**********************************中心扩展算法**********************************/
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int len1 = this.expandAroundCenter(s, i, i);
            int len2 = this.expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > res.length()) {
                int start = i - (len - 1) / 2;
                int end = i + len / 2;
                res = s.substring(start, end + 1);
            }
        }
        return res;
    }
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String args[]) {
        LongestPalindrome l = new LongestPalindrome();
        System.out.println(l.longestPalindrome1("babad"));
    }
}
