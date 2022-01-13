public class MinimumSwapstoGroupAll1sTogetherII {
  public static void main(String[] args) {
    System.out.println(minSwaps(new int[]{0,1,1,1,0,0,1,1,0}));
  }

  public static int minSwaps(int[] nums) {
    int len = nums.length;
    int count = 0; // window size
    for (int i = 0; i < len; i++) {
      count += nums[i];
    }
    int min = Integer.MAX_VALUE;
    int i = 0, j = 0;
    int z = 0;
    for (j = 0; j < count; j++) {
      z += nums[j] == 0 ? 1 : 0;
    }
    j--;
    System.out.println("j : z = " + j + " : " + z );
    min = Math.min(min, z);
    while (i < len) {
      if (nums[i] == 0) {
        z--;
      }
      i++;
      j++;
      if (nums[j % len] == 0) {
        z++;
      }
      min = Math.min(min, z);
    }

    return min;
  }
}
