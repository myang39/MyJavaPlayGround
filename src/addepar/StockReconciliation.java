package addepar;

import java.util.*;

public class StockReconciliation {
    public static void main(String[] args) {
        List<String> today = new ArrayList<>(Arrays.asList("APPL 50", "GOOG 100", "Cash 10"));
        List<String> txn = new ArrayList<>(Arrays.asList("APPL SL 50 10", "GOOG BY 120 20"));
        List<String> list = reconciliation(today, txn, null);
        System.out.println(list);
    }

    public static List<String> reconciliation(List<String> today, List<String> txn, List<String> endOfDay) {
        Map<String, Double> map = new HashMap<>();
        for (String s : today) {
            String[] ss = s.split(" ");
            map.put(ss[0], map.getOrDefault(ss[0], 0D) + Double.parseDouble(ss[1]));
        }
//        System.out.println(map.get("Cash"));

        // txn: ["APPL SL 50 10", "GOOG BY 120 20"]
        for (String s : txn) {
            String[] ss = s.split(" ");
//            System.out.println(Arrays.toString(ss));
            if (ss[1].equals("SL")) {
                map.put(ss[0], map.getOrDefault(ss[0], 0D) - Double.parseDouble(ss[2]));
                map.put("Cash", map.getOrDefault("Cash", 0D) + Double.parseDouble(ss[3]));
            } else {
                map.put(ss[0], map.getOrDefault(ss[0], 0D) + Double.parseDouble(ss[2]));
                map.put("Cash", map.getOrDefault("Cash", 0D) - Double.parseDouble(ss[3]));
            }
//            System.out.println(map.get("Cash"));
        }


        List<String> rest = new ArrayList<>();
        for (Map.Entry<String, Double> e : map.entrySet()) {
            rest.add(e.getKey() + " " + e.getValue());
        }

        return rest;
    }

}
