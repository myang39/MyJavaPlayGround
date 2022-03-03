package square;

import java.util.*;

class Sock {
  int id;
  String color;
  int dir; // left = 0, right = 1

  Sock(int id, String color, int dir) {
    this.id = id;
    this.color = color;
    this.dir = dir;
  }
}

public class SocksPair {

  public static void main(String[] args) {
    // [1, black, left]， [2, black, right]， [3, pink, left]， [4, pink, right]
    Sock sock1 = new Sock(1, "black", 0);
    Sock sock2 = new Sock(2, "black", 1);
    Sock sock3 = new Sock(3, "pink", 0);
    Sock sock4 = new Sock(4, "pink", 1);

    Sock sock5 = new Sock(5, "black", 0);
    Sock sock6 = new Sock(6, "black", 1);
    Sock sock7 = new Sock(7, "pink", 0);
    Sock sock8 = new Sock(8, "pink", 1);

    Sock[] socks = {sock7, sock8, sock1, sock2, sock5, sock6, sock3, sock4};
    System.out.println(Arrays.deepToString(socksParingII(socks)));

//    System.out.println("1 ^ 1 = " + (1 ^ 1));
//    System.out.println("1 ^ 0 = " + (1 ^ 0));
  }

  public static int[][] socksParing(Sock[] socks) {
     // key is color, array 0 is left, array 2 is right, list is ids
    Map<String, List<List<Integer>>> map = new HashMap<>();
    List<int[]> ret = new LinkedList<>();
    for (Sock s : socks) {
      if (map.get(s.color) == null) {
        map.put(s.color, new ArrayList<>());
        map.get(s.color).add(new LinkedList<>());
        map.get(s.color).add(new LinkedList<>());
        map.get(s.color).get(s.dir).add(s.id);
      } else if (map.get(s.color).get(1 - s.dir).size() != 0) {
        ret.add(new int[]{map.get(s.color).get(1 - s.dir).get(0), s.id});
        map.get(s.color).get(1 - s.dir).remove(0);
        continue;
      } else {
        map.get(s.color).get(s.dir).add(s.id);
      }
    }

    return ret.toArray(new int[ret.size()][]);
  }

  // Move left and right lift out of the map, seems more clear
  public static int[][] socksParingII(Sock[] socks) {
    // key is color, array 0 is left, array 2 is right, inner list is ids
    List<Map<String, List<Integer>>> maps = new ArrayList<>();
    maps.add(new HashMap<>());
    maps.add(new HashMap<>());

    List<int[]> ret = new LinkedList<>();

    for (Sock s : socks) {
      String color = s.color;
      Map<String, List<Integer>> oppositeMap = maps.get(1 - s.dir);

      if (oppositeMap.get(color) != null && oppositeMap.get(color).size() > 0) {
        ret.add(new int[]{oppositeMap.get(color).get(0), s.id});
        oppositeMap.get(color).remove(0);
      } else {
        Map<String, List<Integer>> map = maps.get(s.dir);
        map.putIfAbsent(s.color, new LinkedList<>());
        map.get(s.color).add(s.id);
      }
    }

    return ret.toArray(new int[ret.size()][]);
  }
}
