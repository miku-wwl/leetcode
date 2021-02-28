/*
1.
Set<Integer> set = new  HashSet<>();
set.add();
set.remove();
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        Set<Character> set=new HashSet<>();
        int ans=0;
        int i=0;
        int j=0;
        while (i<n && j<n){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                ans = Math.max(ans,j-i+1);
                j++;
            }else{
                set.remove(s.charAt(i));
                i++;
            }
            
        }
        return ans;
    }
}

/*
2.
Set<Integer> set = new HashSet<Integer>();
set.contains();
foreach:
for (int i : set){

}
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

/*
3.
Iterator<Integer> it = set.iterator();
it.next();
it.hasNext();
*/
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        


        for(int i: nums){
            if (set.contains(i)){
                set.remove(i);
            }else{
                set.add(i);
            }
        }
        Iterator<Integer> it = set.iterator();
        return it.next();
        
    }
}

/*
4.
Set<ListNode> hash = new HashSet<ListNode>();

*/

public class Solution {
    Set<ListNode> hash = new HashSet<ListNode>();
    ListNode ref;

    private void find(ListNode node){
        if (node != null){
            hash.add(node);
            find(node.next);
        }
    }
     private void find2(ListNode node){
        if (node != null){
            if (!hash.add(node)){
                ref = node;
                return ;
            }else{
                find2(node.next);
            }
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        find(headA);
        find2(headB);
        return ref;
    }
}