package square;


import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {
  int capacity;
  public LRUCache(int c) {
    super(c, 0.75F, true);
    capacity = c;
  }
  public int get(int key) {
    return super.getOrDefault(key, -1); // super cannot be referenced from a
    // static context.
  }

  public void put(int key, int val) {
    super.put(key, val);
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
    return size() > capacity;
  }
}

