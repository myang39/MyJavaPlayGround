package util;

public class LeetcodeToClassName {
  public static void main(String[] args) {
    String lcTitle = "273. Integer to English Words";
    System.out.println("lc" +
            lcTitle.replace(". ", "_")
            .replace(" ", ""));
  }
}
