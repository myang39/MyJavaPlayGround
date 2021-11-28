package collections;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        q.offer(1);
        q.offer(2);
        System.out.println(q);
        System.out.println("q.poll() = " + q.poll());
    }

}
