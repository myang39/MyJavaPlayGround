package datastructureAndAlgo;

import java.util.*;

public class StackOfStacks {
    TreeSet<Integer> notEmpty;
    TreeSet<Integer> notFull; // including empty
    int size = 0;
    int capacity;
    List<Deque<Integer>> lists;

    StackOfStacks(int capacity) {
        this.capacity = capacity;
        lists = new ArrayList<>();
        notEmpty = new TreeSet<>();
        notFull = new TreeSet<>();
    }

    // push into the left most not full stack
    void push(int val) {
        if (notFull.isEmpty()) {
            lists.add(new ArrayDeque<Integer>());
            notFull.add(size++);
        }
        int idx = notFull.first();
        Deque<Integer> s = lists.get(idx);
        s.offerFirst(val);
        if (s.size() == capacity) {
            notFull.remove(idx);
        }
        notEmpty.add(idx);
    }

    // pop from the right most not empty stack
    int pop() {
        if (notEmpty.isEmpty()) {
            return -1;
        }
        int idx = notEmpty.last();
        Deque<Integer> s = lists.get(idx);
        Integer val = s.pollFirst();
        if (s.size() == 0) {
            notEmpty.remove(idx);
        }
        notFull.add(idx);
        return val;
    }

    int popAtStack(int idx) {
        if (idx >= size) {
            return -1;
        }
        Deque<Integer> s = lists.get(idx);
        if (s.isEmpty()) {
            return -1;
        }
        Integer val = s.pollFirst();
        if (s.size() == 0) {
            notEmpty.remove(idx);
        }
        notFull.add(idx);
        return val;
    }
}
