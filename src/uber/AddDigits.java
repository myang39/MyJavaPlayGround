package uber;

public class AddDigits {
  public static void main(String[] args) {
    System.out.println(addDigits(11, 9));
  }

  static String addDigits(int a, int b) {
    StringBuilder sb = new StringBuilder();
    while (a > 0 || b > 0) {
      int ra = a % 10;
      int rb = b % 10;
      int rab = ra + rb;
      while (rab > 0) {
        sb.append(rab % 10);
        rab /= 10;
      }
      a /= 10;
      b /= 10;
    }

    return sb.reverse().toString();
  }
}
