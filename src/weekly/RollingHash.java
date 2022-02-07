package weekly;

public class RollingHash {
  public static void main(String[] args) {

  }

  public String subStrHash(String s, int pow, int modulo, int k , int hashValue) {
    long cur = 0, pk = 1; // pk = p to the power of k
    int res = 0, n = s.length();
    for (int i = n - 1; i >= 0; i--) {
      cur = (cur * pow + s.charAt(i) - 'a' + 1) % modulo;
      if (i >= n - k) {
        pk *= pow;
      } else {
        cur = (cur - (s.charAt(i + k) - 'a' + 1) * pk % modulo + modulo) % modulo;
        if (cur == hashValue) {
          res = i;
        }
      }
    }
    return s.substring(res, res + k);
  }
}
