package misc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SumOfSubarrayMinimums {
  public static void main(String[] args) {
    int[] array = {3,1,2,4};
    System.out.println(sumSubarrayMins(array));
    System.out.println(sumSubarrayMinsII(array));
  }


  public static int sumSubarrayMins(int[] arr) {
    int n = arr.length;
    int[] left = new int[n];
    int[] right = new int[n];
    int[] f = new int[n];

    // left
    for (int i = 0; i < n; i++) {
      int j = i - 1;
      while (j >= 0 && arr[j] >= arr[i]) {
        j--;
      }
      left[i] = i - (j + 1);
    }

    // right
    for (int i = 0; i < n; i++) {
      int j = i + 1;
      while (j < n && arr[j] >= arr[i]) {
        j++;
      }
      right[i] = (j - 1) - i;
    }

    for (int i = 0; i < n; i++) {
      f[i] = (left[i] + 1) * (right[i] + 1);
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      System.out.println("arr[i]: " + arr[i] + " f[i]: " + f[i]);
      sum += arr[i] * f[i];
    }

    return sum;
  }

  public static int sumSubarrayMinsII(int[] arr) {
    int n = arr.length;
    Deque<Integer> stack = new ArrayDeque<>();
    int[] prevSmaller = new int[n]; //
    int[] nextSmaller = new int[n]; // monotonic increasing stack
    Arrays.fill(prevSmaller, -1);
    Arrays.fill(nextSmaller, n);

    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peekFirst()] > arr[i]) {
        nextSmaller[stack.pollFirst()] = i;
      }
      stack.offerFirst(i);
    }

    while (!stack.isEmpty()) {
      stack.pollFirst();
    }

    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[stack.peekFirst()] >= arr[i]) {
        prevSmaller[stack.pollFirst()] = i;
      }
      stack.offerFirst(i);
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      System.out.println("arr[i] " + arr[i] + " f[i] " + (nextSmaller[i] - i) * (i - prevSmaller[i]));
      System.out.println("nextSmaller[i]: " + nextSmaller[i] + " prevSmaller[i]: " + prevSmaller[i]);
      res += arr[i] * (nextSmaller[i] - i) * (i - prevSmaller[i]);
    }

    return res;
  }
}
