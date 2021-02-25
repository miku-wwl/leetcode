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
    public ListNode sortList(ListNode head) {
       
        int count = 0;
        ListNode listnode = head;
        while (listnode !=null){
            count++;
            listnode=listnode.next;
        }

        int[] num = new int[count];

        listnode = head;

        count=-1;
        while (listnode !=null){
            count++;
            num[count]=listnode.val;
            listnode=listnode.next;
        }
        


        Arrays.sort(num);
        
        listnode = head;
        for (int i=0;i<=count; i++){
            listnode.val = num[i];
            listnode = listnode.next;
        }
        return head;
    }
}