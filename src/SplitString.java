public class SplitString {
    public static void main(String[] args) {
        System.out.println(splitString("050043"));
    }

    public static boolean splitString(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        return dfs(s, 0, 0);
    }

    private static boolean dfs(String s, int start, long preValue) {
        System.out.println("start " + start);
        if (start >= s.length()) {
            return true;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            if (start == 0 && i == s.length()) {
                return false;
            }
            long value = parseString(s.substring(start, i));
            System.out.println("parse " + value);
            if ((start == 0 || value == preValue - 1) && dfs(s, i, value)) {
                System.out.print(value + " ");
                return true;
            } else if (start != 0 && value > preValue - 1) {
                System.out.println("");
                return false;
            }
        }

        System.out.println("");
        return false;
    }

    private static long parseString(String s) {
        long ret = 0;
        for (int i = 0; i < s.length(); i++) {
            ret = ret * 10 + (s.charAt(i) - '0');
        }
        return ret;
    }
}
