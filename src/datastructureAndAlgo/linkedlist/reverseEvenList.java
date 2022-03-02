package datastructureAndAlgo.linkedlist;

public class reverseEvenList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static void main(String[] args) {
//        int[] array = {5,2,6,3,9,1,7,3,8,4};
//        int[] array = {1, 2};
        int[] array = {0,4,2,1,3};
        ListNode head = arrayToList(array);
        printList(head);
//        System.out.println();
//        printList(reverse(head));
        head = reverseEvenLengthGroups(head);
        System.out.println("\n final answer: ");
        printList(head);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    private static ListNode arrayToList(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i : array) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }



    public static ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 5,6,2,3,9,1,4,8,3,7]
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        ListNode pre = dummy;
        int length = 1;
        int count = 1;
        ListNode next = head;
        while (next != null) {
            pre = count % 2 == 0 ? slow : fast;
            slow = next;
            fast = next;
            length++;
            count = 1;
            while (count < length && fast != null && fast.next != null) {
                fast = fast.next;
                count++;
            }
            System.out.println(
                    "pre: " + pre.val +
                    "slow: " + slow.val + " fast: " +  fast.val +
                            "next: " + next.val
            );
            next = fast.next;
            if (count % 2 == 0) {
                fast.next = null;
                pre.next = reverse(slow);
                slow.next = next;
                System.out.println("\n print list: ");
                printList(head);
            }

        }
        return dummy.next;
    }

    // iterative, return new head
    private static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
