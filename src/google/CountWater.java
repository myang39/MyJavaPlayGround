package google;

public class CountWater {
    public static void main(String[] args) {

    }


    static class UnionFind {
        int[] p;
        public UnionFind(int n) {
            p = new int[n];
            for (int i = 0; i < n; ++i) {
                p[i] = i;
            }
        }

        public int get(int x) {
            return p[x] == x ? x : (p[x] = get(p[x]));
        }

        public void union(int x, int y) {
            int a = get(x);
            int b = get(y);
            p[a] = p[b];
        }
    }
}
