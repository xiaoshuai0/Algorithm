package LinkList;

import java.util.LinkedList;

public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            head.next.next = null;
            pre.next = reverseList(head);
            pre = head;
            head = next;
        }
        if (head != null) {
            pre.next = head;
        }
        return dummy.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs2(newHead.next);
        newHead.next = head;
        return newHead;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    public void prient(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
    public static void main(String[] args ) {
        ListNode node = generateLinkList(3);
        SwapPairs swap = new SwapPairs();
        swap.prient(swap.swapPairs(node));
    }

    public static ListNode generateLinkList(int num) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < num; i++) {
            cur.next = new ListNode(i + 1);
            cur = cur.next;
        }

        return dummy.next;
    }
}
