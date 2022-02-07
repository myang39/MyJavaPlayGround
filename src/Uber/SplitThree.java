package Uber;

public class SplitThree {
  public static void main(String[] args) {
    System.out.println(solution("xzxzxzxzxz"));
  }

  static int solution(String s) {
    int n = s.length();
    int count = 0;
    // // 0 ... i's left
    // // i ... j's left
    // // j ... n - 1
    for (int i = 1; i <= n - 2; i++) {
      for (int j = i + 1; j <= n - 1; j++) {
        // a + b vs b + c
        String ab = s.substring(0, j);
        String bc = s.substring(i, n);
//        System.out.println("ab: " + ab + " bc: " + bc + " ca: " + ca);
        if (ab.equals(bc)) {
          continue;
        }
        String ca = s.substring(j, n) + s.substring(0, i);
        if (ab.equals(ca) || bc.equals(ca)) {
          continue;
        }
        count++;
//        System.out.println("count = " + count);
      }
    }
    return count;
  }
}
