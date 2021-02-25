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
    
    Set<Integer> set = new HashSet<Integer>();
    List<Integer> list = new ArrayList<Integer>();

    private void find(TreeNode tree, int level){
        if (tree !=null){
            if (!set.add(level)){
                find(tree.right,level+1);
                find(tree.left,level+1);
            }else{
                list.add(tree.val);
                find(tree.right,level+1);
                find(tree.left,level+1);
            }
        }
        return ;  
    }

    public List<Integer> rightSideView(TreeNode root) {
        find(root,1);
        return list;
    }
}