import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeAddition {
    public static void main(String[] args) {
        List<String> upRight = new ArrayList<>(Arrays.asList("1 4", "2 3", "4 1"));
        System.out.println(countMax(upRight));
    }

    public static long countMax(List<String> upRight) {
        int[][] ops = new int[upRight.size()][2];
        for (int i = 0; i < upRight.size(); i++)  {
            String[] nums = upRight.get(i).split(" ");
            ops[i][0] = Integer.parseInt(nums[0]);
            ops[i][1] = Integer.parseInt(nums[1]);
        }
        int m = Integer.MAX_VALUE;
        int n = Integer.MAX_VALUE;
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
