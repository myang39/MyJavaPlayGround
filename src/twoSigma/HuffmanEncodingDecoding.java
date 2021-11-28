package twoSigma;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncodingDecoding {
    public static void main(String[] args) {
        String encoded = encode("HelloWorld Big Panda Hello World");
        System.out.println(encoded);
        System.out.println(decode(encoded));
    }

    static class Node {
        char c;
        int val;
        Node left;
        Node right;

        public Node(char c, int val, Node l, Node r) {
            this.c = c;
            this.val = val;
            left = l;
            right = r;
        }
    }

    static Node root;
    static Map<Character, String> huffmanMap;
    private static void createHuffmanMap(Node node, String encoding, Map<Character, String> huffmanMap) {
        if(node.left == null && node.right == null) {
            huffmanMap.put(node.c, encoding);
            return;
        }

        createHuffmanMap(node.left, encoding + "0" , huffmanMap);
        createHuffmanMap(node.right, encoding + "1" , huffmanMap);
    }

    public static String encode(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((i, j) -> i.val - j.val);
        for(char c: s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> e: countMap.entrySet()) {
            pq.offer(new Node(e.getKey(), e.getValue(), null, null));
        }

        while(pq.size() > 1) {
            Node node1 = pq.poll();
            Node node2 = pq.poll();
            Node parent = new Node(' ', node1.val + node2.val, node1, node2);
            pq.offer(parent);
        }
        root = pq.poll();
        huffmanMap = new HashMap<>();
        createHuffmanMap(root, "", huffmanMap);

        String res = "";
        for(char c: s.toCharArray()) {
            res += huffmanMap.get(c);
        }

        return res;
    }

    public static String decode(String s) {
        Node cur = root;
        String res = "";
        for(char c: s.toCharArray()) {
            if(c == '0') {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

            if(cur.left == null && cur.right == null) {
                res += cur.c;
                cur = root;
            }
        }

        return res;
    }
}
