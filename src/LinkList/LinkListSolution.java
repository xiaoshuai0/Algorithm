package LinkList;
import java.util.*;

public class LinkListSolution {
    public ListNode reverseList(ListNode  head)  {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode current = head;

        while (current != null) {
            ListNode tmp = current.next;
            current.next = pre;
            pre = current;
            current = tmp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode  head)  {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = this.reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return head;
    }
    public boolean isPalindrome1(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode cur =  head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.val != stack.pop().val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode headB = slow.next;
        slow.next = null;
        ListNode p2 = this.reverseList(headB);
        headB = p2;
        ListNode p1 = head;
        while (p2 != null) {
            if (p1.val != p2.val) {
                slow.next = this.reverseList(headB);
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = this.reverseList(headB);
        return true;
    }

    public void printNode(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    public ListNode listPartition2(ListNode node, int pivot){
        ListNode sH = null;
        ListNode sT = null;
        ListNode eH = null;
        ListNode eT = null;
        ListNode mH = null;
        ListNode mT = null;
        ListNode next = null;

        while (node != null) {
            next = node.next;
            node.next = null;
            if (node.val < pivot) {
                if (sH == null) {
                    sH = node;
                    sT = node;
                } {
                    sT.next = node;
                    sT = node;
                }
            } else if (node.val == pivot) {
                if (eH == null) {
                    eH = node;
                    eT = node;
                } {
                    eT.next = node;
                    eT = node;
                }
            } else  {
                if (mH == null) {
                    mH = node;
                    mT = node;
                } {
                    mT.next = node;
                    mT = node;
                }
            }
            node = next;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail  = null;
        if (sH != null) {
            dummy.next = sH;
            tail = sT;
        }

        if (eH != null) {
            if (dummy == null) {
                dummy.next = eH;
            } else {
                tail.next = eH;
            }
            tail = eT;
        }

        if (mH != null) {
            if (dummy == null) {
                dummy.next = mH;
            } else {
                tail.next = mH;
            }
            tail = mT;
        }

        return dummy.next;
    }

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
        if (carry > 0)  {
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//        ListNode pre = dummy;
//        ListNode fast = dummy;
//        for (int i = 0; i < n; i++) {
//            fast = fast.next;
//        }
//
//        while (fast != null && fast.next != null) {
//            fast = fast.next;
//            pre = pre.next;
//        }
//        pre.next = pre.next.next;
//        return dummy.next;
//    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;

        while (fast != null) {
            slow = slow.next;

            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
//  ????????????????????????
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        ListNode dummy = new ListNode(0);
//        ListNode current = dummy;
//        while (list1 != null && list2 != null) {
//            if (list1.val <= list2.val) {
//                current.next = list1;
//                list1 = list1.next;
//            } else  {
//                current.next = list2;
//                list2 = list2.next;
//            }
//            current = current.next;
//        }
//
//        current.next = list1 == null ? list2 : list1;
//        return dummy.next;
//    }

    public ListNode mergeKLists(ListNode[] lists) {
//        ListNode ans = null;
//        for (int i = 0; i < lists.length; i++) {
//            ans = mergeTwoLists(ans, lists[i]);
//        }
//        return ans;
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];

        if (l > r) return null;

        int mid = l + ((r - l) >> 1);
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }
//  ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    public ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode next = head.next;
//        head.next = swapPairs(next.next);
//        next.next = head;
//        return next;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummy.next;
    }
/**
 * ??????????????????????????k?????????????????????????????????????????????????????????????????
 *
 * k?????????????????????????????????????????????????????????????????
 *
 * ??????????????????????????k???????????????????????????????????????????????????????????????????????
 *
 * ?????????
 *
 * ?????????????????????????????????????????????????????????????????????????????????
 * ??????????????????????????????????????????????????????????????????????????????????????????
 *
 * ??????????????????LeetCode???
 * ?????????https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * ??????????????????????????????????????????????????????????????????????????????????????????????????????
 * */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;

            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseList(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        // ???????????????????????? k ???n ????????????????????????????????? k % n?????????
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // ?????????????????????????????????????????????????????????????????????
        tail.next = head;
        while (add-- > 0) {
            tail = tail.next;
        }
        ListNode res = tail.next;
        tail.next = null;
        return res;
    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            /// ?????????????????????????????????????????????????????????
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else  {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode pre = null;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
            while (cur != null && cur.next != null && cur.val == cur.next.val) {
                int temp = cur.val;
                while (cur != null && cur.val == temp) {
                    cur = cur.next;
                }
                pre.next = cur;
            }
        }
        return dummy.next;
    }
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode before = new ListNode(0);
        ListNode before_head = before;
        ListNode after = new ListNode(0);
        ListNode after_head = after;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else  {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = after_head.next;
        return before_head.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        ListNode dummy = new ListNode(0);
//        ListNode current = dummy;
//        while (list1 != null && list2 != null) {
//            if (list1.val < list2.val) {
//                current.next = list1;
//                list1 = list1.next;
//            } else {
//                current.next = list2;
//                list2 = list2.next;
//            }
//            current = current.next;
//        }
//
//        if (list1 != null) {
//            current.next = list1;
//        }
//
//        if (list2 != null) {
//            current.next = list2;
//        }
//        return dummy.next;
//    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = this.mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = this.mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
    public static void main(String args[]) {
    }
}


