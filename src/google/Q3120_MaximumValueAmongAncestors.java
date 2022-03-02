package google;

import java.util.HashMap;
import java.util.Map;

public class Q3120_MaximumValueAmongAncestors {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(6);

        Q3120_MaximumValueAmongAncestors s = new Q3120_MaximumValueAmongAncestors();
        Map<Integer, Integer> map = new HashMap<>();
        s.maximumValueAmongAncestors(root, map, Integer.MIN_VALUE);
        System.out.println();
        System.out.println("answer:");
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }

    int count = 0;

    void printIndent(int count) {
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.print("   ");
        }
    }
    void maximumValueAmongAncestors(TreeNode root, Map<Integer, Integer> map, int max) {
//        printIndent(count++);
        if (root == null) {
//            System.out.print("x");
//            count--;
            return;
        }
//        System.out.print(root.val);
        max = Math.max(root.val, max);
        if (root.left == null && root.right == null) {
            map.put(root.val, max);
//            count--;
            return;
        }
        maximumValueAmongAncestors(root.left, map, max);
        maximumValueAmongAncestors(root.right, map, max);
//        count--;
    }

    // Might not be binary tree
    void maximumValueAmongAncestors() {

    }

}
