package misc;

import java.util.HashSet;
import java.util.Set;

public class MaxGoodPeopleBasedOnStatements {
  public static void main(String[] args) {

  }

  int max = 0;
  int m = 0;

  public int maximumGood(int[][] statements) {
    m = statements.length;
    dfs(statements, 0, new HashSet<Integer>(), new HashSet<Integer>());
    return max;
  }

  private void dfs(int[][] statements, int i, Set<Integer> good, Set<Integer> bad) {
    if (i == m) {
      max = Math.max(max, m - bad.size());
      return;
    }

    if (good.contains(i)) {
      for (int j = 0; j < m; j++) {
        if (statements[i][j] == 0 && good.contains(j)) {
          return;
        } else if (statements[i][j] == 1 && bad.contains(j)) {
          return;
        }
      }

      for (int j = 0; j < m; j++) {
        if (statements[i][j] == 0 && !bad.contains(j)) {
          bad.add(j);
        } else if (statements[i][j] == 1 && !good.contains(j)) {
          good.add(j);
        }
      }
      dfs(statements, i + 1, good, bad);
      for (int j = 0; j < m; j++) {
        if (statements[i][j] == 0) {
          bad.remove(j);
        } else if (statements[i][j] == 1) {
          good.remove(j);
        }
      }

      return;
    }
  }
}
