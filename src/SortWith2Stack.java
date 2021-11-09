import java.util.Arrays;
import java.util.LinkedList;

public class SortWith2Stack {
    public static void main(String[] args) {
        LinkedList<Integer> s1 = new LinkedList<>();
        s1.offerLast(1);
        s1.offerLast(2);
        s1.offerLast(3);
        sort(s1);
        System.out.println(s1.size());
        System.out.println(Arrays.toString(s1.toArray()));
    }

    public static void sort(LinkedList<Integer> s1) {
        LinkedList<Integer> s2 = new LinkedList<>();
        // Write your solution here.
        int size = s1.size();
        int max;
        int freq = 1;
        int top = 0;
        while (top < size) {
            int idx = size - 1;
            max = Integer.MIN_VALUE;
            while (!s1.isEmpty() && idx >= top) {
                int cur = s1.pollLast();
                if (cur > max) {
                    max = cur;
                    freq = 1;
                } else if (cur == max) {
                    freq++;
                }
                s2.offerLast(cur);
                idx--;
            }

            top += freq;
//            System.out.println("freq " + freq + " max " + max + " top " + top);
            while (freq > 0) {
                s1.offerLast(max);
//                System.out.println("offer " + max);
                freq--;
            }

//            System.out.println("s2 :" + Arrays.toString(s2.toArray()));
            while (!s2.isEmpty()) {
                int cur = s2.pollLast();
                if (cur != max) {
                    s1.offerLast(cur);
                }
            }

//            System.out.println("s1 :" + Arrays.toString(s1.toArray()));
        }
    }
}
