package weekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlternatingNumbers {
    public static void main(String[] args) {
        AlternatingNumbers s  = new AlternatingNumbers();

        if (s.minimumOperationsII(new int[] {2,2,2,2}) != 2) {
            System.out.println("2,2,2,2");
        }

        if (s.minimumOperationsII(new int[] {1,2,2,2,2}) != 2) {
            System.out.println("1,2,2,2,2");
        }

        if (s.minimumOperationsII(new int[] {3,1,3,2,4,3}) != 3) {
            System.out.println("3,1,3,2,4,3");
        }

        if (s.minimumOperationsII(new int[] {4,4,4,4,3,4}) != 2) {
            System.out.println("4,4,4,4,3,4");
        }

        // [3,1,3,2,4,3]

    }

    public int minimumOperations(int[] nums) {
        /**
         *
         */
        if (nums.length == 1) {
            return 0;
        }
        int len = nums.length;
        if (len == 2) {
            if (nums[0] == nums[1]) {
                return 1;
            } else {
                return 0;
            }
        }

        Map<Integer, Integer> even = new HashMap<>();
        Map<Integer, Integer> odd = new HashMap<>();


        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (i % 2 == 0) {
                even.put(num, even.getOrDefault(num, 0) + 1);
            } else {
                odd.put(num, odd.getOrDefault(num, 0) + 1);
            }
        }

        List<Integer> evenFirstAndSecondMaxKey = firstAndSecondMaxKey(even, nums[0]);
        List<Integer> oddFirstAndSecondMaxKey = firstAndSecondMaxKey(odd, nums[1]);

        Integer evenFirstKey = evenFirstAndSecondMaxKey.get(0);
        Integer oddFirstKey = oddFirstAndSecondMaxKey.get(0);

        Integer evenSecondKey = evenFirstAndSecondMaxKey.get(1);
        Integer oddSecondKey = oddFirstAndSecondMaxKey.get(1);

//        System.out.println(evenFirstAndSecondMaxKey.get(0));
//        System.out.println(evenFirstAndSecondMaxKey.get(1));
//
//        System.out.println(oddFirstAndSecondMaxKey.get(0));
//        System.out.println(oddFirstAndSecondMaxKey.get(1));

        if (!evenFirstKey.equals(oddFirstKey)) {
            return len - even.get(evenFirstKey) - odd.get(oddFirstKey);
        } else if (even.get(evenFirstKey) > odd.get(oddFirstKey)) { // need oddMaxTwo
            return len - even.get(evenFirstKey) - ((oddSecondKey == null) ? 0 : odd.get(oddSecondKey));
        } else {
            return len - odd.get(oddFirstKey) - ((evenSecondKey == null) ? 0 : even.get(evenSecondKey));
        }

    }

    /**
     *
     * @param map frequency map
     * @return List of array, (key, frequency), return both the largest and second largest is possible;
     * if no second largest, return first largest key as it is, and null for the second-largest key
     */
    private List<Integer> firstAndSecondMaxKey(Map<Integer, Integer> map, int firstKey) {
        List<Integer> rest = new ArrayList<>();
        if (map.size() == 1) {
            rest.add(firstKey);
            rest.add(null);
            return rest;
        }

        Integer maxKey = firstKey;
        Integer secondMaxKey = null;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int key = e.getKey();
            if (key == maxKey) {
                continue;
            }
            int count = e.getValue();
            if (count > map.get(maxKey)) {
                secondMaxKey = maxKey;
                maxKey = key;
            } else if (secondMaxKey == null || count > map.get(secondMaxKey)) {
                secondMaxKey = key;
            }
        }
        rest.add(maxKey);
        rest.add(secondMaxKey);
        return rest;
    }

    /**
     * Tricks on map, safe some typing
     * @param nums
     * @return
     */
    public int minimumOperationsII(int[] nums) {
        Map<Integer, Integer> even = new HashMap<>();
        Map<Integer, Integer> odd = new HashMap<>();

        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (i % 2 == 0) {
                even.put(num, even.getOrDefault(num, 0) + 1);
            } else {
                odd.put(num, odd.getOrDefault(num, 0) + 1);
            }
        }

        for (int key : odd.keySet()) {
            if (even.get(key) != null) {
                if (even.get(key) > odd.get(key)) {
                    odd.put(key, 0);
                } else {
                    even.put(key, 0);
                }
            }
        }


        int evenNum = 0;
        int oddNum = 0;

        for (int key : even.keySet()) {
            evenNum = Math.max(evenNum, even.get(key));
        }

        for (int key : odd.keySet()) {
         oddNum = Math.max(oddNum, odd.get(key));
        }

        return len - evenNum - oddNum;
    }
}
