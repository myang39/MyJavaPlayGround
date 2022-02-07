package robinhood;

import java.util.Arrays;
import java.util.List;

public class SuffixPairs {


  public static void main(String[] args) {
    String[] strings = {"cba", "a", "a","b", "ba", "ca"};
    System.out.println(suffixPairs(strings));
  }

  static class TriNode {
    int count;
    TriNode[] children = new TriNode[26];
    boolean end;
  }

  public static int suffixPairs(String[] strings) {
    int count = 0;
    Arrays.sort(strings, (a, b) -> a.length() - b.length());
    TriNode root = new TriNode();

    for (String s : strings) {
      count += helper(root, s);
      System.out.println("string = " + s);
      System.out.println("count = " + count);
    }

    return count;
  }

  private static int helper(TriNode root, String input) {
    int count = 0;
    TriNode current = root;
    for (int i = input.length() - 1 ; i >= 0; i--) {
      int index = input.charAt(i) - 'a';
      if (current.children[index] == null) {
        current.children[index] = new TriNode();
      }
      current = current.children[index];
      if (current.end) {
        count += current.count;
      }
    }
    current.end = true;
    current.count++;

    return count;
  }


}
