package google;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Q2_DiffusionSimulator {
    static Node a = new Node('a', 0);
    static Node b = new Node('b', 0);
    static Node c = new Node('c', 0);
    static Node d = new Node('d', 0);
    static Node e = new Node('e', 0);

    Set<Node> set = new HashSet<>();

    public static void main(String[] args) {
        a.neighbors.add(c);
        b.neighbors.add(c);

        c.neighbors.add(a);
        c.neighbors.add(b);
        c.neighbors.add(d);

        d.neighbors.add(c);
        d.neighbors.add(e);

        e.neighbors.add(d);

    }

    static class Node {
        char label;
        int val;
        List<Node> neighbors;

        Node(char c, int v) {
            label = c;
            val = v;
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
            return Objects.hash(label, val, neighbors);
        }

        @Override
        public String toString() {
            return label + " " + val;
        }
    }

    void trigger(Node node, int k) {
        node.val = k;
        set.add(node);
    }

    void step() {

    }



}
