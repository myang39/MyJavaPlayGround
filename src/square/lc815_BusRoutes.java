package square;

import java.util.*;
/*

 n is # os buses and k is how many stops per bus

map<stop, set of buses> -> n * k

q<stop>

bfs -> n * k

pop stop -> buses
enqueue stop

 */

public class lc815_BusRoutes {
  public static void main(String[] args) {
    int[][] routes = {
            {1,2,7},{3,6,7}
    };
    int source = 6;
    int target = 1;
    System.out.println(numBusesToDestinationII(routes, source, target));
  }
  public static int numBusesToDestination(int[][] routes, int source, int target) {
    int s = source;
    int t = target;

    if (s == t) {
      return 0;
    }

    int n = routes.length;
    // think of the buses as nodes
    // we want to take the least number of buses
    // bfs
    List<Set<Integer>> busToStops = new ArrayList<>(); // bus -> stops
    for (int i = 0; i < routes.length; i++) {
      Set<Integer> set = new HashSet<>();
      for (int stop : routes[i]) {
        set.add(stop);
      }
      busToStops.add(set);
    }

    // bus -> bus
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    // build bus to bus graph
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (intersect(busToStops.get(i), busToStops.get(j))) {
          graph.get(i).add(j);
          graph.get(j).add(i);
        }
      }
    }

    Set<Integer> targets = new HashSet<>();
    Set<Integer> seen = new HashSet<>();
    Queue<int[]> q = new LinkedList<>();

    // initialize targets and sources
    for (int i = 0; i < n; i++) {
      if (busToStops.get(i).contains(s)) {
        seen.add(i);
        q.add(new int[]{i, 1});
      }
      if (busToStops.get(i).contains(t)) {
        targets.add(i);
      }
    }

    // bfs
    while (!q.isEmpty()) {
      int[] busAndStep = q.poll();
      int bus = busAndStep[0];
      int step = busAndStep[1];
      if (targets.contains(bus)) {
        return step;
      }
      for (int nei : graph.get(bus)) {
        if (!seen.contains(nei)) {
          seen.add(nei);
          q.offer(new int[]{nei, step + 1});
        }
      }
    }

    return -1;

  }

  private static boolean intersect(Set<Integer> s1, Set<Integer> s2) { // time: O(s1.length)
    if (s1.size() <= s2.size()) {
      for (Integer i : s1) {
        if (s2.contains(i)) {
          return true;
        }
      }
    } else {
      for (Integer i : s2) {
        if (s1.contains(i)) {
          return true;
        }
      }
    }
    return false;
  }

  public static int numBusesToDestinationII(int[][] routes, int s, int t) {
    if (s == t) {
      return 0;
    }

    // create stop to buses graph
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < routes.length; i++) { // for every bus
      for (int j = 0; j < routes[i].length; j++) { // for every stop
        map.putIfAbsent(routes[i][j], new ArrayList<>());
        map.get(routes[i][j]).add(i);
      }
    }

    printIntegetToListMap(map);

    Queue<Integer> q = new LinkedList<>();
    q.offer(s);
    Set<Integer> seen = new HashSet<>();
    seen.add(s);

    int step = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int cur = q.poll();
        System.out.println("stop:" + cur);
        if (cur == t) {
          return step;
        }
        List<Integer> buses = map.get(cur);
        for (int bus : buses) {
          for (int stop : routes[bus]) {
            if (!seen.contains(stop)) {
              seen.add(stop);
              q.offer(stop);
            }
          }
        }
      }
      step++;
    }
    return -1;
  }



  private static void printIntegetToListMap(Map<Integer, List<Integer>> map) {
    for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
      System.out.println("stop " + e.getKey() + ":" + e.getValue());
    }
  }




}
