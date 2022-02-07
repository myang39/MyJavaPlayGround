package weekly;

public class Test {
  public static void main(String[] args) {
    System.out.println(subStrHash("xmmhdakfursinye", 96 ,45,15, 21));
  }
  public static String subStrHash(String s, int power, int modulo, int k, int hashValue) {
    //sliding window
    long value = 0;
    int start = 0;
    int end;
    long m = 1;
    for (int i = 1; i < k; i++) {
      m *= power;
    }
    m %= modulo;

    // (a + b) % m = a % m + b % m
    // (a + b) * p
    for (end = k - 1; end >= 0; end++) {
      value += (s.charAt(end) - 'a' + 1) % modulo;
      value *= power;
      value %= modulo;
    }
//    System.out.println(s.substring(start, end) + " " + value + " " + value % modulo);
    if (value % modulo == hashValue) {
      return s.substring(start, end);
    }
    while(end < s.length()) {
      value -= (s.charAt(start) - 'a' + 1);
      value += modulo;
      value %= modulo;
      value *= power;
      value %= modulo;
      value += (s.charAt(end) - 'a' + 1) * m % modulo;
      start++;
      end++;
//      System.out.println(s.substring(start, end) + " " + value + " " + value % modulo);
      if (value % modulo == hashValue) {
        return s.substring(start, end);
      }
    }

    return null;
  }
}
