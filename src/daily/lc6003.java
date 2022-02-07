package daily;

import java.util.Arrays;

public class lc6003 {
  public static void main(String[] args) {
    String s = "1100101";
    System.out.println(minimumTime(s));
  }
  public static int minimumTime(String s) {
    char[] array = s.toCharArray();
    int len = s.length();
    int[] left = new int[len]; //
    int[] right = new int[len]; //

    left[0] += (array[0] - '0');
    for (int i = 1; i < len; i++) {
      if (array[i] == '1') {
        left[i] = Math.min(left[i - 1] + 2, i + 1);
      } else {
        left[i] = left[i - 1];
      }
    }

    right[len - 1] += array[len - 1] - '0';
    for (int i = len - 2; i >= 0; i--) {
      if (array[i] == '1') {
        right[i] = Math.min(right[i + 1] + 2, len - i);
      } else {
        right[i] = right[i+1];
      }
    }

     System.out.println(Arrays.toString(left));
     System.out.println(Arrays.toString(right));

    int min = right[0];
    for (int i = 0; i < len - 1; i++) {
      min = Math.min(min, left[i] + right[i+1]);
    }
    min = Math.min(min, left[len - 1]);

    return min;

  }
}
