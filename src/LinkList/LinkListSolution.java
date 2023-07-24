package LinkList;

public class LinkListSolution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            // 比较 list1 和 list2 两个指针
            // 较小的节点接到cur指针
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else  {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 != null ? list1 : list2;
        return dummy.next;
    }

//    public ListNode partition(ListNode head, int x) {
//        /// 存放小于x的队列
//        ListNode dummy_s = new ListNode(0);
//        ListNode s = dummy_s;
//        /// 存放大于x的队列
//        ListNode dummy_b = new ListNode(0);
//        ListNode b = dummy_b;
//        while (head != null) {
//            if (head.val < x) {
//                s.next = head;
//                s = s.next;
//            } else  {
//                b.next = head;
//                b = b.next;
//            }
//            // 断开原链表中的每个节点的 next 指针
//            ListNode temp = head.next;
//            head.next = null;
//            head = temp;
//        }
//        s.next = dummy_b.next;
//        return dummy_s.next;
//    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummy_s = new ListNode(-1);
        ListNode s = dummy_s;
        ListNode dummy_b = new ListNode(-1);
        ListNode b = dummy_b;
        while (head != null) {
            if (head.val < x) {
                s.next = head;
                s = s.next;
            } else {
                b.next = head;
                b = b.next;
            }
            ListNode temp = head.next;
            head.next = null;
            head = temp;
        }
        s.next = dummy_b.next;
        return dummy_s;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        return _merge(lists, 0, lists.length - 1);
    }

    public ListNode _merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = left + ((right - left) >> 1);
        return mergeTwoLists(_merge(lists, left, mid), _merge(lists, mid + 1, right));
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        // 先走 k 步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // 同时走 n - k 步
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return slow;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode pre = getKthFromEnd(dummy, n + 1);
        // 删除倒数第n个节点
        pre.next = pre.next.next;
        return dummy.next;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        // 没有环
        if (fast == null || fast.next == null) return null;
        slow = head;
        // 慢指针走 k 步， 快指针走 2k，
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            // p1 走一步，如果走到 A 链表末尾，转到 B 链表
            p1 = p1 == null ? headB : p1.next;
            // p2 走一步，如果走到 B 链表末尾，转到 A 链表
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
