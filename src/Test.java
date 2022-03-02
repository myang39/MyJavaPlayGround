import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Test {
    public static void main(String[] args) {
//        Test t = new Test();
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        System.out.println(t.maxPathSum(root));

        int n = 8;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lists.add(new String(board[i]));
        }
        System.out.println(lists.toString().replace(",", "\n"));
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }
    }
    int globalmax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
//        System.out.println("globalmax "  + globalmax);
//        int dfs = dfs(root);
//        System.out.println("dfs " + dfs);
//        System.out.println("globalmax " + globalmax);
//        globalmax = Math.max(globalmax, dfs);
//        System.out.println("globalmax " + globalmax);
        globalmax = Math.max(globalmax, dfs(root));
        return globalmax;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        globalmax = Math.max(globalmax, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
