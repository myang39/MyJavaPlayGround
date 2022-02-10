package misc;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SumOfSubarrayRanges {
  public static void main(String[] args) {
    System.out.println(subArrayRanges(new int[]{4,-2,-3,4,1}));
  }

  public static long subArrayRanges(int[] nums) {
    long sum = 0;
    for (int size = 2; size <= nums.length; size++) { // window size
//      System.out.println("size: " + size);
      TreeMap<Integer, Integer> window = new TreeMap<>();
      for (int i = 0; i < size; i++) {
        window.put(nums[i], window.getOrDefault(nums[i], 0) + 1);
      }
//      System.out.println(window);
//      System.out.println("sum +" + (window.lastKey() - window.firstKey()));
      sum += window.lastKey() - window.firstKey();

      for (int i = size; i < nums.length; i++) {
        // move forward
        Integer leftMostCount = window.get(nums[i - size]);
        if (leftMostCount == 1) {
          window.remove(nums[i - size]);
        } else {
          window.put(nums[i - size], leftMostCount - 1);
        }
        window.put(nums[i], window.getOrDefault(nums[i], 0) + 1);
//        System.out.println(window);
//        System.out.println("sum +" + (window.lastKey() - window.firstKey()));
        sum += window.lastKey() - window.firstKey();
      }
    }

    return sum;
  }
}
