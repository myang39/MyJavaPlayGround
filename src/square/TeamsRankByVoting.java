package square;

import java.util.*;

// Has a bug, give up on fixing this
// Using this solution: https://leetcode.com/problems/rank-teams-by-votes/discuss/524853/Java-O(26n%2B(262-*-log26))-Sort-by-high-rank-vote-to-low-rank-vote
public class TeamsRankByVoting {
  public static void main(String[] args) {
//    String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
//    String[] votes = {"WXYZ","XYZW"};
//    String[] votes = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
    String[] votes = {"BCA","CAB","CBA","ABC","ACB","BAC"};
    System.out.println(rankTeams(votes));
  }

  public static String rankTeams(String[] votes) {
    int n = votes[0].length();
    if (n == 1) {
      return votes[0];
    }
    List<Map<Character, Integer>> ranks = new ArrayList<>();
    List<List<Map.Entry<Character, Integer>>> listOfRanks = new ArrayList<>();
    // int[0] is team, int[1] is count
    for (int i = 0; i < n; i++) {
      ranks.add(new HashMap<>());
    }

    for (String s : votes) {
      char[] vote = s.toCharArray();
      for (int i = 0; i < vote.length; i++) {
        Map<Character, Integer> map = ranks.get(i);
        Character c = vote[i];
        map.putIfAbsent(c, 0);
        map.put(c, map.get(c) + 1);
      }
    }

    Set<Character> set = new HashSet<>();

    for (int i = 0; i < n; i++) {
      listOfRanks.add(new ArrayList<>());
      listOfRanks.get(i).addAll(ranks.get(i).entrySet());
      listOfRanks.get(i).sort((a, b) -> b.getValue() - a.getValue());
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      int j = i;
      List<Map.Entry<Character, Integer>> rank = listOfRanks.get(j);
      if (rank.size() == 1) {
        sb.append(rank.get(0).getKey());
        continue;
      }
      Character c1 = rank.get(0).getKey(), c2 = rank.get(1).getKey();


      Map<Character, Integer> map = ranks.get(i);
      while (map.get(c1) != null && map.get(c2) != null && map.get(c1) - map.get(c2) == 0 && j < n) {
        map = ranks.get(j);
        j++;
      }
      if (j >= n) {
        sb.append(c1 - c2 < 0 ? c1 : c2);
        sb.append(c1 - c2 < 0 ? c2 : c1);
      } else if (map.get(c1) == null) {
        sb.append(c2);
        sb.append(c1);
      } else if (map.get(c2) == null) {
        sb.append(c1);
        sb.append(c2);
      } else {
        sb.append(map.get(c1) - map.get(c2) > 0 ? c1 : c2);
        sb.append(map.get(c1) - map.get(c2) > 0 ? c2 : c1);
      }
      i++;
    }

    return sb.toString();
  }
}
