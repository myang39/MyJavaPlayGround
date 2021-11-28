import java.util.Deque;
import java.util.LinkedList;

public class StackWithMin {
    public static void main(String[] args) {
        StackWithMin sol = new StackWithMin();
    }

    Deque<Integer> stack;
    Deque<Integer> minStack;
    public StackWithMin() {
        // write your solution here
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int cur = stack.poll();
        if (cur == minStack.peek()) {
            minStack.poll();
        }
        return cur;
    }

    public void push(int element) {
        stack.offer(element);
        if (minStack.isEmpty() || minStack.peek() >= element) {
            minStack.offer(element);
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int min() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }
}
