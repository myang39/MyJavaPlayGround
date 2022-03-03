package square;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class JsonToMap {
    public static void main(String[] args) {

    }

    public Map<String, Object> parse(final String input) {
        Map<String, Object> map = new HashMap<>();
        if (input != null && input.length() != 0) {
            String[] lines = input.split("\n");
            int i, n = lines.length;

            for (i = 0; i < n; i++) {
                String[] parts = lines[i].split(":");
                if (parts.length == 1) {
                    StringBuilder inner = new StringBuilder();
                    i++;

                    while (i < n) {
                        if (lines[i].startsWith("  ")) {
                            inner.append(lines[i].substring(2));
                            inner.append("\n");
                        } else {
                            break;
                        }
                        i++;
                    }
                    map.put(parts[0], parse(inner.toString()));
                } else {
                    map.put(parts[0], parts[1]);
                }
            }
        }

        return map;
    }

    public Map<String, Object> parse2(final String input) {
        Map<String, Object> map = new HashMap<>();
        Deque<Map<String, Object>> stack = new ArrayDeque<>();
        stack.addFirst(map);
        if (input != null && input.length() != 0) {
            String[] lines = input.split("\n");
            int i, n = lines.length;

            for (i = 0; i < n; i++) {
                String[] parts = lines[i].split(":");
                int curDepth = 1;
                for (int j = 0; j < parts[0].length(); j += 2) {
                    if (parts[0].charAt(j) == ' ') {
                        curDepth++;
                    } else {
                        break;
                    }
                }
                // pop map higher than me
                while (curDepth < stack.size()) {
                    stack.pollFirst();
                }

                if (parts.length == 1) {
                    Map<String, Object> newMap = new HashMap<>();
                    stack.peekFirst().put(parts[0].trim(), newMap);
                    stack.addFirst(newMap);
                } else {
                    stack.peekFirst().put(parts[0].trim(), parts[1]);
                }
            }
        }

        return map;
    }

    @Test
    public void parse() {
        String test =
                "K1:V1\n" +
                "K2:V2\n" +
                "K3:\n" +
                "  K32:\n" +
                "    K321:V321\n" +
                "    K322:V322\n" +
                "    K333:V333\n" +
                "  K31:V31\n" +
                "K4:\n" +
                "  K41:V41\n" +
                "  K42:V42";

        Map<String, Object> ans = parse2(test);
        System.out.println(ans);
        // iterate line by line
        // Check the leading space count
        // Use a stack to track the list of nodes (parents)
        // if value is empty; current node should be a container; add to stack

        Assertions.assertEquals("V333", ((Map<String, Map>)ans.get("K3")).get("K32").get("K333"));
    }
}
