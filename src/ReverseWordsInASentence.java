import java.util.Arrays;

public class ReverseWordsInASentence {
  public static void main(String[] args) {
    String input = "an apple";
    System.out.println(reverseWords(input));
  }

  public static String reverseWords(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    // Write your solution here
    // reverse the whole input
    char[] array = input.toCharArray();
    reverse(array, 0, input.length() - 1);
    System.out.println(Arrays.toString(array));
    // swap each words
    int start = 0;
    int end = 1;

    while (end < array.length) {
      while (end < array.length && array[end] != ' ') {
//        System.out.println(input.charAt(end));
        end++;
      }
      System.out.println("end: " + end);
      reverse(array, start, end - 1);
      System.out.println(Arrays.toString(array));
      start = end + 1;
      end = start + 1;
    }

    return new String(array);
  }

  private static void reverse(char[] array, int l, int r) {
    while (l < r) {
      swap(array, l++, r--);
    }
  }

  private static void swap(char[] array, int i, int j) {
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
