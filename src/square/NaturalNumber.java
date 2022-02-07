package square;

import java.util.Objects;

public class NaturalNumber {
  final String s;

  @Override
  public String toString() {
    return s;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NaturalNumber that = (NaturalNumber) o;
    return Objects.equals(s, that.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(s);
  }

  NaturalNumber(int n) {
    StringBuilder sb = new StringBuilder();
    sb.append('0');
    for (int i = 0; i < n; i++) {
      sb = sb.insert(0, "s(").append(')');
    }
    s = sb.toString();
  }

  NaturalNumber(NaturalNumber nn, int n) {
    StringBuilder sb = new StringBuilder();
    sb.append(nn.s);
    for (int i = 0; i < n; i++) {
      sb = sb.insert(0, "s(").append(')');
    }
    s = sb.toString();
  }

  NaturalNumber(String s) {
    this.s = s;
  }

  NaturalNumber add(NaturalNumber nn) {
    int count = 0;
    String ss = nn.s;
    for (int i = 0; i < ss.length(); i++) {
      count += ss.charAt(i) == 's'? 1 : 0;
    }
    return new NaturalNumber(this, count);
  }

  NaturalNumber minus(NaturalNumber nn) {
    // corner case
    if (s.length() < nn.s.length()) {
      return null;
    }
    return new NaturalNumber(s.replace(nn.s, "0"));
  }

  NaturalNumber mod(NaturalNumber nn) {
    // 5 % 7 = 5
    // 7 % 2 = 1
    String ss = nn.s;
    String temp = s;
    while (temp.length() >= ss.length()) {
      temp = temp.replace(ss, "0");
    }
    return new NaturalNumber(temp);
  }

  public static void main(String[] args) {
    NaturalNumber n0 = new NaturalNumber(0);
    NaturalNumber n1 = new NaturalNumber(1);
    NaturalNumber n2 = new NaturalNumber(2);
    NaturalNumber n3 = new NaturalNumber(3);
    NaturalNumber n4 = new NaturalNumber(4);

    System.out.println(n0);
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n3);
    System.out.println(n4);

    System.out.println(n1.add(n2));

    System.out.println(n4.minus(n1));

    System.out.println(n4.mod(n2));

  }
}
