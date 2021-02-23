

# 1

```
public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] == target - nums[i]) {
                return new int[] { i, j };
            }
        }
    }
    throw new IllegalArgumentException("No two sum solution");
}
```

# 2

```
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode(0);
        ListNode curr=l3;
        int carry = 0;
        
        while (l1!=null || l2!=null){
            int x = (l1!=null)?l1.val:0;
            int y = (l2!=null)?l2.val:0;
            int sum = carry + x + y;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr=curr.next;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        if (carry>0){
            curr.next= new ListNode(carry);
        }
        return l3.next;    
    }
}


```

# 3

```
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
```

# 4

```
class Solution {
    public double find_k(int[] nums1,int[] nums2,int i,int j,int k){
        if (i>=nums1.length) return nums2[j+k-1];
        if (j>=nums2.length) return nums1[i+k-1];
        if (k==1) {
            return Math.min(nums1[i],nums2[j]);
        }     
        int mid1=(i+k/2-1<nums1.length)?nums1[i+k/2-1]:Integer.MAX_VALUE;
        int mid2=(j+k/2-1<nums2.length)?nums2[j+k/2-1]:Integer.MAX_VALUE;
        
        if (mid1<mid2){
            return find_k(nums1,nums2,i+k/2,j,k-k/2);
        }else{
            return find_k(nums1,nums2,i,j+k/2,k-k/2);
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int left=(m+n+1)/2;
        int right=(m+n+2)/2;
        return (find_k(nums1,nums2,0,0,left)+find_k(nums1,nums2,0,0,right))/2;
    }
}
```

# 5

```
class Solution {
    public String longestPalindrome(String s) {
       boolean[][] a = new boolean [1001][1001];
       for (int i=0;i<s.length();i++) {
           a[i][1]=true;
       }
       for (int i=0;i<s.length()-1;i++) {
           a[i][2]=s.charAt(i)==s.charAt(i+1);
       }
       for (int j=3;j<=s.length();j++){
           for (int i=0;i+j-1<s.length();i++){
               a[i][j]=(a[i+1][j-2] && s.charAt(i)==s.charAt(i+j-1));
           }
       }
     for (int j=s.length();j>0;j--){
         for (int i=0;i+j-1<s.length();i++){
             if (a[i][j]){
                 return s.substring(i,i+j);
             }
         }
     }  
        return s.substring(0,1);
    }
}
```

# 10

```
class Solution {
    public boolean isMatch(String text, String pattern) {
    boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];    
        
    dp[text.length()][pattern.length()] = true;
        
     for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
           return dp[0][0];   
        
    }
}
```

# 11

~~~java
class Solution {
    public int maxArea(int[] height) {
        int maxarea=0;
        for(int i=0;i<height.length;i++)
            for (int j=i+1;j<height.length;j++){
                maxarea=Math.max(maxarea,Math.min(height[i],height[j])*(j-i));
            }
        return maxarea;
    }
}
~~~

# 15

~~~
int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
~~~

# 17

~~~
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}

~~~

# 19

~~~
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
~~~

# 20

~~~
class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
~~~

# 21

```
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
```





