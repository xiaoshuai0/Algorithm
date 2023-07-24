package Hot;

import LinkList.ListNode;

import java.sql.Array;
import java.util.*;


public class HotHundred {

    public int[] twoSum(int[] nums, int target) {
        /// 用于存储遍历过的数据 key 是值 value是位置
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

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str: strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int length = nums.length;
        for (int first = 0; first < length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int thrid = length - 1;
            int target = -nums[first];
            for (int second = first + 1; second < length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < thrid && nums[second] + nums[thrid] > target) {
                    thrid--;
                }
                if (second == thrid) {
                    break;
                }
                if (nums[second] + nums[thrid] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[thrid]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode last = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return head;
        ListNode pre = null;
        ListNode cur = head;
        while (head != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();
        ListNode cur = head;
        while (cur != null) {
            vals.add(cur.val);
            cur = cur.next;
        }

        int left = 0, right = vals.size() - 1;
        while (left < right) {
            if (vals.get(left) != vals.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }
//    public boolean isPalindrome2(ListNode head) {
//        if (head == null) return true;
//
//    }
//
//    private ListNode endOfFirstHalf(ListNode head) {
//
//    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
