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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;

        ListNode realHead = new ListNode();
        realHead.next = head;

        list.add(realHead);

        while(node != null){
            list.add(node);
            node = node.next;
        }

        list.get(left-1).next = list.get(right);
        list.get(left).next = list.get(right).next;
        for (int i = right;i>left;i--){
            list.get(i).next = list.get(i-1);
        }
        

    return realHead.next;
    }
}