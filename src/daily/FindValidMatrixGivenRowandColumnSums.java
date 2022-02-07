package daily;

import java.util.Arrays;

public class FindValidMatrixGivenRowandColumnSums {
  public static void main(String[] args) {
    int[] rowSum = {5,7,10};
    int[] colSum = {8,6,8};

    System.out.println(Arrays.deepToString((restoreMatrix(rowSum, colSum)))
            .replace("], ", "]\n")
            .replace("[[", "[")
            .replace("]]", "]"));
  }

  public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
    int m = rowSum.length;
    int n = colSum.length;
    int[][] A = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        A[i][j] = Math.min(rowSum[i], colSum[j]);
        rowSum[i] -= A[i][j];
        colSum[j] -= A[i][j];
      }
    }
    return A;
  }

}
