package misc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    public static void main(String[] args) {
        ReorganizeString rs = new ReorganizeString();
        System.out.println(rs.reorganizeString("abbabbaaab"));
    }

    public String reorganizeString(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        for(char c: s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue() == 0 ? a.getKey() - b.getKey() : b.getValue() - a.getValue()
        );
        for(Map.Entry<Character, Integer> e: countMap.entrySet()) {
            pq.offer(e);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            Map.Entry<Character, Integer> e1 = pq.poll();
            if(pq.isEmpty()) {
                if(e1.getValue() > 1) {
                    return "";
                } else {
                    sb.append(e1.getKey());
                    break;
                }
            } else {
                sb.append(e1.getKey());
            }

            Map.Entry<Character, Integer> e2 = pq.poll();
            sb.append(e2.getKey());

//            if(e1.getValue() > 1) {
//                countMap.put(e1.getKey(), e1.getValue() - 1);
//                pq.offer(e1);
//            }

            if(e2.getValue() > 1) {
                countMap.put(e2.getKey(), e2.getValue() - 1);
                pq.offer(e2);
            }

             if(e1.getValue() > 1) {
                 countMap.put(e1.getKey(), e1.getValue() - 1);
                 pq.offer(e1);
             }

        }

        return sb.toString();
    }

    private class CharComparator implements Comparator<Map.Entry<Character, Integer>> {
        @Override
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
            if(e1.getValue() == e2.getValue()) {
//                return e1.getKey() - e2.getKey()
                return 0;
            }

            return e1.getValue() > e2.getValue() ? -1 : 1;
        }
    }
}
