import java.util.Arrays;

public class KClosest {
    public static void main(String[] args) {
        int[] array =  new int[]{1,3,3,6,9,9,12,15};
        int target = 0;
        int k = 3;
        System.out.println(Arrays.toString(kClosest(array, target, k)));
    }

    public static int[] kClosest(int[] array, int target, int k) {
        // Write your solution here
        int l = 0;
        int r = array.length - 1;
        int m = 0;
        while (l < r - 1) {
            m = l + (r - l) / 2;
            if (array[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }
        System.out.println(m);
        int[] ret = new int[k];
        int i = 0;
        while (i < k) {
            if (r >= array.length || l >= 0 && Math.abs(array[l] - target) <= Math.abs(array[r] - target)) {
                ret[i++] = array[l];
                System.out.println(l);
                l--;
            } else {
                ret[i++] = array[r];
                System.out.println(r);
                r++;
            }
        }
        return ret;
    }
}
