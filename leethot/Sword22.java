/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        List<ListNode> list = new ArrayList<ListNode>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }
        return list.get(list.size()-k);
    }
}