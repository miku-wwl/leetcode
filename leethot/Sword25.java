/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<ListNode> list = new ArrayList<ListNode>();
        while (l1!=null || l2!=null){
            if (l2==null){
                list.add(l1);
                l1=l1.next;
                continue;
            }
            if (l1==null){
                list.add(l2);
                l2=l2.next;
                continue;
            }
            if (l1.val<=l2.val){
                list.add(l1);
                l1=l1.next;
                continue;
            }
            if (l1.val>l2.val){
                list.add(l2);
                l2=l2.next;
                continue;
            }

        }
        if (list.size()==0) return null;
        
        for (int i=0;i<list.size()-1;i++){
            list.get(i).next = list.get(i+1);
        }
        list.get(list.size()-1).next = null;
        return list.get(0);
    }
}