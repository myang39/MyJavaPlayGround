package misc;

import java.util.*;

public class FindtheMinimumandMaximumNumberofNodesBetweenCriticalPoints {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public static void main(String[] args) {
        ListNode head = arrayToList(new int[]{5,3,1,2,5,1,2});
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(head)));
    }
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        List<Integer> list = new ArrayList<>();
        ListNode pre = head;
        ListNode cur = head.next;
        int i = 1;

        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            if (cur.val < pre.val && cur.val < next.val || cur.val > pre.val && cur.val > next.val) {
                list.add(i);
            }
            i++;
            pre = cur;
            cur = next;
        }

//        System.out.println("list: " + list);

        if (list.size() < 2) {
            return new int[]{-1, -1};
        }

        int[] ret = new int[2];
        ret[1] = list.get(list.size() - 1) - list.get(0);
        int minDis = ret[1];
        for (int j = 1; j < list.size(); j++) {
            int curDis = list.get(j) - list.get(j - 1);
            if (curDis < minDis) {
                minDis = curDis;
            }
        }
        ret[0] = minDis;
        return ret;
    }

    private static ListNode arrayToList(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i: array) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return dummy.next;
    }

    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> fq = new LinkedList<>();
        Queue<Integer> bq = new LinkedList<>();

        Map<Integer, Integer> forwardMap = new HashMap<>();
        Map<Integer, Integer> backwardMap = new HashMap<>();

        fq.offer(start);
        bq.offer(goal);
        forwardMap.put(start, 0);
        backwardMap.put(goal, 0);

        int k = 0;
        while(k <= 500) {
            int size = fq.size();
            for(int i = 0; i < size; ++i) {
                int num = fq.poll();
                int step = forwardMap.get(num);
                if(backwardMap.containsKey(num)) {
                    return forwardMap.get(num) + backwardMap.get(num);
                }
                for(int n: nums) {
                    if(!forwardMap.containsKey(num + n)) {
                        forwardMap.put(num + n, step + 1);
                        fq.offer(num + n);
                    }
                    if(!forwardMap.containsKey(num - n)) {
                        forwardMap.put(num - n, step + 1);
                        fq.offer(num - n);
                    }
                    if(!forwardMap.containsKey(num ^ n)) {
                        forwardMap.put(num ^ n, step + 1);
                        fq.offer(num ^ n);
                    }
                }
            }

            size = bq.size();
            for(int i = 0; i < size; ++i) {
                int num = bq.poll();
                int step = backwardMap.get(num);
                if(forwardMap.containsKey(num)) {
                    return forwardMap.get(num) + backwardMap.get(num);
                }
                for(int n: nums) {
                    if(!backwardMap.containsKey(num + n)) {
                        backwardMap.put(num + n, step + 1);
                        bq.offer(num + n);
                    }
                    if(!backwardMap.containsKey(num - n)) {
                        backwardMap.put(num - n, step + 1);
                        bq.offer(num - n);
                    }
                    if(!backwardMap.containsKey(num ^ n)) {
                        backwardMap.put(num ^ n, step + 1);
                        bq.offer(num ^ n);
                    }
                }
            }
            k++;
        }

        return -1;
    }


}
