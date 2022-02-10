package square;

import java.util.Arrays;

public class EditDistance {
  public static void main(String[] args) {
    String word1 = "a";
    String word2 = "b";
    System.out.println(minDistance(word1, word2));
  }

  public static int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }

    int[][] dp = new int[m+1][n+1];

    //initialization
    for (int i = 0; i <= m; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
      dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (word1.charAt(i-1) == word2.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1];
          continue;
        }
        int insert = dp[i][j-1] + 1;
        int delete = dp[i-1][j] + 1;
        int replace = dp[i-1][j-1] + 1;

        dp[i][j] = Math.min(insert, Math.min(delete, replace));
      }
    }

    System.out.println(Arrays.deepToString(dp).
            replace("],", "\n"));
    return dp[m][n];
  }
}
