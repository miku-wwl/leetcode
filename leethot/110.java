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
    boolean ifBalanced = true;
    private int find(TreeNode tree){
        if (tree==null) return 0;
        int l = find(tree.left);
        int r = find(tree.right);
        
        if (Math.abs(l-r)>1) ifBalanced = false;
        return Math.max(l,r)+1;

    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        find(root);
        return ifBalanced;
    }
}