/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private void delete(ListNode node){
        if (node!=null){
            while (node.next!=null && node.next.val == node.val){
                node.next = node.next.next;
            }
            delete(node.next);
        }
    }
    public ListNode deleteDuplicates(ListNode head) {
        delete(head);
        return head;
    }
}