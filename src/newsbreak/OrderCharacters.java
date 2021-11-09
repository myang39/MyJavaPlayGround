package newsbreak;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderCharacters {
    public static void main(String[] args) {
        String s = "abcccaba";
        System.out.println(orderCharacters(s));
        System.out.println(orderCharactersII(s));
    }

    // method 1, using bucket sort, time: o(n)
    // space: o(n)
    public static String orderCharacters(String s) {
        int[] bucket = new int[26];
        for (Character c : s.toCharArray()) {
//            System.out.println(c - 'a');
            bucket[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                char x = (char) ('a' + i);
                sb.append(x);
            }
        }
        return sb.toString();
    }

    // method 2, using priority queue
    // For PriorityQueue
    // O(log(n)) time for the enqueing and dequeing methods (offer, poll, remove() and add);
    // linear time for the remove(Object) and contains(Object) methods;
    // and constant time for the retrieval methods (peek, element, and size).

    // time complexity: nlogn for building the priority queue, nlogn for poll  all elements from the queue
    // so nlogn
    // space: n for the priority queue
    public static String orderCharactersII(String s) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> q = new PriorityQueue<>(
                Comparator.comparingInt(a -> a)
        );

        for (Character c : s.toCharArray()) {
            q.add(c);
        }
        while (!q.isEmpty()) {
            sb.append(q.poll());
        }
        return sb.toString();
    }


}
