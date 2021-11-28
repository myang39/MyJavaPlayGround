package twoSigma;

import java.util.*;

public class ParallelCoursesIII {
    public static void main(String[] args) {
        int n = 7;
        int[][] relations = {
                {1,5},{2,5},{3,5},{3,4},{4,5},{6,7}
        };
        int[] time = {1,2,3,4,5,2,3};
        System.out.println(Arrays.toString(parallelCourses(n, relations, time).toArray()));
    }

    private static List<List<Integer>> parallelCourses(int n, int[][] relations, int[] time) {
        List<List<Integer>> rest = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[n + 1];
        for (int[] relation : relations) {
            map.putIfAbsent(relation[0], new ArrayList<>());
            map.get(relation[0]).add(relation[1]);
            inDegree[relation[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            map.putIfAbsent(i, new ArrayList<>());
            if (inDegree[i] == 0) {q.offer(i);}
        }

        int[] startTime = new int[n + 1];
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : map.get(cur)) {
                inDegree[nei]--;
                System.out.println("nei :" + nei + " inDegree[nei]: " + inDegree[nei]);
                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
                startTime[nei] = Math.max(startTime[nei], startTime[cur] + time[cur - 1]);
            }

            for (int i = startTime[cur]; i < startTime[cur] + time[cur - 1]; i++) {
                if (rest.size() <= i) {
                    rest.add(new ArrayList<>());
                }
                rest.get(i).add(cur);
            }
        }
        return rest;
    }
}
