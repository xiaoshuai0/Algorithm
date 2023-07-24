package LinkList;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode cur = lists[0];
        for (int i = 1; i < lists.length; i++) {
            cur = mergeTwoLists(cur, lists[i]);
        }
        return cur;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeKLists2(lists, 0, lists.length -1);
    }

    public ListNode mergeKLists2(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        int mid = left + ((right - left) >> 1);
        return mergeTwoLists(mergeKLists2(lists, left, mid), mergeKLists2(lists, mid + 1, right));
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

}
