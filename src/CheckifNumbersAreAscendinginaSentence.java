public class CheckifNumbersAreAscendinginaSentence {
    // Character.isDigit doesn't work!!!???
    public static void main(String[] args) {
        System.out.println(areNumbersAscending("4 5 11 26"));
    }
    public static boolean areNumbersAscending(String s) {
        int pre = -1;
        char[] array = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            char cur = array[i];

            if (cur >= '0' && cur <= '9') {
//                System.out.println(cur);
                int sum = 0;
                while (cur >= '0' && cur <= '9' && i < s.length() - 1) {
                    sum = sum * 10 + (cur - '0');
                    i++;
                    cur = array[i];
                }
                if (cur >= '0' && cur <= '9') {
                    sum = sum * 10 + (cur - '0');
                }
//                System.out.println("sum:" + sum);
                if (pre >= sum) {
                    return false;
                } else {
                    pre = sum;
                }
            }
        }
        return true;
    }
}
