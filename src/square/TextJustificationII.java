package square;

import java.util.ArrayList;
import java.util.List;

public class TextJustificationII {
  public static void main(String[] args) {
    String[]  words = {"This", "is", "an", "example", "of", "text",
            "justification."};
    int maxWidth = 16;
    StringBuilder ruler = new StringBuilder();
    for (int i = 0; i < maxWidth; i++) {
      ruler.append('a');
    }
    System.out.println(ruler.toString());
    System.out.println(fullJustify(words, maxWidth).toString()
            .replace(", ", "\n")
            .replace("[", "")
            .replace("]", ""));
  }
  public static List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ans = new ArrayList<>();
    int right = 0;
    int n = words.length;

    while (true) {
      int left = right; // get the index of first word in words for this row
      int sumLen = 0; // len for all words for this row
      // calculate how many words we can put for this row
      while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
        sumLen += words[right++].length();
      }

      // if current row is the last row, left-justified and just one space
      // between each words
      if (right == n) {
        StringBuilder sb = join(words, left, right, " ");
        sb.append(blank(maxWidth - sb.length()));
        ans.add(sb.toString());
        return ans;
      }

      int numWords = right - left;
      int numSpaces = maxWidth - sumLen;

      // there is only one word in this row
      if (numWords == 1) {
        StringBuilder sb = new StringBuilder(words[left]);
        sb.append(blank(numSpaces));
        ans.add(sb.toString());
        continue;
      }

      // there are more than one word in this row
      int avgSpaces = numSpaces / (numWords - 1);
      int extraSpaces = numSpaces % (numWords - 1);
      StringBuilder sb = new StringBuilder();
      sb.append(join(words, left, left + extraSpaces + 1,
              blank(avgSpaces + 1)));
      sb.append(blank(avgSpaces));
      sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces)));
      ans.add(sb.toString());
    }
  }

  private static String blank(int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }

  private static StringBuilder join(String[] words, int left, int right,
                               String sep) {
    StringBuilder sb = new StringBuilder(words[left]);
    for (int i = left + 1; i < right; i++) {
      sb.append(sep);
      sb.append(words[i]);
    }
    return sb;
  }
}
