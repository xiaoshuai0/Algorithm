package LinkList;

public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = head, pre = dummy;
        while (cur != null) {
            ListNode tail = getKntNode(cur, k);
            if (tail == null) {
                pre.next = cur;
                break;
            }
            ListNode newHead = tail.next;
            tail.next = null;
            pre.next = reverseList(cur);
            pre = cur;
            cur = newHead;
        }
        return dummy.next;
    }
    public ListNode getKntNode(ListNode head , int num) {
        ListNode node = head;
        for (int i = 0; i < num - 1; i++) {
            if (node == null) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
