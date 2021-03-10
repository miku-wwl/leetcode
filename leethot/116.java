/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root ==null) return null;

        Queue<Node> q = new LinkedList<Node>();
        Queue<Integer> level = new LinkedList<Integer>();
        q.add(root);
        level.add(1);
        while (!q.isEmpty()){
            Node current = q.poll();
            int currentLevel = level.poll();
            if (q.size()==0){
                current.next=null;
            }else{
                if (currentLevel<level.peek()){
                    current.next=null;
                }else{
                    current.next = q.peek();
                }
            }
            if (current.left!=null){
                q.add(current.left);
                level.add(currentLevel+1);
            }
            if (current.right!=null){
                q.add(current.right);
                level.add(currentLevel+1);
            }
        }
    return root;


    }
}