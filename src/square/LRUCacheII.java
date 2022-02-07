package square;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheII {
  public static void main(String[] args) {

  }
  class Node { // using default constructor
    int key;
    int val;
    Node prev;
    Node next;
  }

  int capacity;
  int size;
  Node head;
  Node tail;
  Map<Integer, Node> cache = new HashMap<>();

  public LRUCacheII(int capacity) {
    this.capacity = capacity;
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
      Node newNode = new Node();
      newNode.key = key;
      newNode.val = val;

      // add node to list
      addNode(newNode);
      // add node to cache
      cache.put(key, newNode);

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
    // always add node after the head
    Node next = head.next;

    node.prev = head;
    node.next = next;

    head.next = node;
    next.prev = node;
  }

  private void removeNode(Node node) {
    Node prev = node.prev;
    Node next = node.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(Node node) {
    removeNode(node);
    addNode(node);
  }


}
