package misc;

import java.util.Arrays;

public class ReorderArray {
  public static void main(String[] args) {
     char[] array = {'a','b','c','d','1','2','3','4'};
//    char[] array = {'a', 'b', '1', '2'};
    System.out.println(Arrays.toString(reorder(array)));
  }

  public static char[] reorder(char[] array) {
    // Write your solution here
    if (array.length <= 2) {
      return array;
    }

    if (array.length % 2 == 0) {
      helper(array, 0, array.length - 1);
    } else {
      helper(array, 0, array.length - 2);
    }
    return array;
  }

  private static void helper(char[] array, int l, int r) {
    System.out.println("start " + Arrays.toString(array));
    int len = 1 + r - l;
    System.out.println("len " + len);
    if (len <= 2) {
      return;
    }
    int m = l + len / 2;
    System.out.println("m " + m);
    int lm = l + len / 4;
    System.out.println("lm " + lm);
    int rm = m + len / 4;
    System.out.println("rm " + rm);
    reverse(array, lm, m - 1);
    reverse(array, m, rm - 1);
    reverse(array, lm, rm - 1);

    System.out.println("end " + Arrays.toString(array));

    System.out.println("lm - 1 + (rm - m) :" + (lm - 1 + (rm - m)));
    helper(array, l, lm - 1 + (rm - m));
    helper(array, lm + (rm - m), r);
  }

  public static void reverse(char[] array, int i, int j) {
    while (i < j) {
      char temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }
  }

//  public static int[] reorder(int[] array) {
//    // Write your solution here
//    if (array.length % 2 == 0) {
//      helper(array, 0, array.length - 1);
//    }
//    else{
//      helper(array, 0, array.length - 2);
//    }
//    return array;
//  }
//
//  public static void helper(int[] array, int left, int right) {
//    if (left >= right - 1) {
//      return;
//    }
//    int len = right - left + 1;
//    System.out.println("len: " + len);
//    int lm = left + len / 4;
//    System.out.println("lm: " + lm);
//    int mm = left + len / 2;
//    System.out.println("mm: " + mm);
//    int rm = left + len * 3/4;
//    System.out.println("rm: " + rm);
//    reverse(array, lm, mm - 1);
//    reverse(array, mm, rm - 1);
//    reverse(array, lm, rm - 1);
//
//    System.out.println("left + (lm - left) * 2 - 1: " + (left + (lm - left) * 2 - 1));
//    helper(array, left, left + (lm - left) * 2 - 1);
//    System.out.println("left + (lm - left) * 2: " + (left + (lm - left) * 2));
//    helper(array, left + (lm - left) * 2, right);
//  }
//
//  public static void reverse(int[] array, int left, int right) {
//    while (left < right) {
//      int temp = array[left];
//      array[left] = array[right];
//      array[right] = temp;
//      left++;
//      right--;
//    }
//  }
}
