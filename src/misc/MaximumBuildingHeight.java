package misc;

import java.util.Arrays;

public class MaximumBuildingHeight {
    public static void main(String[] args) {

    }

    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int m = restrictions.length + 1;
        int[] h = new int[m];
        int[] pos = new int[m];
        int[] lim = new int[m];

        pos[0] = 1;
        lim[0] = 0;
        for (int i = 1; i < m; i++) {
            pos[i] = restrictions[i][0];
            lim[i] = restrictions[i][1];
        }
        h[0] = 0;
        for (int i = 1; i <= m; i ++) {
            h[i] = Math.min(lim[i], h[i - 1] + pos[i] - pos[i - 1]);
        }
        for (int i = m - 2; i >= 1; i--) {
            h[i] = Math.min(h[i], h[i + 1] + pos[i + 1] - pos[i]);
        }


        // h[i - 1] + x = h[i] + y;
        // pos[i - 1] + x = pos[i] - y;
        // x = (h[i] + pos[i] - h[i - 1] - pos[i - 1]) / 2;
        // peak = h[i - 1] + x = h[i - 1] + (h[i] + pos[i] - h[i - 1] - pos[i - 1]) / 2;
        //                     = (h[i] + pos[i] + h[i - 1] - pos[i - 1]) / 2;
        int ret = 0;
        for (int i = 1; i < m; i++) {
            int peak = (h[i] + pos[i] + h[i - 1] - pos[i - 1]) / 2;
            ret = Math.max(ret, peak);
        }

        ret = Math.max(ret, h[m] + n - pos[m]);
        return ret;
    }
}
