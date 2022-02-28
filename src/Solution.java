import org.jetbrains.annotations.NotNull;
import LinkList.ListNode;

import java.util.*;

public class Solution {
    /*
     * 1. 两数相加
     * */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            }
            map.put(num, i);
        }
        return new int[0];
    }
    /*
    * 2. 两数相加
    * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
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
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        if (carry != 0) {
            current = new ListNode(carry);
        }
        return dummy.next;
    }
    /*
        输入: s = "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    * */
    public int lengthOfLongestSubstring(@NotNull String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int length = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            Character str = s.charAt(i);
            if (map.containsKey(str)) {
                start = Math.max(start, map.get(str) + 1);
            }
            length = Math.max(length, i - start + 1);
            map.put(str, i);
        }
        return length;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;
        while (true) {
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k-= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
    public boolean isValid(String s) {
        Deque<Character> queue = new LinkedList<Character>();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (queue.isEmpty() || queue.peek() != pairs.get(ch)) {
                    return false;
                }
                queue.pop();
            } else {
                queue.push(ch);
            }
        }
        return queue.isEmpty();
    }
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
//        generateAll(new char[2 * n], 0, combinations);
//        dfs(combinations, "", 0, 0, n);
        backtrack(combinations, new StringBuffer(), 0, 0, n);
        return combinations;

    }
    public void backtrack(List<String> ans, StringBuffer cur, int left, int right, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (left < max) {
            cur.append('(');
            backtrack(ans, cur, left + 1, right, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (right < left) {
            cur.append(')');
            backtrack(ans, cur, left, right + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
    public void dfs(List<String> ans, String cur, int left, int right, int max) {
        if (left == max && right == max) {
            ans.add(cur.toString());
            return;
        }

        if (left < right) {
            return;
        }

        if (left < max) {
            dfs(ans, cur + "(", left + 1, right, max);
        }

        if (right < max) {
            dfs(ans, cur + ")", left, right + 1, max);
        }
    }
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (vaild(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean vaild(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
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

        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }
    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (digits.length() == index) {
            combinations.add(combination.toString());
            return;
        }
        char chr = digits.charAt(index);
        String s = phoneMap.get(chr);
        int lettersCount = s.length();
        for (int i = 0; i < lettersCount; i++) {
            combination.append(s.charAt(i));
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
    public static void main(String args[]) {
        Solution s = new Solution();
        System.out.println(s.generateParenthesis(3));
        System.out.println(s.letterCombinations("23"));
    }
}
