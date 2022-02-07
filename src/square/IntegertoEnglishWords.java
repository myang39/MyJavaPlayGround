package square;

public class IntegertoEnglishWords {
  public static void main(String[] args) {
    double num = 1_000.0_123_456;
    System.out.println(numberToWords(num));
  }

  private static final String[] LESS_THAN_20 = {
          "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
          "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
          "Eighteen", "Nineteen"
  };

  private static final String[] TENS = {
          "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
  };

  private static final String[] Thousands = {
          "", "Thousand", "Million", "Billion"
  };

  private static final String[] Decimals = {
          "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
  };

  public static String numberToWords(double num) {
    StringBuilder sb = new StringBuilder();
    String s = String.valueOf(num);
    String[] ss = s.split("\\.");
//    System.out.println("int: " + ss[0]);
//    System.out.println("decimal points: " + ss[1]);
    numberToWords(Integer.parseInt(ss[0]), sb);
    if (ss[1].length() > 0) {
      String decimals = ss[1];
      sb.append(" POINT");
      for(int i = 0; i < decimals.length(); i++) {
        int point = decimals.charAt(i) - '0';
//        System.out.println(point);
        sb.append(" "  + Decimals[point]);
      }
    }
    return "|" + sb.toString().trim() + "|";
  }

  private static void numberToWords(int num, StringBuilder sb) {
    int i = 0;
    while (num > 0) {
      if (num % 1000 > 0) {
        sb.insert(0, Thousands[i]); // first insert unit
        sb.insert(0, " " + helper(num % 1000)); // then insert number
      }
      num /= 1000;
      i++;
    }
  }

  private static String helper(int num) {
    if (num == 0) {
      return "";
    } else if (num < 20) {
      return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
      return TENS[num / 10] + " " + helper(num % 10);
    } else { // num >= 100, num < 1000
      return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
  }
}
