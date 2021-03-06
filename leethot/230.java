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
    int K;
    int Count = 0;
    int ans;

    private void find(TreeNode tree){
        if (tree !=null){
            find(tree.left);
            Count+=1;
            if (Count == K) ans = tree.val;
            find(tree.right);
        }
        return;
    }

    public int kthSmallest(TreeNode root, int k) {
        K = k;
        find(root);
        return ans;

    }
}