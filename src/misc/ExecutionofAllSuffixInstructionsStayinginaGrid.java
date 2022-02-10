package misc;

import java.util.Arrays;

public class ExecutionofAllSuffixInstructionsStayinginaGrid {
  public static void main(String[] args) {
    System.out.println(
            Arrays.toString(executeInstructions(3, new int[] {0, 1}, "RRDDLU"))
    );
  }
  public static int[] executeInstructions(int n, int[] startPos, String s) {
    int[] res = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      int x = startPos[0];
      int y = startPos[1];
      int step = 0;
      for (int j = i; j <= s.length(); j++) {
//        System.out.println("i " + i + " j " + j + " " + s.charAt(j));
        if (j == s.length()) {
          res[i] = step;
          break;
        }
        if (s.charAt(j) == 'L') {
          if (y > 0) {
            y--;
            step++;
          } else {
            res[i] = step;
            break;
          }
        } else if (s.charAt(j) == 'R') {
          if (y < n - 1) {
            y++;
            step++;
          } else {
            res[i] = step;
            break;
          }
        } else if (s.charAt(j) == 'U') {
          if (x > 0) {
            x--;
            step++;
          } else {
            res[i] = step;
            break;
          }
        } else {
          if (x < n - 1) {
            x++;
            step++;
          } else {
            res[i] = step;
            break;
          }
        }
      }
    }
    return res;
  }
}
