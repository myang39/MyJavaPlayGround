package square;

import java.util.*;

/*
题目是：给定一个加入meeting的history entry list[timestamp, "name", "ENTER" or "EXIT"]. 比如{[1, "a", "ENTER"],
[2, "b", "ENTER"],[3, "a", "EXIT"]}， 给定一个timestamp 2.
返回‍‍‍‍‌‌‍‍‍‌‍‌‍‌‌‍‌‍‍在timestamp 2这个时间段有多少人在meeting里。
* */
public class HistoryEntryList {
  public static void main(String[] args) {
    String[][] list = {
            {"3", "a", "EXIT"},
            {"1", "a", "ENTER"},
            {"2", "b", "ENTER"},
    };

    System.out.println(peoplesInTheMeeting(list, 3));
  }

  public static int peoplesInTheMeeting(String [][] list, int targetTime) {
//    Map<String, Integer> map = new HashMap<>();
    // Is the list sorted by timestamp? Can a person enter twice without exit in between?
    // if not, we need to sort the list first
    Arrays.sort(list, (a, b) -> Integer.parseInt(a[0]) - Integer.parseInt(b[0]));
    System.out.println(Arrays.deepToString(list));
    // and the example is valid
    // If it is
    int count = 0;
    for (String[] e : list) {
      int time = Integer.parseInt(e[0]);
      String key = e[1];
      String action = e[2];
      if (time > targetTime) {// clarify: does equal count?
        break;
      }
      if (action.equals("ENTER")) {
        count++;
      } else {
        count--;
      }
    }

    return count;
  }
}
