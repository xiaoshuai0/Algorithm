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
            } else  {
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
    public static void main(String args[]) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
//        ListNode node3 = new ListNode(3);
//        node2.next = node3;
        ListNode node4 = new ListNode(2);
        node2.next = node4;
        ListNode node5 = new ListNode(1);
        node4.next = node5;
        LinkListSolution solution = new LinkListSolution();
        System.out.println(solution.isPalindrome1(head));
        solution.printNode(head);


    }
}


