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
    int[] value = new int[1000000];
    int valueCount = -1;
    private void find(TreeNode tree){
        if (tree!=null){
            if (tree.left!=null) find(tree.left);
            valueCount+=1;
            value[valueCount]=tree.val;
            if (tree.right!=null) find(tree.right);
        }
    }
    public boolean isValidBST(TreeNode root) {
        find(root);
        for (int i=1;i<=valueCount;i++){
            if (value[i]<=value[i-1]) return false;
        }
        return true;
    }
}