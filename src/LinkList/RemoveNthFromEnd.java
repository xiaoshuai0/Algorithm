package LinkList;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /// 删除倒数第N个节点要找到倒数第N+1
        ListNode pre = getKthFromEnd(dummy, n + 1);
        /// 删除倒数第N个节点
        pre.next = pre.next.next;
        return dummy.next;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        // 先走K部
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // 同时走n-k部
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 现在指向 n - k + 1个节点，即倒数第K个节点
        return slow;
    }
}
