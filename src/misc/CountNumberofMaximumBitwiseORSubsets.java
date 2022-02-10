package misc;

import java.util.HashMap;
import java.util.Map;

public class CountNumberofMaximumBitwiseORSubsets {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5};
        System.out.println(countMaxOrSubsets(nums));
    }
    public static int countMaxOrSubsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            // itself
            map.put(i, map.getOrDefault(i, 0) + 1);
            max = Math.max(max, i);
            // Or with the rest
            for (Integer e : map.keySet()) {
                int cur = i | e;
                max = Math.max(max, cur);
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }
        printHashMap(map);
        return map.get(max);
    }

    private static void printHashMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}

class Solution {
    public int countMaxOrSubsets(int[] nums) {
        Map<Integer, Integer> orValCountMap = new HashMap<>();
        int[] max = new int[]{0};

        dfs(nums, 0, orValCountMap, max, null);

        return orValCountMap.get(max[0]);
    }

    private void dfs(int[] nums, int idx, Map<Integer, Integer> orValCountMap, int[] max, Integer curVal) {
        if(idx >= nums.length) {
            if(curVal != null) {
                orValCountMap.put(curVal, orValCountMap.getOrDefault(curVal, 0) + 1);
                max[0] = Math.max(max[0], curVal);
            }
            return;
        }

        dfs(nums, idx + 1, orValCountMap, max, curVal);

        if(curVal != null) {
            dfs(nums, idx + 1, orValCountMap, max, curVal | nums[idx]);
        } else {
            dfs(nums, idx + 1, orValCountMap, max, nums[idx]);
        }
    }
}
