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
    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> record = new HashSet<Integer>();
        ListNode node = head;
        while (node!=null){
            if (!set.add(node.val)){
                record.add(node.val);
            }
            node = node.next;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        node =head;
        while (node!=null){
            if (!record.contains(node.val)){
                list.add(node);
            }
            node = node.next;
        }
        for (int i=0;i<list.size()-1;i++){
            list.get(i).next = list.get(i+1);
        }

        if (list.size()==0){
            return null;
        }
        list.get(list.size()-1).next = null;
        return list.get(0);

    }
}