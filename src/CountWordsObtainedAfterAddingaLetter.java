import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountWordsObtainedAfterAddingaLetter {
  public static void main(String[] args) {

    String[] startWords = {"g","vf","ylpuk","nyf","gdj","j","fyqzg","sizec"};
    String[] targetWords = {"r","am","jg","umhjo","fov","lujy","b","uz","y"};
//    String[] startWords = {"ant","act","tack"};
//    String[] targetWords = {"tack","act","acti"};

    System.out.println(wordCount(startWords, targetWords));
  }

  public static int wordCount(String[] startWords, String[] targetWords) {
    int rest = 0;
    for (int i = 0; i < targetWords.length; i++) {
      String cur = targetWords[i];
      int len = cur.length();
      Map<Character, Integer> map = new HashMap<>();
      for (Character c : cur.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
      }

      for (int j = 0; j < startWords.length; j++) {
        String s = startWords[j];
        int len2 = s.length();
        if (len2 + 1 == len) {
          Map<Character, Integer> map2 = new HashMap<>(map);
          int count = 0;
          Set<Character> set2 = new HashSet<>();
          for (Character cc : s.toCharArray()) {
            set2.add(cc);
            if (map2.get(cc) == null) {
              break;
            } else {
              count ++;
              map2.put(cc, map2.get(cc) - 1);
              if (map2.get(cc) == 0) {
                map2.remove(cc);
              }
            }
          }
          if (count == len2) {
            Map.Entry<Character, Integer> e = map2.entrySet().iterator().next();
            if (!set2.contains(e.getKey())) {
              rest++;
              break;
            }
          }
        }
      }
    }

    return rest;
  }

  public int wordCount2(String[] startWords, String[] targetWords) {
    Set<Integer> startSet = new HashSet<>();
    for(String word : startWords){
      startSet.add(toInt(word));
    }
    int ans = 0;
    for(String word : targetWords){
      int num = toInt(word);
      for(int i=0; i<26; i++){
        if((num & (1<<(i))) > 0){
          int temp = num - (1<<(i));
          if(startSet.contains(temp)){
            ans++;
            break;
          }
        }
      }
    }
    return ans;
  }

  public int toInt(String s){
    int ret = 0;
    for(char c : s.toCharArray()){
      ret += (1<<(c-'a'));
    }
    return ret;
  }
}
