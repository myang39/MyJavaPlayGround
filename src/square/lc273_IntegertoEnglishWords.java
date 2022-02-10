package square;

public class lc273_IntegertoEnglishWords {
  public static void main(String[] args) {
    System.out.println(numberToWords(1_234_567));
  }

  private static final String[] LESS_THAN_20 = {
          "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
          "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
          "Eighteen", "Nineteen"
  };

  private static final String[] TENS = {
          "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
  };

  public static final String[] Thousands = {
          "", "Thousand", "Million", "Billion"
  };

  public static String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }

    StringBuilder sb = new StringBuilder();
    numberToWords(num, sb);
    return sb.toString().trim();
  }

  private static void numberToWords(int num, StringBuilder sb) {
    int i = 0;
    while (num > 0) {
      if (num % 1000 > 0) {
        sb.insert(0, Thousands[i]); // first insert unit
        sb.insert(0, " " + helper(num % 1000));
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
      return TENS[num/10] + " " + helper(num % 10);
    } else { // num >= 100, num < 1000
      return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
  }
}
