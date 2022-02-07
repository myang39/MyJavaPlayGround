package twoSigma;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncodingDecodingII {
  public static void main(String[] args) {
    String s = "Hello World!";
    s = "Designing a system that supports millions of users is challenging," +
            "and it is a journey that requires continuous refinement and endless" +
            "improvement. In this chapter, we build a system that supports a single" +
            "user and gradually scale it up to serve millions of users. After" +
            "reading this chapter, you will master a handful of techniques that" +
            "will help you to crack the system design interview questions.";
    String encoded = encode(s);
    System.out.println("encoded: " + encoded);
    System.out.println("decoded: " + decode(encoded));

    // print huffmanMap
  }

  static class Node {
    char c;
    int freq;
    Node left;
    Node right;

    public Node(char c, int f, Node l, Node r) {
      this.c = c;
      freq = f;
      left = l;
      right = r;
    }
  }

  static Map<Character, String> huffmanMap;
  static Node root;

  private static void createHuffmanTree(String s) {
    huffmanMap = new HashMap<>();
    Map<Character, Integer> freqMap = new HashMap<>();
    for (Character c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }
    PriorityQueue<Node> q = new PriorityQueue<>(
            (a, b) -> a.freq - b.freq
    );
    for (Map.Entry<Character, Integer> e : freqMap.entrySet()) {
      Node n = new Node(e.getKey(), e.getValue(), null, null);
      q.offer(n);
    }

    while (q.size() > 1) { // create huffman tree
      Node n1 = q.poll();
      Node n2 = q.poll();
      Node parent = new Node(' ', n1.freq + n2.freq, n1, n2);
      q.offer(parent);
    }

    root = q.poll();
  }

  private static void createHuffmanMap(Node root, StringBuilder sb) {
    if (root.left == null && root.right == null) {
      huffmanMap.put(root.c, sb.toString());
      return;
    }
    createHuffmanMap(root.left, sb.append('0'));
    sb.deleteCharAt(sb.length() - 1);
    createHuffmanMap(root.right, sb.append('1'));
    sb.deleteCharAt(sb.length() - 1);
  }

  public static String encode(String s) {
    // corner case null? empty string?
    createHuffmanTree(s);
    createHuffmanMap(root, new StringBuilder());
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      sb.append(huffmanMap.get(c));
    }
    return sb.toString();
  }

  public static String decode(String s) {
    StringBuilder sb = new StringBuilder();
    Node cur = root;
    for (char c : s.toCharArray()) {
      if (c == '0') {
        cur = cur.left;
      } else {
        cur = cur.right;
      }
      if (cur.left == null && cur.right == null) {
        sb.append(cur.c);
        cur = root;
      }
    }

    return sb.toString();
  }
}
