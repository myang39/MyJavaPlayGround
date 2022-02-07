package square;

import util.Tree;

import java.util.*;

public class HuffmanCoding {
  public static void main(String[] args) {
    String s = "Hello World!";
    s = "Designing a system that supports millions of users is challenging, " +
            "and it is a journey that requires continuous refinement and endless " +
            "improvement. In this chapter, we build a system that supports a single " +
            "user and gradually scale it up to serve millions of users. After " +
            "reading this chapter, you will master a handful of techniques that " +
            "will help you to crack the system design interview questions.";
    String encoded = encode(s);
    System.out.println(encoded);
//    levelOrderPrint(root);
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

  public static void levelOrderPrint(Node root) {
    if (root == null) {
      return;
    }

    Queue<Node> q = new LinkedList<>();
    q.offer(root);
    int level = 0;
    List<List<Node>> lists = new ArrayList<>();
    while (q.size() > 0) {
      int size = q.size();
      List<Node> list;
      if (lists.size() <= level) {
        list = new ArrayList<>();
        lists.add(list);
      }
      list = lists.get(level);
      for (int i = 0; i < size; i++) {
        Node cur = q.poll();
        list.add(cur);
        if (cur != null) {
          q.offer(cur.left);
          q.offer(cur.right);
        }
      }
      level++;
    }

    System.out.println("print huffman tree:");
    System.out.println("levels: " + lists.size());
    for (int i = 0; i < lists.size(); i++) {
      List<Node> list = lists.get(i);
      for (int j = 0; j < list.size(); j++) {
        Node n = list.get(j);
        if (n != null) {
          System.out.print(list.get(j).c + " " + list.get(j).freq + ", ");
        }
      }
      System.out.println();
    }
  }

  static Node root;
  static Map<Character, String> huffmanMap;

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

    while (pq.size() > 1) {
      Node l = pq.poll();
      Node r = pq.poll();
      Node parent = new Node(' ', l.freq + r.freq, l, r);
      pq.offer(parent);
    }
    root = pq.poll();
//    System.out.print("root " + root.c + " " + root.freq);
  }

  private static void createHuffmanMap(Node root, StringBuilder sb) {
    // huffmanMap = new HashMap<>(); !!! this would ditch the current map and create
    //  a new map everytime.
    if (root.left == null && root.right == null) {
//      System.out.println("root.c + code: " + root.c + " : " + sb.toString());

      huffmanMap.put(root.c, sb.toString());
//      System.out.println("print huffmanMap:");
//      util.Maps.printCharacterString(huffmanMap);
      return;
    }

    createHuffmanMap(root.left, sb.append('0'));
    sb.deleteCharAt(sb.length() - 1);

    createHuffmanMap(root.right, sb.append('1'));
    sb.deleteCharAt(sb.length() - 1);
  }

  public static String encode(String s) {
    createHuffmanTree(s);
    huffmanMap = new HashMap<>();
    createHuffmanMap(root, new StringBuilder());
//    System.out.println("huffman map:");
//    util.Maps.printCharacterString(huffmanMap);
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
