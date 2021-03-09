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
    int minLevel = 9999999;
    private void  find(TreeNode tree, int level){
        if (tree!=null){
            if (tree.left==null && tree.right==null){
                minLevel = Math.min(minLevel,level);
            }
            find(tree.left,level+1);
            find(tree.right,level+1);
        }
    }
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        find(root,1);
        return minLevel;
    }
}