package google;

import java.util.Arrays;

public class MaximumNumberOfPointsWithCost {
  public static void main(String[] args) {
    System.out.println(maxPoints(new int[][]
            {
                    {1,2,3},
                    {1,5,1},
                    {3,1,1}
            }));
  }

  public static long maxPoints(int[][] points) {
    int rows = points.length;
    int cols = points[0].length;
    long[][] dp = new long[rows][cols];

    long[] left = new long[cols]; // left[i] = max value from left
    long[] right = new long[cols];
    // dp[i][j] = points[i][j] + max(dp[i-1][x] - Math.abs(j - x)) where x from 0 to cols - 1
    for (int j = 0; j < cols; j++) {
      dp[0][j] = points[0][j];
    }
    // curr[i] = max(prev[j] - abs(j - i) for j in range(n)) + points[X+1][i]

    for (int i = 1; i < rows; i++) {
      // get left array
      left[0] = dp[i-1][0];
      for (int j = 1; j < cols; j++) {
        left[j] = Math.max(left[j-1] - 1, dp[i-1][j]);
      }
      // get right array
      right[cols - 1] = dp[i-1][cols - 1];
      for (int j = cols - 2; j >= 0; j--) {
        right[j] = Math.max(right[j+1] - 1, dp[i-1][j]);
      }

      for (int j = 0; j < cols; j++) {
        dp[i][j] = Math.max(left[j], right[j]) + points[i][j];
      }
    }

    long ret = 0;
    for (int j = 0; j < cols; j++) {
      ret = Math.max(ret, dp[rows - 1][j]);
    }

    System.out.println(Arrays.deepToString(dp).replace("],", "\n"));

    return ret;
  }
}
