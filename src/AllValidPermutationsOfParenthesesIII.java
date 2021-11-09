import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllValidPermutationsOfParenthesesIII {

    public static void main(String[] args) {
        List<String> list = validParenthesesIII(3,1,0);
        System.out.println(Arrays.deepToString(list.toArray()));
    }

    public static List<String> validParenthesesIII(int l, int m, int n) {
        // Write your solution here
        List<String> ret = new ArrayList<>();
        helper(l, l, m, m, n, n, 0, new char[(l + m + n) * 2], ret);
        return ret;
    }

    private static void helper(int ll, int lr,
                        int ml, int mr, int nl, int nr, int index, char[] cur, List<String> ret) {
        if (ll == 0 && lr == 0 && ml == 0 && mr == 0 && ml == 0 && nr == 0) {
            ret.add(new String(cur));
            return;
        }

        // higher order
        if (ml == mr) {
            if (nl == nr && nl > 0) {
                cur[index] = '{';
                helper(ll, lr, ml, mr, nl - 1, nr, index + 1, cur, ret);
            }
            if (nl == nr - 1) {
                cur[index] = '}';
                helper(ll, lr, ml, mr, nl, nr - 1, index + 1, cur, ret);
            }
        }

        // mid order
        if (ml == mr && ml > 0) {
            cur[index] = '<';
            helper(ll, lr, ml - 1, mr, nl, nr, index + 1, cur, ret);
        }
        if (ml == mr - 1) {
            cur[index] = '>';
            helper(ll, lr, ml, mr - 1, nl, nr, index + 1, cur, ret);
        }
        // lower order
        if (ll > 0) {
            cur[index] = '(';
            cur[index + 1] = ')';
            helper(ll - 1, lr - 1, ml, mr, nl, nr, index + 2, cur, ret);
        }
    }
}
