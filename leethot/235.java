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
    TreeNode answer;
    boolean lock = true;
    Set<TreeNode> set = new HashSet<TreeNode>();
    private boolean findleft(TreeNode tree, TreeNode left){
        if (tree!=null){
            if (tree == left || findleft(tree.left,left) || findleft(tree.right,left))  {
                set.add(tree);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean findright(TreeNode tree,TreeNode right){
        if (tree!=null){
            if (tree == right ||findright(tree.left,right) || findright(tree.right,right))  {
                if (lock==true && set.contains(tree)){
                    answer = tree;
                    lock = false;
                }
                return true;
            }
            return false;

        }
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<TreeNode> set = new HashSet<TreeNode>();
        findleft(root,p);
        findright(root,q);
        return answer;
    }
}