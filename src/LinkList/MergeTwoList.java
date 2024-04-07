package LinkList;

public class MergeTwoList {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            // 比较 list1 和 list2 两个指针
            // 将较小的节点接到 cur
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            // cur 不断前进
            cur = cur.next;
        }

        if (list1 != null) cur.next = list1;

        if (list2 != null) cur.next = list2;

        return dummy.next;
    }
    public ListNode partition(ListNode head, int x) {
        ListNode dummy_s = new ListNode(-1), dummy_b = new ListNode(-1);
        ListNode s = dummy_s, b = dummy_b;
        while (head != null) {
            if (head.val < x) {
                s.next = head;
                s = s.next;
            } else {
                b.next = head;
                b = b.next;
            }
            ListNode next = head.next;
            head.next = null;
            head = next;
        }
        s.next = dummy_b.next;
        return dummy_s.next;
    }

    ListNode mergeKLists(ListNode[] lists) {
//       if (lists.length == 0) return null;
//       if (lists.length == 1) return lists[0];
//       ListNode res = lists[0];
//       for (int i = 1; i < lists.length; i++) {
//           res = mergeTwoLists(res, lists[i]);
//       }
//       return res;
        return mergeKLists2(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists2(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = left + ((right - left) >> 1);
        return mergeTwoLists(mergeKLists2(lists, left, mid), mergeKLists2(lists, mid + 1, right));
    }

    public ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;

        /// p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }

        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = findFromEnd(head, n + 1);
        pre.next = pre.next.next;
        return dummy.next;
    }

    ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    boolean hasCycle(ListNode head) {
        // 初始化快慢指针指向head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        // 初始化快慢指针指向head
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2 = headA;
            } else  {
                p2 = p2.next;
            }
        }
        return p1;
    }
}
