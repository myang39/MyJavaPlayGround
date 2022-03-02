package labuladong.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class Framework {
    public static void main(String[] args) {
        System.out.println(minWindow("ebbancf", "abc"));
    }
    void slidingWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, valid = 0;
        char[] sArray = s.toCharArray();
        while (right < sArray.length) {
            // c is the character just get into the window
            char c = sArray[right];
            // right forward the widnow
            right++;
            // do update for data in the window

            System.out.println("Window: " + left + ", " + right);

            // check whether left window need to shrink
            while (true) { // some conditions
                // d is the character that will be moved out of the window
                char d = sArray[left];

                left++;
                // do update for data in the window
            }
        }
    }

    static String minWindow(String input, String t) {
        Map<Character, Integer> need = new HashMap<>(),
                window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        int start = 0, len = Integer.MAX_VALUE;
        char[] s = input.toCharArray();
        while (right < s.length) {
            char c = s[right];
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s[left];
                left++;

                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? null : input.substring(start, start + len);
    }
}
