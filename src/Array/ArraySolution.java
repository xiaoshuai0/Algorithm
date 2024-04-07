package Array;

import LinkList.ListNode;

import java.util.*;

public class ArraySolution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // int mid = (left + right) / 2;
            // (left + right) 可能会存在越界的情况
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else  {
                right = mid - 1;
            }
        }
        return -1;
    }

    int[] twoSum(int[] nums, int target) {
        int letf = 0, right = nums.length - 1;
        while (letf < right) {
            int sum = nums[letf] + nums[right];
            if (sum == target) {
                 return new int[]{letf + 1, right + 1};
            } else if (sum < target) {
                 letf += 1;
            } else {
                right -= 1;
            }
        }
        return new int[]{-1, -1};
    }

    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left ++;
            right --;
        }
    }
    boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            /// 以s[i]为中心的最长回文子串
            String str1 = palindrome(s, i, i);
            /// 以s[i]和s[i+1]为中心的最长回文子串
            String str2 = palindrome(s, i, i + 1);
            res = res.length() < str1.length() ? str1 : res;
            res = res.length() < str2.length() ? str2 : res;
        }
        return res;
    }

    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // 双指针左右展开
            --left;
            ++right;
        }
        return s.substring(left + 1, right);
    }

    public int removeDuplicates(int[] nums) {
       if (nums.length < 2) {
           return nums.length;
       }
       int slow = 0, fast = 1;
       while (fast < nums.length) {
           if (nums[fast] != nums[slow]) {
               slow ++;
               nums[slow] = nums[fast];
           }
           fast ++;
       }
       return slow + 1;
    }
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });


        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < L) {
                res.add(new int[]{L, R});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], R);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newNums = new int[n];
        for (int i = 0; i < n; i++) {
            newNums[(i + k) % n] = nums[i];
        }
        System.arraycopy(newNums, 0, nums, 0, n);
    }

    public static void main(String[] args) {
        ArraySolution s = new ArraySolution();
//        System.out.println(s.longestPalindrome("babad"));
        System.out.println(s.longestPalindrome("cbbd"));
        System.out.println(s.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }
}
