public class IdenticalDistribution {
    public static void main(String[] args) {
        int[] snacks = new int[]{4,7,3,8,15};
        System.out.println(minAdditionalSnacks(snacks));
    }

    public static int minAdditionalSnacks(int[] snacks) {
        int n = snacks.length;
        int ans = n;
        int M = 0;
        for (int s : snacks) {
            M = Math.max(M, s);
        }
        for (int d = 2; d <= M; d++) {
            int s = 0;
            for (int i = 0; i < n && s < ans; i++) {
                int left = snacks[i] % d;
                if (left > 0) {
                    s += d - snacks[i] % d;
                }
            }
            ans = Math.min(ans, s);
        }
        return ans;
    }
}
