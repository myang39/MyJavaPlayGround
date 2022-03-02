package labuladong.linkedlist;

public class ListNode {

    static class Node {
        int val;
        Node next;

        public Node (int val) {
            this.val = val;
        }
    }

    public static Node arrayToLinkedList(int[] array) {
        Node dummy = new Node(4);
        Node head = dummy;
        for (int i = 0; i < array.length; i++) {
            head.next = new Node(array[i]);
            head = head.next;
        }
        return dummy.next;
    }

    public static void print(Node h) {
        for (;h != null ;h = h.next) {
            System.out.print(h.val);
            if (h.next != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        Node h1 = arrayToLinkedList(new int[]{1,2,3,4,5});
        print(h1);
//        print(reverseN(h1, 3));
        print(reverseBetween(h1, 2,6 ));
    }

    /***
     * Recursively reverse the first N nodes
     * @param head
     * @param n
     * @return
     */
    public static Node reverseN(Node head, int n) {
        Node[] successor = new Node[1];
        return reverseN(head, n, successor);
    }

    public static Node reverseN(Node head, int n, Node[] successor) {
        if (n == 1) {
            successor[0] = head.next;
            return head;
        }

        Node last = reverseN(head.next, n - 1, successor);
        head.next.next = head;
        head.next = successor[0];
        return last;
    }

    /***
     * Iteratively reverse nodes from range m to n
     * assume m and n already valid
     * Algo:
     * loop to the m-1's node,
     * mark m-1's node as start,
     * mark m's next as nodeM,
     * reverse nodeM to nodeN,
     * mark nodeN's next node as successor,
     * start.next = nodeN
     * nodeM.next = successor
     */
    public static Node reverseBetween(Node head, int m , int n) {
        if (m >= n) {
            return head;
        }
        Node dummy = new Node(-1);
        dummy.next = head;
        Node start = dummy;
        Node nodeM;
        Node nodeN;

        for (int i = 1; i < m; i++) {
            start = start.next;
        }
        nodeM = start.next;

        // reverse m to n, and mark nodeN, and successor
        Node cur = nodeM;
        Node prev = null;
        Node next = null;

        for (int i = m; i <= n && cur != null; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        nodeN = prev;
        Node successor = cur;
        start.next = nodeN;
        nodeM.next = successor;
        return dummy.next;

    }

}
