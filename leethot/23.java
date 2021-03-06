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
    List<Integer> ansList = new ArrayList<Integer>();

    private void find(ListNode Current){
        if (Current !=null){
            ansList.add(Current.val);
            find(Current.next);
            return;
        }
        return;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        
        for (ListNode list : lists){
            find(list);
        }
        Collections.sort(ansList);
        ListNode list = new ListNode();
        ListNode head = list;
        for (Integer i : ansList){
            list.next = new ListNode(i);
            list = list.next;
        }
        return head.next;
    }
}