package misc;

import java.util.*;

public class ParallelCoursesIII {
    public static void main(String[] args) {
        ParallelCoursesIII p = new ParallelCoursesIII();
        int n = 3;
        int[][] relations = {{1,3},{2,3}};
        int[] times = {3,2,5};
        System.out.println(p.minimumTime(n, relations, times));
    }

    class Class {
        int idx;
        int pre;
        List<Integer> classes;
    }

    public int minimumTime(int n, int[][] relations, int[] time) {
        Map<Integer, Class> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Class c = new Class();
            c.classes = new ArrayList<>();
            c.idx = i;
            map.put(i, c);
        }
        for (int[] r : relations) {
            int first = r[0] - 1;
            int second = r[1] - 1;

            Class i = map.get(first);
            i.classes.add(second);

            Class j = map.get(second);
            j.pre = j.pre + 1;
        }
        PriorityQueue<Class> q = new PriorityQueue<>(
                (a, b) -> a.pre - b.pre
        );
        int total = 0;
        for (int i = 0; i < n; i++) {
            q.offer(map.get(i));
        }
        while (!q.isEmpty()) {
            int moreTime = 0;
            List<Class> noPres = new ArrayList<>();
            while (!q.isEmpty() && q.peek().pre == 0) {
                Class c = q.poll();
                moreTime = Math.max(moreTime, time[c.idx]);
                noPres.add(c);
            }
//            System.out.println("moreTime : " + moreTime);
            total += moreTime;
            for (Class noPre : noPres) {
                for (Integer i : noPre.classes) {
                    map.get(i).pre--;
                }
            }
        }
        return total;
    }
}
