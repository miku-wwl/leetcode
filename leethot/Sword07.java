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
    private TreeNode Create(int[] preorder,int[] inorder){
        if (preorder.length>0){
            TreeNode node = new TreeNode(preorder[0]);
            int index=0;
            
            for (int i=0;i<inorder.length;i++){
                if (inorder[i]==preorder[0]){
                    index = i;
                }
            }

            if (index>0){
                int[] p = new int[index];
                int[] q = new int[index];
                for (int i=0;i<index;i++){
                    p[i]=preorder[i+1];
                    q[i]=inorder[i];
                    
                }
                node.left = Create(p,q);
            }
            if (preorder.length-1-index>0){
                int[] p = new int[preorder.length-1-index];
                int[] q = new int[preorder.length-1-index];
                for (int i=0;i<preorder.length-1-index;i++){
                    p[i]= preorder[i+index+1];
                    q[i]= inorder[i+index+1]; 
                }
                node.right = Create(p,q);
            }


            return node;
        }
        return null;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode tree = Create(preorder,inorder);
        return tree;
    }
}