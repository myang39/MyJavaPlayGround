package google;

import java.util.ArrayList;
import java.util.List;

/**Speed entry for the timer on a microwave.

One day I noticed that I could enter 90 on my microwave keypad to count
down for 1 minute and 30 seconds. Since 90 is only 2 key presses and 1:30
would be 3, that is clearly quicker to enter. But I am so lazy that even
moving my finger to another key has a cost. So 66 seconds is “better”
for me to enter than 60 seconds, because I did not have to move my finger
and could type it faster. 55 is even better, because it is closer.

So, I wanted to devise an algorithm that converts a desired time in
minutes and seconds to the optimal cost key presses, according to
these rules.

The actual timer setting must be within 10% of the desired time.
It costs 1 for each press
It costs 2 to move your finger
Among equal costs, the one closest to the desired time wins.

Full writeup here: https://docs.google.com/document/d/15rkZG5rqHgsyy19OuJxz-xsrYeycn404mWlYnlMwIqM/edit#
*/

public class bestMovesMicrowave {

    static final String[] sample =
            {"11", "60", "1:00", "1:90", "2:00", "10:00", "1:02:03", "1:30", "1:10"};

    private static final int keyPressCost = 10;
    private static final int moveCost = 20;
    private static final int penaltyFor5Percent = 20;

    public static void main(String[] args) {
        bestMovesMicrowave b = new bestMovesMicrowave();
//        testMmssToSeconds();
        b.testScore();
    }

    int mmssToSeconds(String s) {
        String[] parts = s.split(":");
        int ret = 0;
        int scale = 1;
        for (int i = parts.length - 1; i >= 0 ; i--) {
            ret += Integer.parseInt(parts[i]) * scale;
            scale *= 60;
        }
        return ret;
    }

    void testMmssToSeconds() {
        for (String s : sample) {
            int ret = mmssToSeconds(s);
            System.out.println(s + " -> " + ret);
        }
    }

    int score(String presses) {
        int score = 0;
        int last = presses.charAt(0);
        for (int press : presses.toCharArray()) {
            score += keyPressCost; // 1 for each press
            if (press != last) {
                score += moveCost;
            }
            last = press;
        }
        return score;
    }

    void testScore() {
        int[] array = {11, 60, 66, 1100, 966, 999, 1200, 1222};
        for (int i : array) {
            int score = score(String.valueOf(i));
            System.out.println(i + " -> " + score);
        }
    }

    List<String> generatePossiblePresses(int seconds) {
        // Generate the different key presses that get us a time in seconds
        List<String> ret = new ArrayList<>();
        int s = seconds % 60;
        int m = (seconds / 60) % 60;
        int h = seconds / 3600;
        ret.add(presses(h, m, s));
        if (h == 0) {
            if (s <= 39 && m > 0) {
                ret.add(presses(h, m-1, s+60));
            }
        } else {
            if (m <= 39) {
                ret.add(presses(h-1, m + 60, s));
                if (s <= 39 && m > 0) {
                    ret.add(presses(h-1, m + 60 - 1, s + 60));
                }
            }
        }
        return ret;
    }

    String presses(int h, int m, int s) {
        if (h > 0) {
            return h + "" + m + "" + s;
        } else if (m > 0) {
            return m + "" + s;
        } else {
            return "" + s;
        }
    }

//    String bestPass1(String timeString) {
//        // For times in the range (desired +/- 10%) pick the best scoring one.
//
//    }

}
