public class SmallestIndexWithEqualValue {
    public static void main(String[] args) {
        System.out.println(smallestEqual(new int[]{5,7,3,5,7,5}));
    }

    public static int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println("i :" + i + ", nums[i]: " + nums[i]);
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;

    }
}
