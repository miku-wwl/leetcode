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
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next == null) return head;
        
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode realHead = new ListNode(-1,head);

        ListNode position = realHead;
        while (position!=null){
            list.add(position);
            position= position.next;
        }

        for (int i=1;i<=(list.size()-1)/2;i++){
            list.get(2*i-2).next = list.get(2*i);
            list.get(2*i-1).next = list.get(2*i).next;
            list.get(2*i).next = list.get(2*i-1);

            list.clear();
            position = realHead;
            while (position!=null){
                list.add(position);
                position=position.next;
            }           
        }
        return realHead.next;
    }
}