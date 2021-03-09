/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode current = node;
        while (current.next.next!=null){
            current.val = current.next.val;
            current = current.next;
        }
        current.val = current.next.val;
        current.next = null;
        return ;
    }
}