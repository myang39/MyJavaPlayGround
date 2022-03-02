package datastructureAndAlgo.dijkstra;

import java.util.*;

public class PathWithMinimumEffort {
    int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // effort[i][j] = smallest effort from (0, 0) to (i, j)
        int[][] effort = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }
        // base case
        effort[0][0] = 0;

        Queue<State> pq = new PriorityQueue<>((a, b) ->
                a.effort - b.effort);

        pq.offer(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            int curEffort = cur.effort;

            if (x == m - 1 && y == n - 1) {
                return curEffort;
            }

            if (curEffort > effort[x][y]) {
                continue;
            }

            for (int[] nei: adj(heights, x, y)) {
                int nx = nei[0];
                int ny = nei[1];

                int effortNext = Math.max(
                        effort[x][y],
                        Math.abs(heights[x][y] - heights[nx][ny])
                );
                if (effort[nx][ny] > effortNext) {
                    effort[nx][ny] = effortNext;
                    pq.offer(new State(nx, ny, effortNext));
                }
            }
        }
        return -1;
    }

    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    class State {
        int x, y;
        int effort;

        State (int x, int y, int effort) {
            this.x = x;
            this.y = y;
            this.effort = effort;
        }
    }

    List<int[]> adj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;

        List<int[]> neis = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            neis.add(new int[]{nx, ny});
        }
        return neis;
    }


}
