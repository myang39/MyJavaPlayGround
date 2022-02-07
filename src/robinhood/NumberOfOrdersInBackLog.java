package robinhood;

import java.util.*;

public class NumberOfOrdersInBackLog {
  public static void main(String[] args) {
    int[][] orders = {{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};
//    int[][] orders = {{10,5,0},{15,2,1},{25,1,1},{30,4,0}};
    System.out.println(getNumberOfBacklogOrders(orders));
  }

  public static int getNumberOfBacklogOrders(int[][] orders) {
//    Map<Integer, Integer> map = new HashMap<>();
//    Set<Integer> set = new HashSet<>();
    PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0] );

    PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> a[0] - b[0] );

    // order[0] price
    // order[1] amount
    // order[2] type, 0 buy, 1 sell
    // if buy >= sell -> deal
    for (int[] order : orders) {
      if (order[2] == 0) { // buy
        buy.add(order);
      } else {
        sell.add(order);
      }
      // check if we can make a deal
      while (sell.size() != 0 && buy.size() != 0
              && buy.peek()[0] >= sell.peek()[0]) {
        int[] sellOrder = sell.peek();
        int[] buyOrder =  buy.peek();
        int k = Math.min(sellOrder[1], buyOrder[1]);
        sellOrder[1] -= k;
        buyOrder[1] -= k;
        if (sellOrder[1] == 0) {
          sell.poll();
        }
        if (buyOrder[1] == 0) {
          buy.poll();
        }
      }
    }

//    int a = (int)Math.pow(10, 9);
//    System.out.println(a);

    long res = 0;
    long mod = 1000000007;

    for (int[] order : sell) {
//      System.out.println(order[0] + " " + order[1] + " " + order[2]);
      res += order[1];
      res %= mod;
//      System.out.println("res: " + res);
//      res %= (long)(10^9 + 7);
//      System.out.println("res after %: " + res);
    }
    for (int[] order : buy) {
      res += order[1];
      res %= mod;
    }

    return (int)res;
  }
}
