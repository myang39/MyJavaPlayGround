package square;

import java.util.ArrayList;
import java.util.List;

public class histogramDrawer {

    public static void main(String[] args) {
        int[] array = {1,3,5,0,6,3,4,0,0,3};
        createHistogram(array);
    }

    /***
     * Given a array of integer, drawing corresponding histogram
     */
    public static void createHistogram(int[] array) {
        List<String> list = new ArrayList<>();
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            maxHeight = Math.max(maxHeight, array[i]);
        }
//        System.out.println(maxHeight + " " + array.length);
        char[][] hist = new char[maxHeight + 1][array.length * 2 + 1];

        // Add the top
        for (int i = 0; i < array.length; i++) {
            int height = array[i];
            hist[maxHeight - height][i*2+1] = '-';
        }

        // Add the vertical walls
        for (int i = 0; i <= array.length; i++) {
            int prevHeight = 0;
            int currHeight = 0;
            if (i != array.length) {
                currHeight = array[i];
            }
            if (i != 0) {
                prevHeight = array[i-1];
            }

            int diff = currHeight - prevHeight;
            if (diff == 0) {
                continue;
            }
            if (diff > 0) {
                for (int d = 0; d <= diff; d++) {
                    hist[maxHeight - currHeight + d][i*2] = '|';
                }
            }
            if (diff < 0) {
                for (int d = diff; d <= 0; d++) {
                    hist[maxHeight - currHeight + d][i*2] = '|';
                }
            }
        }

//        System.out.println(Arrays.deepToString(hist)
//                .replace("],", "\n"));
        printHistogram(hist);
    }

    private static void printHistogram(char[][] hist) {
        for (int i = 0; i < hist.length - 1; i++) {
            System.out.print(i);
            for (int j = 0; j < hist[0].length; j++) {
                if (hist[i][j] == '\u0000') {
                    System.out.print(new String(" "));
                } else {
                    System.out.print(hist[i][j]);
                }
            }
            System.out.println();
        }
    }
}
