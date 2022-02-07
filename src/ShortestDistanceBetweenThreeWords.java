import java.util.*;

public class ShortestDistanceBetweenThreeWords {
  public static void main(String[] args) {
    Integer a = -128;
    Integer b = -128;
    if (a == b) {
      System.out.println("xxx");
    } else {
      System.out.println("x");
    }
  }


  // 好像也不是很会写，我先看几个 Leetcode sliding window再说
  public static int shortestDistanceBetweenWords(String[] words, List<String> targetWords) {
    if (targetWords == null || targetWords.size() == 0) {
      return 0;
    }
    Map<String, Integer> map = new HashMap<>();
    Set<String> target = new HashSet<>();
    target.addAll(targetWords);
    int count = target.size();
    int ret = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    // count == 0, increase left until count != 0
    // count != 0, increase right, until count == 0
    while (right < words.length) {
      while (count == 0) { // we still have all the target words in the sliding window

      }
    }

    return ret;
  }
}
