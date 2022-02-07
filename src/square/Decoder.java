package square;


//本帖最后由 godlikeleo 于 2021-12-9 15:36 编辑
//        # A - 0
//        # B - 111
//        # C - 1100
//        # D - 1101
//        # E - 10100
//        # F - 10010
//        # "BAD" => "11101101"
//        # 1. Implement a decoder for the encoding scheme.
//        # func ("11101101") => "BAD"

import java.util.HashMap;
import java.util.Map;

public class Decoder {
  static Map<String, String> map;
  public static void main(String[] args) {
    String[][] pairs = {
            {"0","A"},
            {"111","B"},
            {"1100","C"},
            {"1101","D"},
            {"10100","E"},
            {"10010","F"},
    };
    createMap(pairs);
    System.out.println(decode("11101101"));
  }

  public static void createMap(String[][] pairs) {
    map = new HashMap<>();
    for (String[] pair : pairs) {
      map.put(pair[0], pair[1]);
    }
  }

  public static String decode(String s) {
    StringBuilder sb = new StringBuilder();
    String[] ans = new String[1];
    dfs(s, 0, sb, ans);
    return ans[0];
  }

  private static void dfs(String s, int index, StringBuilder sb, String[] ans) {
    if (index >= s.length()) {
      ans[0] = sb.toString();
      return;
    }

    for (int i = index + 1; i <= s.length() && i <= index + 5; i++) {
      String code = s.substring(index, i);
      if (map.get(code) != null) {
        sb.append(map.get(code));
        dfs(s, index + code.length(), sb, ans);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
}
