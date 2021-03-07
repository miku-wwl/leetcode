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
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode realHead = new ListNode(-1,head);

        
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = realHead;
        while (node!=null){
            list.add(node);
            node=node.next;
        }
        int sum = 0;
        while (sum+k<=list.size()-1){
            if (sum==0){
                list.get(sum).next = list.get(sum+k);
                list.get(sum+1).next = list.get(sum+k).next;
                for (int i=sum+k;i>sum+1;i--){
                    list.get(i).next = list.get(i-1);
                }
            }else{
                list.get(sum-k+1).next = list.get(sum+k);
                list.get(sum+1).next = list.get(sum+k).next;
                for (int i=sum+k;i>sum+1;i--){
                    list.get(i).next = list.get(i-1);
                }
            }
           
            sum+=k;
        }

        return realHead.next;
        
    }
}