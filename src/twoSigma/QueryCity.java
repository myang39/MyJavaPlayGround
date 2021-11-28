package twoSigma;

import java.util.*;

public class QueryCity {
    public static void main(String[] args) {
        List<int[]> cities = new LinkedList<>();
        cities.add(new int[]{0, 0});
        cities.add(new int[]{2, 1});
        cities.add(new int[]{1, 3});
        cities.add(new int[]{3, 3});
        cities.add(new int[]{2, 3});
        cities.add(new int[]{5, 4});
        cities.add(new int[]{0, 4});
        cities.add(new int[]{2, 5});
        cities.add(new int[]{1, 4});
        cities.add(new int[]{3, 7});
        cities.add(new int[]{2, 10});
        cities.add(new int[]{7, 6});

        List<int[]> queries = new LinkedList<>();
        queries.add(new int[]{0, 0});
        queries.add(new int[]{1, 1});
        queries.add(new int[]{-1, -1});
        queries.add(new int[]{2, 8});
        queries.add(new int[]{3, 6});
        queries.add(new int[]{2, 0});
        queries.add(new int[]{10, 1});
        queries.add(new int[]{5, 6});

        System.out.println(Arrays.deepToString(queryCity(cities, queries).toArray()));
    }

    public static List<int[]> queryCity(List<int[]> cities, List<int[]> queries) {
        Map<Integer, TreeSet<Integer>> mapX = new HashMap<>(); // have same x
        Map<Integer, TreeSet<Integer>> mapY = new HashMap<>(); // have same y
        for (int[] city : cities) {
//            System.out.println("city[0] : " + city[0] + " city[1] : " + city[1]);
            mapX.putIfAbsent(city[0], new TreeSet<>());
            mapX.get(city[0]).add(city[1]);

            mapY.putIfAbsent(city[1], new TreeSet<>());
            mapY.get(city[1]).add(city[0]);
        }

        List<int[]> rest = new ArrayList<>();
        for (int[] query : queries) {
            TreeSet<Integer> colSet = mapX.get(query[0]);
            int[] coor = null;
            if (colSet != null) {
//                System.out.println("colset: " + colSet);
                coor = new int[2];
                Integer floor;
                Integer ceiling;
                coor[0] = query[0];
                floor = colSet.floor(query[1]);
//                System.out.println("floor :" + floor);

                ceiling = colSet.ceiling(query[1]);
//                System.out.println("ceiling :" + ceiling);
                if (floor != null && ceiling != null) {
                    coor[1] = query[1] - floor <= ceiling - query[1] ? floor : ceiling;
                } else if (floor != null) {
                    coor[1] = floor;
                } else {
                    coor[1] = ceiling;
                }
            }

            TreeSet<Integer> rowSet = mapY.get(query[1]);
            int[] coorII = null;
            if (rowSet != null) {
                coorII = new int[2];
                Integer floor;
                Integer ceiling;
                coorII[1] = query[1];
                floor = rowSet.floor(query[0]);
//                System.out.println("floor :" + floor);

                ceiling = rowSet.ceiling(query[0]);
//                System.out.println("ceiling :" + ceiling);
                if (floor != null && ceiling != null) {
                    coorII[0] = query[0] - floor <= ceiling - query[0] ? floor : ceiling;
                } else if (floor != null) {
                    coorII[0] = floor;
                } else {
                    coorII[0] = ceiling;
                }
            }

            int[] ans;
//            System.out.println(Arrays.toString(query) + " " + Arrays.toString(coor) + " " + Arrays.toString(coorII));
            if (coor != null && coorII != null) {
                ans = Math.abs(coor[1] - query[1]) <= Math.abs(coorII[0] - query[0]) ? coor : coorII;
            } else if (coor != null) {
                ans = coor;
            } else if (coorII != null) {
                ans = coorII;
            } else {
                ans = null;
            }
            rest.add(ans);
        }

        return rest;
    }



}
