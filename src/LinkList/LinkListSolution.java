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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

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
//  合并俩个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else  {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        current.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

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
//  给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
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
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
        // 当向右移动的次数 k ≥n 时，我们仅需要向右移动 k % n次即可
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // 可以先将给定的链表连接成环，然后将指定位置断开
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
            /// 当前节点和下个节点相同时，删除下个节点
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
    public static void main(String args[]) {
    }
}


