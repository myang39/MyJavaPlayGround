package robinhood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlertUsingSameKeyCard {
  public static void main(String[] args) {
    System.out.println(alertNames(
            new String[]{"daniel","daniel","daniel","luis","luis","luis","luis"},
            new String[]{"10:00","10:40","11:00","09:00","11:00","13:00","15:00"}));
  }

  public static List<String> alertNames(String[] keyName, String[] keyTime) {
    int len = keyName.length;
    Map<String, List<Integer>> map = new HashMap<>();
    List<String> ret = new ArrayList<>();

    for (int i = 0; i < len; i++) {
      map.putIfAbsent(keyName[i], new ArrayList<>());
      map.get(keyName[i]).add(getTime(keyTime[i]));
    }

    for (Map.Entry<String, List<Integer>> e : map.entrySet()) {
      List<Integer> list = e.getValue();
      for (int i = 2; i < list.size(); i++) {
        if (list.get(i) - list.get(i - 2) <= 60) {
          ret.add(e.getKey());
          break;
        }
      }
    }

    return ret;
  }

  private static int getTime(String s) {
    String[] ss = s.split(":");
    return 60 * Integer.parseInt(ss[0]) + Integer.parseInt(ss[1]);
  }
}
