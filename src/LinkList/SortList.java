package LinkList;

public class SortList {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return head;

        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = mergeTwoLists(list1, list2);
        return sorted;
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode head = ListNode.generateLinkList(new int[]{4, 2, 1, 3});
        SortList s = new SortList();
        s.sortList(head).print();
    }
}
