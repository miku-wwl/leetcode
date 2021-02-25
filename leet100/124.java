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
    int maxn=-99999;
    private int find(TreeNode tree){
        int leftmax=0;
        int rightmax=0;
        if (tree!=null){
            leftmax=0;
            rightmax=0;
            if (tree.left!=null) leftmax = Math.max(0,find(tree.left));
            if (tree.right!=null) rightmax = Math.max(0,find(tree.right));



            maxn = Math.max(tree.val+leftmax,maxn);
            maxn = Math.max(tree.val+rightmax,maxn);
            maxn = Math.max(tree.val+leftmax+rightmax,maxn);

            return Math.max(tree.val+leftmax,tree.val+rightmax);
        }
        return 0;
    }

    public int maxPathSum(TreeNode root) {
        find(root);

        return maxn;
    }
}