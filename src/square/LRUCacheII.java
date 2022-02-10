package square;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheII {
  public static void main(String[] args) {
    LRUCacheII l = new LRUCacheII(3);
    l.put(1, 1);
    l.put(2, 2);
    l.put(3, 3);
    System.out.println(l.get(2));
    l.put(4, 4);
    System.out.println(l.get(1));
  }

  static class Node {
    int key;
    int val;
    Node prev;
    Node next;
  }

  int capacity;
  int size;
  Map<Integer, Node> cache; // key to Node
  Node head;
  Node tail;

  public LRUCacheII(int capacity) {
    this.capacity = capacity;
    cache = new HashMap<>();
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    Node node = cache.get(key);
    if (node == null) {
      return -1;
    }
    moveToHead(node);
    return node.val;
  }

  public void put(int key, int val) {
    Node node = cache.get(key);
    if (node == null) {
      node = new Node();
      node.key = key;
      node.val = val;
      cache.put(key, node);
      addNode(node);
      size++;
      if (size > capacity) {
        Node lastNode = tail.prev;
        removeNode(lastNode);
        cache.remove(lastNode.key);
        size--;
      }
    } else {
      node.val = val;
      moveToHead(node);
    }
  }

  private void addNode(Node node) {
    // always add node after head
    Node next = head.next;
    head.next = node;
    node.prev = head;
    node.next = next;
    next.prev = node;
  }

  private void removeNode(Node node) {
    Node prev = node.prev;
    Node next = node.next;

    prev.next = next;
    next.prev = prev;
  };

  private void moveToHead(Node node) {
    removeNode(node);
    addNode(node);
  }
}