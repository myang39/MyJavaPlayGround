package collections;

import java.util.Map;
import java.util.TreeMap;

public class MyTreeMap {
  public static void main(String[] args) {
    int[] array = {11,23,4,1,5,1,-50};
    Map<Integer, Integer> map = new TreeMap<>(
            (a, b) -> Math.abs(a) - Math.abs(b)
    );
    for (int i = 0; i < array.length; i++) {
      map.put(array[i], map.getOrDefault(array[i], 0) + 1);
    }

    for (int i : map.keySet()) {
      System.out.println(i);
    }
  }
}
