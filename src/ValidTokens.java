import java.util.Arrays;

public class ValidTokens {
    public static void main(String[] args) {
        ValidTokens v = new ValidTokens();
        v.countValidWords(" o6                5");
    }
    public int countValidWords(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return 0;
        }
        String ss = sentence.trim();
        String[] array = ss.split("\\s+");
        System.out.println(array.length);

        sprintStringArray(array);
        int sum = 0;
        for (String s : array) {
            boolean b = true;
            int numH = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigit(c)) {
                    b = false;
                    break;
                }
                if (c == '-') {
                    numH++;
                    if (numH > 1 || i == 0 || i == s.length() - 1
                            || !isLowercaseLetter(s.charAt(i - 1)) || !isLowercaseLetter(s.charAt(i + 1))
                    ) {
                        b = false;
                        break;
                    }
                }
                if (isPunc(c) && i != s.length() - 1) {
                    b = false;
                    break;
                }
            }
            sum += b ? 1 : 0;
        }

        return sum;
    }

    private boolean isLowercaseLetter(char r) {
        return r >= 'a' && r <= 'z';
    }

    private boolean isDigit(char r) {
        return (r - '0') >= 0 && (r - '0') <= 9;
    }

    private boolean isPunc(char r) {
        return r == '!' || r == ',' || r == '.';
    }

    private void sprintStringArray(String[] array) {
        for (String s : array) {
            System.out.println("s length = " + s.length() + ":" + s);
        }
    }
}
