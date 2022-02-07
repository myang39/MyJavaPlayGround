package robinhood;

import util.Maps;

import java.util.*;


// 还没写完，写了思路
public class MarginCall {
  public static void main(String[] args) {
    // [timestamp, symbol, B/S (for buy/sell), quantity, price]
    String[][] trades0 = {
            {"1", "AAPL", "B", "10", "10"},
            {"3", "GOOG", "B", "20", "5"},
            {"10", "AAPL", "S", "5", "15"}};
    String[][] trades1 = {
            {"1", "AAPL", "B", "5", "100"},
            {"2", "GOOG", "B", "5", "75"},
            {"3", "AAPLO", "B", "5", "50"}};
    Map<String, int[]> portfolio = buildPortfolio(trades1, 1000);
    print(portfolio);
  }
  static final String cash = "CASH";
  public static Map<String, int[]> buildPortfolio(String[][] trades, Integer beginCash) {
    // key: symbol, value: int[0] quantity, int[1] current price
    Map<String, int[]> map = new HashMap<>();
    TreeMap<Integer, TreeSet<String>> treeMap = new TreeMap<>(); // key -> price,
    // when price change, remove stock from old price to new price

    // locked stock map, when stock is locked because of collateral (lock same number of AAPL
    // as AAPL0), move them from treeMap to locked;
    // When some shares of AAPL0 is sold, free the same amount of AAPL stocks from this locked map
    // back to treeMap.
    Map<String, int[]> locked = new HashMap<>();

    map.put(cash, new int[] {beginCash, 1});

    // PriorityQueue 不会根据update的element重新排序，这个不work
    PriorityQueue<String> pq = new PriorityQueue<>(
            new Comparator<String>() {
              @Override
              public int compare(String s1, String s2) {
                int quant1 = map.get(s1)[0];
                int quant2 = map.get(s2)[0];
                int price1 = map.get(s1)[1];
                int price2 = map.get(s1)[1];

                if (quant1 == 0 && quant2 == 0) {
                  return -1;
                } else if (quant1 == 0) {
                  return 1;
                } else if (quant2 == 0) {
                  return -1;
                }

                if (price1 > price2) {
                  return -1;
                } else if (price1 < price2) {
                  return 1;
                }

                if (s1.equals(s2 + '0') || s2.equals(s1 + '0')) { // collateral
                  return s2.length() - s1.length();
                } else {
                  return s1.compareTo(s2);
                }
              }
            }
    );

    for (String[] s : trades) {
      String symbol = s[1];
      String buysell = s[2];
      Integer quantity = Integer.parseInt(s[3]);
      Integer price = Integer.parseInt(s[4]);
      if (buysell.equals("B")) {
        map.putIfAbsent(symbol, new int[]{0, 0});
        map.get(symbol)[0] += quantity;
        map.get(symbol)[1] = price;
        map.get(cash)[0] -= quantity * price;

      } else { // sell
        map.get(symbol)[0] -= quantity;
        map.get(symbol)[1] = price;
        map.get(cash)[0] -= quantity * price;
      }
    }
    return map;
  }

  public static void marginCall(Map<String, Integer> map, PriorityQueue<Map.Entry<String, Integer>> pq) {
    while (map.get(cash) < 0) {

    }
  }

  public static void print(Map<String, int[]> map) {
    for (Map.Entry<String, int[]> e : map.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue()[0] + " shares" + " at price: " + e.getValue()[1]);
    }
  }
}
