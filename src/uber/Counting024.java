package uber;

public class Counting024 {
  // You are given an integer n.
  // Your task is to calculate how many times the digits 0, 2 and 4 appear
  // in all the non-negative integers up to n (0, 1, ...., n).
  public static void main(String[] args) {
//    System.out.println(1 / 10);
    System.out.println(count024(22));
  }

  static int count024(int n) {
    int count = 1;
    for (int i = 0; i <= n; i++) {
      int cur = i;
      while (cur > 0) {
        System.out.println("i " + i);
        int r = cur % 10;
        count += r == 0 || r == 2 || r == 4 ? 1 : 0;
        cur = cur / 10;
      }
    }
    return count;
  }
}
