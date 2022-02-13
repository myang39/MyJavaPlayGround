package weekly;

import java.util.HashMap;
import java.util.Map;

public class AlternatingNumbers {
    public static void main(String[] args) {
        AlternatingNumbers s  = new AlternatingNumbers();

        if (s.minimumOperations(new int[] {2,2,2,2}) != 2) {
            System.out.println("2,2,2,2");
        }

        if (s.minimumOperations(new int[] {1,2,2,2,2}) != 2) {
            System.out.println("1,2,2,2,2");
        }

        if (s.minimumOperations(new int[] {3,1,3,2,4,3}) != 3) {
            System.out.println("3,1,3,2,4,3");
        }

        // [3,1,3,2,4,3]

    }

    public int minimumOperations(int[] nums) {
        // algo:
        // extract the array into even and odd
        // find the max freq number in that array
        // we need to operate len_array - len_of_max_freq
        int len = nums.length;
        // List<Integer> odd = new ArrayList<>();
        // List<Integer> even = new ArrayList<>();
        if (len == 2) {
            if (nums[0] == nums[1]) {
                return 1;
            } else {
                return 0;
            }
        }
        Map<Integer, Integer> oddFreq = new HashMap<>();
        Map<Integer, Integer> evenFreq = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (i % 2 != 0) {
                oddFreq.putIfAbsent(num, 0);
                oddFreq.put(num, oddFreq.get(num) + 1);
            } else {
                evenFreq.putIfAbsent(num, 0);
                evenFreq.put(num, evenFreq.get(num) + 1);
            }
        }

        Integer evenKey = 0;
        Integer evenMax = 0;
        Integer evenMaxTwo = null;
        // count max and second max from each map
        if (evenFreq.size() == 1) {
            evenMax = evenFreq.get(nums[0]);
            evenKey = nums[0];
        } else {
            for (Map.Entry<Integer, Integer> e : evenFreq.entrySet()) {
                int count = e.getValue();
                if (count > evenMax) {
                    evenMaxTwo = evenMax;
                    evenMax = count;
                    evenKey = e.getKey();
                } else if (count > evenMaxTwo) {
                    evenMaxTwo = count;
                }
            }
        }

        Integer oddKey = 0;
        Integer oddMax = 0;
        Integer oddMaxTwo = null;

        if (oddFreq.size() == 1) {
            oddMax = oddFreq.get(nums[1]);
            oddKey = nums[1];
        } else {
            for (Map.Entry<Integer, Integer> e : oddFreq.entrySet()) {
                int count = e.getValue();
                if (count > oddMax) {
                    oddMaxTwo = oddMax;
                    oddMax = count;
                    oddKey = e.getKey();
                } else if (count > oddMaxTwo) {
                    oddMaxTwo = count;
                }
            }
        }

        if (!evenKey.equals(oddKey)) {
            return len - evenMax - oddMax;
        } else if (evenMax > oddMax) { // need oddMaxTwo
            return len - evenMax - ((oddMaxTwo == null) ? 0 : oddMaxTwo);
        } else {
            return len - oddMax - ((evenMaxTwo == null) ? 0 : evenMaxTwo);
        }

    }
}
