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

public class BusRoutes {
  public static void main(String[] args) {
    int[][] routes = {
            {1,2,7},{3,6,7}
    };
    int source = 6;
    int target = 1;
    System.out.println(numBusesToDestination(routes, source, target));
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
    int n = routes.length;
    Map<Integer, Set<Integer>> stopToBuses = new HashMap<>();
    for (int i = 0; i < n; i++) { // for every bus
      for (int j : routes[i]) { // for every stop
        stopToBuses.putIfAbsent(j, new HashSet<>());
        stopToBuses.get(j).add(i);
      }
    }

    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{s, 0}); // stop, steps
    Set<Integer> seenStops = new HashSet<>(); // stop visited
    seenStops.add(s);
    boolean[] seenBuses = new boolean[n];
    while (!q.isEmpty()) {
      int stop = q.peek()[0];
      int step = q.peek()[1];
      q.poll();
      if (stop == t) {
        return step;
      }
      for (int bus : stopToBuses.get(stop)) { // all the buses to this stop
        if (seenBuses[bus]) {
          continue;
        }
        seenBuses[bus] = true;
        for (int newStop : routes[bus]) {
          if (!seenStops.contains(newStop)) {
            seenStops.add(newStop);
            q.offer(new int[] {newStop, step + 1});
          }
        }
      }
    }
    return -1;
  }







}
