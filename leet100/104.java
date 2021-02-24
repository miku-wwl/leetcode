/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxn = 0;
    private void find(TreeNode tree, int K){
        if (K>maxn) maxn=K;
        if (tree.left!=null){find(tree.left,K+1);}
        if (tree.right!=null){find(tree.right,K+1);}
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        find(root,1);
        return maxn;
    }
}