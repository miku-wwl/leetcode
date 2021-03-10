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
        if( l1.val==0) return l2;
        if( l2.val==0) return l1;

        
        List<ListNode> list1 =new ArrayList<ListNode>();
        List<ListNode> list2 =new ArrayList<ListNode>();
    
        ListNode node = l1;
        while (node!=null){
            list1.add(node);
            node=node.next;
        }
        node = l2;
        while (node!=null){
            list2.add(node);
            node=node.next;
        }


        if (list1.size()<list2.size()){
            List<ListNode> temp;
            temp = list1;
            list1 = list2;
            list2 = temp;
        }

        Collections.reverse(list1);
        Collections.reverse(list2);

        list1.add(new ListNode(0));

        for (int i = 0;i<list2.size();i++){
            list1.get(i).val+=list2.get(i).val;
            if (list1.get(i).val>=10){
                list1.get(i).val %= 10;
                list1.get(i+1).val++;
            }
        }

        for (int i = 0;i<list1.size()-1;i++)
            if (list1.get(i).val>=10){
                list1.get(i).val %= 10;
                list1.get(i+1).val++;
            }

        if (list1.get(list1.size()-1).val>0){
            for (int i =list1.size()-1;i>=1;i--){
                list1.get(i).next = list1.get(i-1);
            }
            list1.get(0).next =null;
            return list1.get(list1.size()-1);
        }

        for (int i =list1.size()-2;i>=1;i--){
            list1.get(i).next = list1.get(i-1);
        }
        list1.get(0).next =null;
        return list1.get(list1.size()-2);

    
    }
}