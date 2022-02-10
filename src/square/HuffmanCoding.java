package square;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
  public static void main(String[] args) {
    String s = "Hello World!";
    s = "Redis (/ˈrɛdɪs/;[6][7] Remote Dictionary Server)[6] is an in-memory dat" +
            "a structure store, used as a distributed, in-memory key–value " +
            "database, cache and message broker, with optional durability. " +
            "Redis supports different kinds of abstract data structures, " +
            "such as strings, lists, maps, sets, sorted sets, HyperLogLogs, " +
            "bitmaps, streams, and spatial indices. " +
            "The project was developed and maintained by Salvatore Sanfilippo." +
            "[8] From 2015 until 2020, he led a project core team sponsored by " +
            "Redis Labs.[9] Salvatore Sanfilippo left Redis as the maintainer " +
            "in 2020.[10] It is open-source software released under a BSD " +
            "3-clause license.[5] In 2021, not long after the original author " +
            "and main maintainer left, Redis Labs dropped the Labs from its " +
            "name and now redis, the open source DB as well as Redis Labs, " +
            "the commercial company, are referred to as \"redis\".[11]";
    String encoded = encode(s);
    System.out.println(encoded);
    System.out.println(decode(encoded));
  }

  static class Node {
    char c;
    int freq;
    Node left;
    Node right;

    Node(char c, int f, Node l, Node r) {
      this.c = c;
      freq = f;
      left = l;
      right = r;
    }
  }

  static Node root;
  static Map<Character, String> huffmanMap = new HashMap<>();

  private static void createHuffmanTree(String s) {
    Map<Character, Integer> freqMap = new HashMap<>();
    for (char c : s.toCharArray()) {
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }
    PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> a.freq - b.freq
    );

    for (Map.Entry<Character, Integer> e : freqMap.entrySet()) {
      Node n = new Node(e.getKey(), e.getValue(), null, null);
      pq.offer(n);
    }

    while(pq.size() > 1) {
      Node l = pq.poll();
      Node r = pq.poll();
      Node p = new Node(' ', l.freq + r.freq, l, r);
      pq.offer(p);
    }
    root = pq.poll();
  }

  private static void createHuffmanMap(Node root, StringBuilder sb) {
    if (root.left == null && root.right == null) {
      huffmanMap.put(root.c, sb.toString());
      return;
    }
    sb.append('0');
    createHuffmanMap(root.left, sb);
    sb.deleteCharAt(sb.length() - 1);

    sb.append('1');
    createHuffmanMap(root.right, sb);
    sb.deleteCharAt(sb.length() - 1);
  }

  public static String encode(String s) {
    if (s == null || s == "") {
      return s;
    }
    createHuffmanTree(s);
    createHuffmanMap(root, new StringBuilder());
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      sb.append(huffmanMap.get(c));
    }
    return sb.toString();
  }

  public static String decode(String s) {
    if (s == null || s == "") {
      return s;
    }
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
