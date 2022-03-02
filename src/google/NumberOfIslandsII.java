package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsII {
    public static void main(String[] args) {
        NumberOfIslandsII solution = new NumberOfIslandsII();
        int m = 3;
        int n = 3;
        int[][] positions = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        System.out.println(solution.numIslands2(m, n, positions));
    }

    class UnionFind {
        int nc; // number of components
        int[] p;

        public UnionFind(int n) {
            p = new int[n];
            nc = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public UnionFind(int n, int value) {
            p = new int[n];
            nc = 0; // only counts islands as components
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        // Find root with path compression
        public int find(int a) {
            return p[a] == a? a : (p[a] = find(p[a]));
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                nc--;
                p[pa] = pb;
            }
        }
    }

    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    /**
     For each position,

     */


    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n, 0);
        List<Integer> rest = new ArrayList<>();
        int[][] map = new int[m][n];
        for (int[] p : positions) {
            debugPrint(map, uf);

            int x = p[0];
            int y = p[1];
            if (map[x][y] == 1) {
                rest.add(uf.nc);
                continue;
            }
            map[x][y] = 1;
            uf.nc++;
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                int xyIndex = x * n + y;
                int nxyIndex = nx * n + ny;
                uf.union(xyIndex, nxyIndex);
            }
            rest.add(uf.nc);
        }
        debugPrint(map, uf);

        return rest;
    }

    private void debugPrint(int[][] map, UnionFind uf) {
        int m = map.length;
        int n = map[0].length;
        System.out.println("------------");
        System.out.println(Arrays.deepToString(map).replace("],", "]\n")
                .replace("[[", " [")
                .replace("]]", "]"));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    System.out.print(0);
                }else {
                    System.out.print(uf.find(i * n + j));
                }
            }
            System.out.println();
        }
        System.out.println("# of components: " + uf.nc);
    }
}
