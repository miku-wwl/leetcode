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
    public boolean isPalindrome(ListNode head) {
        ListNode list = head;
        int count=0;
        while (list!=null){
            count++;
            list=list.next;
        }
        int[] x = new int[count];
        int[] y = new int [count];

        list =head;
        int search = -1;

        while (list!=null){
            search++;
            x[search]=list.val;
            y[count-1-search]=list.val;
            list=list.next;
        }

        for (int i=0;i<count;i++)
            if (x[i]!=y[i]) return false;
        
        return true;



    }
}