package datastructure;

// base on https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/datastructures/unionfind/UnionFind.java
/**
 * A pretty completed UnionFind class
 * including constructor, find method and union method
 * also including optional fields:
 * size for # of elements in the union find,
 * sz[] for size of each of the component
 * numComponents tacks the number of components in the union find,
 * connected() tells whether two elements belongs to the same component
 */
public class UnionFind {
    // The number of elements in this union find
    private int size;

    // Used to track the size of each of the component
    private int[] sz;

    // id[i] points to the parent of i, if id[i] = i then i is a root node
    private int[] id;

    // Tracks the number of components in the union find
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size <= 0 is not allowed");
        }

        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i; // Link to itself (self root)
            sz[i] = 1; // Each component is originally of size one
        }
    }

    // Find which component 'p' belongs to, takes amortized constant time.
    public int find(int p) {
        // Find the root of the component
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // Path compression leading back to the root
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    // There is an alternative recursive formula for the find method
//    public int find(int p) {
//        if (p == id[p]) {
//            return p;
//        }
//        return id[p] = find(id[p]);

    // Return whether of not the elements 'p' and 'q' are in the same component.
    public boolean connected (int p, int q) {
        return find(p) == find(q);
    }

    // return the size of the components 'p' belongs to
    public int componentSize(int p) {
        return sz[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int root1 = find(p);
        int root2 = find(q);

        // Merge smaller component into the larger one
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
            sz[root1] = 0;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
            sz[root2] = 0;
        }

        numComponents--;
    }
}
