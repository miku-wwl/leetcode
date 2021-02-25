/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    Set<ListNode> hash = new HashSet<ListNode>();
    ListNode ref;

    private void find(ListNode node){
        if (node != null){
            hash.add(node);
            find(node.next);
        }
    }
     private void find2(ListNode node){
        if (node != null){
            if (!hash.add(node)){
                ref = node;
                return ;
            }else{
                find2(node.next);
            }
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        find(headA);
        find2(headB);
        return ref;
    }
}