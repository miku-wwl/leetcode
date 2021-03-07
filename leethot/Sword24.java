/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;

        while (node != null) {
            list.add(node);
            node = node.next;
        }

        for (int i = list.size()-1;i>=1;i--){
            list.get(i).next = list.get(i-1);
            list.get(0).next = null;
        }
        return list.get(list.size()-1);
    }
}