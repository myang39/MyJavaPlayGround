package square;

import java.util.*;

// Has a bug, give up on fixing this
// Using this solution: https://leetcode.com/problems/rank-teams-by-votes/discuss/524853/Java-O(26n%2B(262-*-log26))-Sort-by-high-rank-vote-to-low-rank-vote
public class lc1366_TeamsRankByVoting {
  public static void main(String[] args) {
//    String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
//    String[] votes = {"WXYZ","XYZW"};
//    String[] votes = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
    String[] votes = {"BCA","CAB","CBA","ABC","ACB","BAC"};
    System.out.println(rankTeams(votes));
  }

  public static String rankTeams(String[] votes) {
    Map<Character, int[]> map = new HashMap<>();
    int l = votes[0].length();
    for (String vote : votes) {
      for (int i = 0; i < l; i++) {
        char c = vote.charAt(i);
        map.putIfAbsent(c, new int[l]);
        map.get(c)[i]++;
      }
    }

    List<Character> list = new ArrayList<>(map.keySet());
    list.sort(
            (a, b) -> {
              int[] aRanks = map.get(a);
              int[] bRanks = map.get(b);
              for (int i = 0; i < l; i++) {
                if (aRanks[i] != bRanks[i]) {
                  return bRanks[i] - aRanks[i];
                }
              }
              return a - b;
            }
    );

    StringBuilder sb = new StringBuilder();
    for (Character c : list) {
      sb.append(c);
    }
    return sb.toString();
  }
}
