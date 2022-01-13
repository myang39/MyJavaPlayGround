// https://leetcode.com/contest/weekly-contest-271/problems/watering-plants-ii/
public class WateringPlantsII {
  public static void main(String[] args) {
    System.out.println(minimumRefill(new int[]{1, 2, 4, 4, 5}, 6, 5));
  }

  public static int minimumRefill(int[] plants, int capacityA, int capacityB) {
    int left = 0;
    int right = plants.length - 1;
    int[] cnt = new int[1];
    int ca = capacityA;
    int cb = capacityB;
    while (left < right) {
      System.out.println("left " + left);
      ca = waterLeftAfterWatering(ca, cnt, plants[left], capacityA);
      left++;

      System.out.println("right " + right);
      cb = waterLeftAfterWatering(cb, cnt, plants[right], capacityB);
      right--;
    }
    if (left == right) {
      System.out.println("left " + left + " right " + right);
      waterLeftAfterWatering(Math.max(ca, cb), cnt, plants[left], ca >= cb ? capacityA : capacityB);
    }
    return cnt[0];
  }

  private static int waterLeftAfterWatering(int c, int[] cnt, int plant, int capacity) {
    if (c < plant) {
      c = capacity;
      cnt[0]++;
      System.out.println("cnt++");
    }
    c -= plant;
    return c;
  }

}
