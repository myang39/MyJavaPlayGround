package misc;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {

  public static void main(String[] args) {
//    String s = "()())()";
    String s = ")(";
    System.out.println(removeInvalidParentheses(s));
  }

  static List<String> ans;
  static int maxLen;

  public static List<String> removeInvalidParentheses(String s) {
    int count = 0;
    int remove = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count++;
      } else if (s.charAt(i) == ')') {
        count--;
      }

      if (count < 0) {
        remove++;
        count = 0;
      }
    }

    remove += count;

    System.out.println("remove " + remove);
    ans = new ArrayList<>();
    maxLen = s.length() - remove;
    StringBuilder sb = new StringBuilder();
    dfs(s, 0, sb, 0);
    System.out.println("ans.size(): " + ans.size());
    return ans;
  }

  private static void dfs(String s, int i, StringBuilder sb, int count) {
//    System.out.println(i);
    if (count < 0) {
      return;
    }
    if (sb.length() > maxLen) {
      return;
    }
    if (i == s.length()) {
      if (sb.length() == maxLen && count == 0) {

        ans.add(sb.toString());
      }
      return;
    }

    if (s.charAt(i) != '(' && s.charAt(i) != ')') {
      dfs(s, i + 1, sb.append(s.charAt(i)), count);
      sb.deleteCharAt(sb.length() - 1);
    } else {
      dfs(s, i + 1, sb.append(s.charAt(i)), count + (s.charAt(i) == '('? 1 : -1) );
      sb.deleteCharAt(sb.length() - 1);
      if (sb.length() == 0 || sb.charAt(sb.length() - 1) != s.charAt(i)) {
        dfs(s, i + 1, sb, count);
      }
    }
  }
}
