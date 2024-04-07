package Practice;

import Array.ArraySolution;
import LinkList.ListNode;

import java.util.*;

public class Solution {

    /// 20240218
    /// 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            }
            map.put(num, i);
        }
        return new int[0];
    }
    /// 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
    /// 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (map.containsKey(ch)) {
                /// 已经出现过所以找到出现的位置
                /// 取max 是为了防止已经出现过但是没有在start之后出现的这种情况
                start = Math.max(start, map.get(ch) + 1);
            }
            res = Math.max(res, i - start + 1);
            map.put(ch, i);
        }
        return res;
    }
    /// 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }

            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }
            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else  {
            return nums[count / 2];
        }
    }
    /// 最长回文子串
    public String longestPalindrome(String s) {
       String ans = "";
       for (int i = 0; i < s.length(); i++) {
           String s1 = palindrome(s, i, i);
           String s2 = palindrome(s, i, i + 1);
           if (s1.length() > ans.length()) ans = s1;
           if (s2.length() > ans.length()) ans = s2;
       }
       return ans;
    }
    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    ///
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for (char c: s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row: rows) res.append(row);
        return res.toString();
    }
    /// 整数翻转
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit < -8)) {
                return 0;
            }
            res = res * 10 + digit;
            res /= 10;
        }
        return res;
    }
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0) ) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
    public int myAtoi(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int index = 0;
        /// 去除前置的
        while (index < length && chars[index] == ' ') {
            index++;
        }
        // 极端情况
        if (index == length) {
            return 0;
        }
        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+') {
            index += 1;
        } else {
            sign = -1;
            index += 1;
        }
        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < length) {
            char cur = chars[index];
            if (cur > '9' || cur < '0') {
                break;
            }
            int value = (cur - '0') * sign;
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && value > 7)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && value < -8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + value;
            index ++;
        }
        return res;
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            int temp = 0;
            int width = right - left;
            if (height[left] < height[right]) {
                temp = width * height[left];
                left++;
            } else {
                temp = width * height[right];
                right--;
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }
    public String intToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[(num % 1000) / 100]);
        roman.append(tens[(num % 100) / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }

    public int romanToInt(String s) {
        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = symbolValues.get(s.charAt(i));
            if (i < s.length() - 1 && value < symbolValues.get(s.charAt(i + 1))){
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int length = strs.length;
        for (int i = 1; i < length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }
    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int length = nums.length;

        for (int first = 0; first < length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = length - 1;
            int target = -nums[first];
            for (int second = first + 1; second < length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printlnArray(solution.twoSum(new int[]{2,7,11,15}, 26));
        System.out.println(solution.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    public void printlnArray(int[] nums) {
        System.out.println("打印数组开始：");
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        System.out.println("打印数组结束");


    }

}

