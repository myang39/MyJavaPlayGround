package util;

import java.util.Map;

public class Maps {
  public static void printStringInteger(Map<String, Integer> map) {
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue());
    }
  }

  public static void printCharacterInteger(Map<Character, Integer> map) {
    for (Map.Entry<Character, Integer> e : map.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue());
    }
  }

  public static void printCharacterString(Map<Character, String> map) {
    for (Map.Entry<Character, String> e : map.entrySet()) {
      System.out.println(e.getKey() + " : " + e.getValue());
    }
  }
}
