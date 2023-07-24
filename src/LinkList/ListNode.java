package LinkList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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

    public static ListNode generateLinkList(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return dummy.next;
    }
    public void print() {
        ListNode cur = this;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
