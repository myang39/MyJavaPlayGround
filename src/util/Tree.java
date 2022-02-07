package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

  static class Node {
    int key;
    int val;
    Node left;
    Node right;
  }
  public static void main(String[] arg) {

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
      List<Node> list = lists.get(level);
      if (list == null) {
        list = new ArrayList<>();
        lists.add(list);
      }
      for (int i = 0; i < size; i++) {
        Node cur = q.poll();
        list.add(cur);
        if (cur != null) {
          q.offer(cur.left);
          q.offer(cur.right);
        }
      }
    }

    for (int i = 0; i < lists.size(); i++) {
      List<Node> list = lists.get(i);
      for (int j = 0; j < list.size(); j++) {
        System.out.print(list.get(j).key);
      }
    }
  }
}
