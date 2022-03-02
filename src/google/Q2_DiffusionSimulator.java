package google;

import java.util.*;

public class Q2_DiffusionSimulator {

    static Deque<Node> stack = new ArrayDeque<>();

    static Node a = new Node('a', 0);
    static Node b = new Node('b', 0);
    static Node c = new Node('c', 0);
    static Node d = new Node('d', 0);
//    static Node e = new Node('e', 0);

    public static void main(String[] args) {
        a.neighbors.add(b);
        a.neighbors.add(c);

        b.neighbors.add(a);
        b.neighbors.add(c);
        b.neighbors.add(d);

        c.neighbors.add(a);
        c.neighbors.add(b);
        c.neighbors.add(d);

        d.neighbors.add(b);
        d.neighbors.add(c);

        trigger(a, 10);
        while (step()) {
            printNodes(a, b, c, d);
        }
    }

    static class Node {
        char label;
        double val;
        List<Node> neighbors;

        Node(char c, double v) {
            label = c;
            val = v;
            neighbors = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return label == node.label && val == node.val && Objects.equals(neighbors, node.neighbors);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public String toString() {
            return label + " " + val;
        }
    }

    static void trigger(Node node, int k) {
        node.val = k;
        stack.add(node);
    }

    /**
     * time: O(Ed + Vd), Vd is the set of nodes that needed to be diffused, and Ed is a set of edges connecteed to the nodes
     *
     */
    static boolean step() {
        Deque<Node> newStack = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        while (!stack.isEmpty()) {
            Node node = stack.pollFirst();
            if (node.neighbors.size() == 0) {
                continue;
            }
            double valueSum = node.val + sumOfNeighbors(node);
            double avg = valueSum / (1 + node.neighbors.size());
            node.val = avg;
            for (Node neighbor : node.neighbors) {
                if (neighbor.val != avg) {
                    neighbor.val = avg;
                    if (!set.contains(neighbor.label)) {
                        set.add(neighbor.label);
                        newStack.offerFirst(neighbor);
                    }
                }
            }
        }
        stack = newStack;
        return !stack.isEmpty();
    }

    static double sumOfNeighbors(Node node) {
        double sum = 0;
        if (node == null || node.neighbors.size() == 0) {
            return sum;
        }
        for (Node neighbor : node.neighbors) {
            sum += neighbor.val;
        }
        return sum;
    }

    static void printNodes(Node ... nodes) {
        for (Node node : nodes) {
            System.out.println(node);
        }
        System.out.println();
    }




}
