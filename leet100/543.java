/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int length = -1;
    private int find(TreeNode tree){
        if (tree == null){
            return 0;
        }else{
            int x = find(tree.left);
            int y = find(tree.right);
            length = Math.max(length,x+y+1);
            return Math.max(x+1,y+1);
        }
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        find(root);       
        return length-1;
    }
}