package amplitude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncodingAndDecoding {
  public static void main(String[] args) {
    String[] input = {"apple", "orange", "melon", "apple"};
    System.out.println(encode(input));
  }

  public static String encode(String[] input) {
    Map<String, Integer> map = new HashMap<>();
    for (String s : input) {
      map.putIfAbsent(s, 0);
      map.put(s, map.get(s) + 1);
    }

    List<String> key = new ArrayList<>();
    List<Integer> count = new ArrayList<>();
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      key.add(e.getKey());
      count.add(e.getValue());
    }

    StringBuilder sb = new StringBuilder();
    for (String s : key) {
      sb.append(s+ " ,");
    }

    sb.replace(sb.length() - 2, sb.length(), ": ");

    for (Integer i : count) {
      sb.append(i + ", ");
    }

    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }
}
