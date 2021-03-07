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
    List<Integer> list = new ArrayList<Integer>();
    private void treeSearch(TreeNode tree){
        if (tree!=null){
            list.add(tree.val);
            treeSearch(tree.left);
            treeSearch(tree.right);
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        treeSearch(root);
        return list;
    }
}