package misc;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class FindOriginalArrayFromDoubledArray {
  public static void main(String[] args) {
    int[] array = {1,3,4,2,6,8};
    System.out.println(Arrays.toString(findOriginalArray(array)));
  }

  public static int[] findOriginalArray(int[] changed) {
    int n = changed.length;
    if (n % 2 == 1) {
      return new int[0];
    }
    int i = 0;

    int[] ret = new int[n / 2];
    Map<Integer, Integer> count = new TreeMap<>();
    for (int a : changed) {
      count.put(a, count.getOrDefault(a, 0) + 1);
    }
    for (int x : count.keySet()) {
      if (count.get(x) > count.getOrDefault(x * 2, 0)) {
        return new int[0];
      }
      for (int j = 0; j < count.get(x); j++) {
        ret[i++] = x;
        count.put(x * 2, count.get(x * 2) - 1);
      }
    }

    return ret;
  }
}
