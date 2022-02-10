package square;

public class lc114_FlatternBinayTreeToList {
    // Definition for a binary tree node.
    public class TreeNode {
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

  }

  public static void flatten(TreeNode root) {
    dfs(root);
  }

  public static TreeNode dfs(TreeNode root) {
      // null or leave
      if (root == null || root.left == null && root.right == null) {
        return root;
      }
      TreeNode leftTail = dfs(root.left);
      TreeNode rightTail = dfs(root.right);

      TreeNode left = root.left;
      TreeNode right = root.right;

      if (left != null) {
        root.right = left;
        leftTail.right = right;
      }

      root.left = null;

      return right == null ? leftTail : rightTail;
  }

}
