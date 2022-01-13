package util;

public class Arrays {
  public static void reverse(char[] array, int i, int j) {
    while (i < j) {
      char temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

  public static void reverse(int[] array, int i, int j) {
    while (i < j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

}
