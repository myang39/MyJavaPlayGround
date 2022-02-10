package misc;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueuebyTwoStack {

    public static void main(String[] args) {
        QueuebyTwoStack qts = new QueuebyTwoStack();
    }

    Deque<Integer> StackFront = new ArrayDeque<>();
    Deque<Integer> StackBack = new ArrayDeque<>();

    public QueuebyTwoStack() {
        // Write your solution here.
        StackFront.add(1);
    }

    public Integer poll() {
        return null;
    }

    public void offer(int element) {

    }

    public Integer peek() {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }
}
