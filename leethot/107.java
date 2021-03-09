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
    int maxLevel = 0;
    Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(TreeNode tree, int level){
        if (tree !=null){
            maxLevel = Math.max(maxLevel,level);
            if (map.containsKey(level)){
                map.get(level).add(tree.val);
                find(tree.left,level+1);
                find(tree.right,level+1);
            }else{
                map.put(level,new ArrayList<Integer>());
                map.get(level).add(tree.val);

                System.out.println(map.get(level));

                find(tree.left,level+1);
                find(tree.right,level+1);
            }
            
        }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        find(root,1);
        for (int i=1;i<=maxLevel;i++){
            lists.add(map.get(i));
        }
        Collections.reverse(lists);
        return lists;
    }
}