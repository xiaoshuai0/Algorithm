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

    public ListNode partition(ListNode head, int x) {
        /// 存放小于x的队列
        ListNode dummy_s = new ListNode(0);
        ListNode s = dummy_s;
        /// 存放大于x的队列
        ListNode dummy_b = new ListNode(0);
        ListNode b = dummy_b;
        while (head != null) {
            if (head.val < x) {
                s.next = head;
                s = s.next;
            } else  {
                b.next = head;
                b = b.next;
            }
            // 断开原链表中的每个节点的 next 指针
            ListNode temp = head.next;
            head.next = null;
            head = temp;
        }
        s.next = dummy_b.next;
        return dummy_s.next;
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
}
