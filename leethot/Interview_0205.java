/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<ListNode> list1 = new ArrayList<ListNode>();
        List<ListNode> list2 = new ArrayList<ListNode>();
        List<ListNode> temp = new ArrayList<ListNode>();
        while (l1!=null){
            list1.add(l1);
            l1=l1.next;
        }
        while(l2!=null){
            list2.add(l2);
            l2=l2.next;
        }
        if (list1.size()<list2.size()){
            temp = list1;
            list1 = list2;
            list2 = temp;
        }

        list1.add(new ListNode(0));
        for (int i=0;i<list2.size();i++){
            list1.get(i).val += list2.get(i).val;
            if (list1.get(i).val>=10){
                list1.get(i).val-=10;
                list1.get(i+1).val+=1;
            } 
        }
        for (int i=0;i<list1.size()-1;i++){
            if (list1.get(i).val>=10){
                list1.get(i).val-=10;
                list1.get(i+1).val+=1;
            }
        }
        
        if (list1.get(list1.size()-1).val !=0){
            list1.get(list1.size()-2).next = list1.get(list1.size()-1);
        }

        return list1.get(0);    


    }
}