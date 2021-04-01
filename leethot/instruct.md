

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

# 6 字符串繁琐操作

~~~
class Solution {
    String str;
    int n;
    int strLength;
    String[] strs;
    private void deploy(int index, int local, int direction){
        if (strLength == index) return;

        int newDirection = direction;
        if (local == 0 ) newDirection = 1;
        if (local == n-1) newDirection = -1;

        strs[local] += str.charAt(index);
        deploy(index+1,local+newDirection,newDirection); 
        return;
    }
    
    public String convert(String s, int numRows) {
        if (numRows==1) {
            return s;
        }

        strs = new String[numRows];
        for (int i=0;i<numRows;i++){
            strs[i] =  "";
        }

        str = s;
        n = numRows;
        strLength = s.length();
        deploy(0,0,1);


        for (int i=1;i<numRows;i++){
            strs[0] += strs[i];
        }
        return strs[0];
    }
}
~~~





# 7 字符串各种操作 数字字符串互转

~~~
class Solution {
    public int reverse(int x) {
        int mark=1;
        Long longX = new Long(x);
        if  (longX<0){
            mark=-1;
            longX=-longX;
        }

        String str = longX.toString();
        str = new StringBuilder(str).reverse().toString();

        Long longint = Long.parseLong(str);

        if (longint*mark<-Math.pow(2,31) || longint*mark>Math.pow(2,31)-1) return 0;
        
        return mark*Integer.parseInt(str);
    }
}
~~~



# 8 有限状态自动机 DFA

~~~
class Solution {
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}

~~~



# 9 String 相等 str.equals(str2)

~~~
class Solution {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        String str = new Integer(x).toString();
        String str2 = new StringBuilder(str).reverse().toString();
        if (str.equals(str2)) return true;
        return false;
    }
}
~~~



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

# 13

~~~
class Solution {
    public int romanToInt(String s) {
        boolean t = true;
        while(t){
            boolean f;
            f=false;
            for (int i=0;i+1<s.length();i++){
                if (s.charAt(i) =='I' && s.charAt(i+1) =='V'){
                    s = s.substring(0,i) + "IIII"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='I' && s.charAt(i+1) =='X'){
                    s = s.substring(0,i) + "VIIII"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='X' && s.charAt(i+1) =='L'){
                    s = s.substring(0,i) + "XXXX"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='X' && s.charAt(i+1) =='C'){
                    s = s.substring(0,i) + "LXXXX"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='C' && s.charAt(i+1) =='D'){
                    s = s.substring(0,i) + "CCCC"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='C' && s.charAt(i+1) =='M'){
                    s = s.substring(0,i) + "DCCCC"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
            }
            t=f;
        }
    int ans = 0;
    for (int i=0;i<s.length();i++){
        if (s.charAt(i) == 'I'){
            ans+=1;
        }
        if (s.charAt(i) == 'V'){
            ans+=5;
        }
        if (s.charAt(i) == 'X'){
            ans+=10;
        }
        if (s.charAt(i) == 'L'){
            ans+=50;
        }
        if (s.charAt(i) == 'C'){
            ans+=100;
        }
        if (s.charAt(i) == 'D'){
            ans+=500;
        }
        if (s.charAt(i) == 'M'){
            ans+=1000;
        }
    }
    return ans;
    }
}


// IV = IIII
// IX = VIIII
// XL = XXXX
// XC = LXXXX
// CD = CCCC
// CM = DCCCC

~~~



# 14

~~~
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";

        int miniLength=9999;
        for (String str : strs){
            miniLength = Math.min(miniLength,str.length());
        }

        String answer = "";
        boolean check = true;
        for (int i=0;i<miniLength && check;i++){
            char c = strs[0].charAt(i);
            for (String str : strs){
                char strC = str.charAt(i);
                if (c!=strC){
                    check = false;
                    break;
                }
            }
            if (check){
                answer += c;
            }
        }

        return answer;
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

# 16 排序+双指针，和15类似

~~~
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }
}
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

# 18  List排序  Collections.sort(list);

~~~
class Solution {

    Set<List<Integer>> set = new HashSet<List<Integer>>();
    List<List<Integer>> answer = new ArrayList<List<Integer>>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        for (int i=0;i<nums.length;i++)
            for (int j=i+1;j<nums.length;j++)
                for (int k=j+1;k<nums.length;k++)
                    for (int l=k+1;l<nums.length;l++){
                        if (nums[i]+nums[j]+nums[k]+nums[l] == target){
                            List<Integer> list = new ArrayList<Integer>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[l]);
                            Collections.sort(list);
                            
                            set.add(list);
                        }
                    }
        for (List<Integer> i: set){
            answer.add(i);
        }
        return answer;
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

# 22

```
class Solution {
    List<String> strs = new ArrayList<String>();

    private void find(int leftNumber,int rightNumber, String str){
        String string ="";
        if (leftNumber == rightNumber && 0 == leftNumber){
            strs.add(str);
            return;
        }
        if (leftNumber == rightNumber){
            find(leftNumber-1,rightNumber,str+"(");
            return;
        }
        if (leftNumber == 0){
            find(leftNumber,rightNumber-1,str+")" );
            return;
        }
        find(leftNumber-1,rightNumber,str+"(");
        find(leftNumber,rightNumber-1,str+")" );
        return;
    }

    
    public List<String> generateParenthesis(int n) {
        int leftNumber=n;
        int rightNumber=n;
        String str = "";
        find(leftNumber,rightNumber,str);
        return strs;
    }
}
```

# 23

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
```

# 24 链表转线性表

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
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next == null) return head;
        
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode realHead = new ListNode(-1,head);

        ListNode position = realHead;
        while (position!=null){
            list.add(position);
            position= position.next;
        }

        for (int i=1;i<=(list.size()-1)/2;i++){
            list.get(2*i-2).next = list.get(2*i);
            list.get(2*i-1).next = list.get(2*i).next;
            list.get(2*i).next = list.get(2*i-1);

            list.clear();
            position = realHead;
            while (position!=null){
                list.add(position);
                position=position.next;
            }           
        }
        return realHead.next;
    }
}
~~~



# 25 链表转线性表

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
            list.get(sum).next = list.get(sum+k);
            list.get(sum+1).next = list.get(sum+k).next;
            for (int i=sum+k;i>sum+1;i--){
                list.get(i).next = list.get(i-1);
            }

            list.clear();
            node = realHead;
            while (node!=null){
                list.add(node);
                node=node.next;
            }
            
            sum+=k;
        }

        return realHead.next;
        
    }
}
~~~

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
~~~

# 26

~~~
class Solution {
    public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[i]) {
            i++;
            nums[i] = nums[j];
        }
    }
    return i + 1;
}
}

~~~



# 28

~~~
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        int answer = -1;
        for (int i=0;i<=haystack.length()-needle.length();i++){
            if (needle.equals(haystack.substring(i,i+needle.length()))){
                answer = i;
                break;
            }
        }
        return answer;
    }
}
~~~





# 31 单调栈  （不要用变量指代存栈顶元素）

~~~
class Solution {
    public void nextPermutation(int[] nums) {
        boolean check = true;
        int n = nums.length;
        int point=0;
        for (int i=0;i<n-1;i++){
            if (nums[i]<nums[i+1]){
               check = false;
               break;
            }
        }
        if (check) {
            Arrays.sort(nums);
            return;
        }


        Stack<Integer> stack = new Stack<Integer>();
        stack.push(n-1);

        for (int i=n-2;i>=0;i--){

            if (nums[i]>=nums[stack.peek()]){
                stack.push(i);
                continue;
            }

            int store = 0;
            while (!stack.isEmpty() && nums[i]<nums[stack.peek()]){
               store = stack.peek();
               stack.pop();
            }

            int temp = nums[i];
            nums[i] = nums[store];
            nums[store] = temp;
            point = i;
            break;
        }

        for (int i=point+1;i<n-1;i++)
            for (int j=i+1;j<n;j++){
                if (nums[i]>nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
    }
}
~~~



# 32

```
public class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
```

# 33

```
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

# 34

```
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } 
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
```



# 36 18个哈希表

~~~
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<Character>();
        
        for (int i=0;i<9;i++)
            {
                set.clear();
                for (int j=0;j<9;j++){
                    if (board[i][j]!='.' && !set.add(board[i][j])) {
                        return false;
                    }
                }
            }
        for (int j=0;j<9;j++){
            set.clear();
            for (int i=0;i<9;i++){
                if (board[i][j]!='.' && !set.add(board[i][j])) {
                    return false;
                }
            }
        }

        for (int i=0;i<9;i=i+3)
            for (int j=0;j<9;j=j+3){
                set.clear();
                for (int k=i;k<=i+2;k++)
                    for (int l=j;l<=j+2;l++){
                        if (board[k][l]!='.' && !set.add(board[k][l]))
                            return false;
                    }

            }
        return true;          


        // (0,0) (2,2)
        // (3,0) (5,2)
        // (6,0) (8,2)

        // (0,3) (2,5)
        // (3,3) (5,5)
        // (6,3) (8,5)

        // (0,6) (2,8)
        // (3,6) (5,8)
        // (6,6) (8,8)



    }
}
~~~



# 39

```
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
```

# 40 搜索 List查重

~~~
class Solution {
    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }
}

~~~



# 41

```
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
```

# 42

```
class Solution {
    public int trap(int[] height) {
        int n=height.length;
        if (n==0) return 0;
        int[] leftwall = new int[n];
        int[] rightwall = new int[n];
      
        leftwall[0]=height[0];
        rightwall[n-1]=height[n-1];
        for (int i=1;i<n-1;i++)  leftwall[i] = Math.max(height[i],leftwall[i-1]);
        for (int i=n-2;i>0;i--)  rightwall[i] = Math.max(height[i],rightwall[i+1]);

        for (int i=1;i<n-1;i++)  rightwall[i] = Math.min(leftwall[i],rightwall[i]);
        int ans=0;
        for (int i=1;i<n-1;i++)  ans+=rightwall[i]-height[i];
        return ans;     
    }
}
```

# 43 高精度乘法

~~~
class Solution {
    public String multiply(String num1, String num2) {
        String temp;
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.length()<num2.length()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int[] a = new int[num1.length()];
        int[] b = new int[num2.length()];
        int[] c = new int[num1.length()*2+1];

        for (int i=0;i<num1.length();i++){
            a[i]=num1.charAt(num1.length()-1-i)-48;
        }
        for (int i=0;i<num2.length();i++){
            b[i]=num2.charAt(num2.length()-1-i)-48;
        }

        for (int j=0;j<b.length;j++)
            for(int i=0;i<a.length;i++){
                c[i+j] += a[i]*b[j];
                c[i+j+1] += c[i+j] /10;
                c[i+j] %=10;

            }
        for (int i=0;i<c.length-1;i++){
            c[i+1] += c[i] /10;
            c[i] %=10;
        }
        String str = "";
        boolean t = true;
        for (int i=c.length-1;i>=0;i--){
            if (c[i]==0 && t==true){
                continue;
            }else{
                t=false;
                str+=c[i];
            }
        }

        return str;

    }
}
~~~

# 44  复杂字符串匹配 正则表达式

~~~
class Solution {
     public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
~~~





# 45

```
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n+1];

        f[0]=0;
        for (int i=1;i<n;i++) f[i]=n*2;


        for (int i=0;i<n;i++){
            for (int j=1;j<=nums[i] && i+j<n;j++){
                f[i+j] = Math.min(f[i+j],f[i]+1);
            }
        }
        return f[n-1];
    }
}
```

# 46

```
class Solution {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(int[] nums, List<Integer> list ){
        if (list.size() == nums.length) {
            lists.add(list);
            return;
        }else{
            for (int i: nums){
                if (!list.contains(i)){

                    List<Integer> templist = new ArrayList<Integer>(list);
                    
                    templist.add(i);
                    find(nums,templist);
                }
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        find(nums,list);
        return lists;
    }
}
```

# 47

~~~
class Solution {
    Set<List<Integer>> set = new HashSet<List<Integer>>();
    int length;
    int[] _nums;
    private void dfs(int current,int[] a){
        if (current==length-1){
            List<Integer> list = new ArrayList<Integer>();
            for (int i : a){
                list.add(_nums[i]);
            }
            set.add(list);
            return;
        }else{
            int[] check = new int[length];
            for (int i=0;i<=current;i++){
            check[a[i]] = 1;
            }

            for (int i=0;i<length;i++)
                if (check[i]==0){
                    a[current+1]=i;
                    dfs(current+1,a);
                }
    

        
            return;    
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        length = nums.length;
        _nums = nums;
        int[] a = new int[length];

        dfs(-1,a);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        for (List<Integer> i:set){
            answer.add(i);
        }
        return answer;
    }
}
~~~



# 48

```
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }
}
```

# 49 复杂 HashMap 和String 操作

~~~
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
~~~



# 53

```
class Solution {
    public int maxSubArray(int[] nums) {
        int n= nums.length;
        int[] f = new int[n];
        f[0]=nums[0];
        int ans = f[0];
        for (int i=1;i<n;i++){
            f[i]=Math.max(f[i-1]+nums[i],nums[i]);
            ans = Math.max(ans,f[i]);
        }
        return ans;
    }
}
```

# 54

~~~
class Solution {
    int[][] map;
    int[][] _matrix;
    
    int[] directX={0,1,0,-1};
    int[] directY={1,0,-1,0};
    
    int m,n;
    List<Integer> list =new ArrayList<Integer>();
    
    private void find(int x, int y,int direct){
        map[x][y] = 1;
        list.add(_matrix[x][y]);
        if (x+directX[direct]>=m || x+directX[direct]<0 || y+directY[direct]>=n || y+directY[direct]<0){
            direct = (direct+1)%4;
        }else if(map[x+directX[direct]][y+directY[direct]] == 1){
            direct = (direct+1)%4;
        }
        if (x+directX[direct]<m && x+directX[direct]>=0 && y+directY[direct]<n && y+directY[direct]>=0 && map[x+directX[direct]][y+directY[direct]] == 0){
            find(x+directX[direct],y+directY[direct],direct);
        }
        
    } 
    public List<Integer> spiralOrder(int[][] matrix) {
        _matrix = matrix;

        m=matrix.length;
        n=matrix[0].length;
        map = new int[m][n];
        find(0,0,0);

        return list;

    }
}
~~~



# 55

```
class Solution {
    public boolean canJump(int[] nums) {
        
        int arriable = 0;
        int n = nums.length;

        for (int i=0;i<n;i++){
            if (arriable>=i){
                arriable = Math.max(arriable,i+nums[i]);
            }
        }

        if (arriable>=n-1){
            return true;
        }else{
            return false;
        }

    }
}
```

# 56

```

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
```

# 62

```
class Solution {
    public int uniquePaths(int m, int n) {
        m=m-1;
        n=n-1;
        if (m>n){
            int temp = m;
            m= n;
            n= temp;
        }

        int k=m+n;
        long ans=1;
        for (int i=n+1;i<=k;i++) ans*=i;
        for (int i=1;i<=m;i++) ans/=i;
        return (int) ans;
    }
}
```

# 63

~~~
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++){
                if (obstacleGrid[i][j]==1){
                    f[i][j]=0;
                  //  System.out.print(f[i][j]);
                    continue;
                }      
                if (!(i==0&&j==0)){
                    int x=0;
                    int y=0;
                    x = i-1>=0?f[i-1][j]:0;
                    y = j-1>=0?f[i][j-1]:0;
                    f[i][j]=x+y;

                //    System.out.print(f[i][j]);
                }
                
            }
        return f[m-1][n-1];
    }
}
~~~



# 64

```
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];
        for(int i =0;i<m;i++)
            for(int j =0;j<n;j++){
                f[i][j]=90000000;
            }
        f[0][0]=grid[0][0];
        for(int i =0;i<m;i++)
            for(int j =0;j<n;j++)
                if (!(i==0 && j==0)){
                    int x=90000000;
                    int y=90000000;
                    if (i>0) x=f[i-1][j];
                    if (j>0) y=f[i][j-1];
                    f[i][j] = Math.min(x+grid[i][j],y+grid[i][j]);
             
                }    
        return f[m-1][n-1];    
    }
}
```

# 69 袖珍计算器 计算Sqort





~~~
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }
}

~~~



# 70

```
class Solution {
    public int climbStairs(int n) {
        int[] f = new int[n+2];
        f[0]=0;
        f[1]=1;
        f[2]=2;
        for (int i=3;i<=n;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f[n];
    }
}
```

# 71

~~~
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack =new Stack<String>();

        
        for (String i: path.split("\\/+")){
            if (i.equals("")) continue;
            if (i.equals(".")) continue;
            if (i.equals("..")){
                if (stack.size()==0) continue;
                stack.pop();
                continue;
            }    
            stack.push(i);
        }
        
        if (stack.size()==0) return"/";
        
        List<String> list = new ArrayList<String>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        Collections.reverse(list);
        
        String str="";
        for (String s: list){
            str=str+"/"+s;
        }
        return str;
    }
}
~~~



# 72

```
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }
}
```

# 73

~~~
class Solution {
  public void setZeroes(int[][] matrix) {
    Boolean isCol = false;
    int R = matrix.length;
    int C = matrix[0].length;

    for (int i = 0; i < R; i++) {

      // Since first cell for both first row and first column is the same i.e. matrix[0][0]
      // We can use an additional variable for either the first row/column.
      // For this solution we are using an additional variable for the first column
      // and using matrix[0][0] for the first row.
      if (matrix[i][0] == 0) {
        isCol = true;
      }

      for (int j = 1; j < C; j++) {
        // If an element is zero, we set the first element of the corresponding row and column to 0
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }

    // Iterate over the array once again and using the first row and first column, update the elements.
    for (int i = 1; i < R; i++) {
      for (int j = 1; j < C; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    // See if the first row needs to be set to zero as well
    if (matrix[0][0] == 0) {
      for (int j = 0; j < C; j++) {
        matrix[0][j] = 0;
      }
    }

    // See if the first column needs to be set to zero as well
    if (isCol) {
      for (int i = 0; i < R; i++) {
        matrix[i][0] = 0;
      }
    }
  }
}
~~~

# 74

~~~
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int column=0;
        for (int i=0;i<m;i++){
            if (matrix[i][n-1]>=target){
                column = i;
                break;
            }
        }
        boolean check = false;
        for (int i=0;i<n;i++){
            if (matrix[column][i] == target){
                check = true;
            }
        }
        return check;
    }
}
~~~





# 75

```
class Solution {
    public void sortColors(int[] nums) {
        int red=0;
        int white=0;
        int blue=0;
        for (int i: nums){
            switch (i){
                case 0: 
                    red+=1;
                    break;
                case 1:
                    white+=1;
                    break;
                default:
                    blue+=1; 
            }
        }

        int number = 0;
        for (int i=1;i<=red;i++) {nums[number]=0;number+=1;}
        for (int i=1;i<=white;i++) {nums[number]=1;number+=1;}
        for (int i=1;i<=blue;i++) {nums[number]=2;number+=1;}
    }
}
```

# 77

~~~
class Solution {
    List<List<Integer>> answer = new ArrayList<List<Integer>>();
    int _n;
    int _k;

    private void find(int carry, int[] a){
        if (carry==_k-1){
            List<Integer> list = new ArrayList<Integer>();
            for (int i: a){
                list.add(i);
            }
            answer.add(list);
            return;
        }else{
            if (carry==-1){
                for (int i=1;i<=_n;i++){
                    a[carry+1]=i;
                    find(carry+1,a);
                }
            }else{
                for (int i=a[carry]+1;i<=_n;i++){
                    a[carry+1]=i;
                    find(carry+1,a);

                }
            }
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        int[] a = new int[k];
        _n = n;
        _k = k;

        find(-1,a);
        return answer;
    }
}
~~~



# 78

~~~
class Solution {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(int[] nums, int K,List<Integer> list){
        int n = nums.length;
        if (K==n){
            lists.add(list);
        }else{
            List<Integer> list1 = new ArrayList<Integer>(list);
            List<Integer> list2 = new ArrayList<Integer>(list);
            list2.add(nums[K]);

            find(nums,K+1,list1);
            find(nums,K+1,list2);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        find(nums,0,new ArrayList<Integer>());
        return lists;
    }
}
~~~

# 79

```
class Solution {
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}
```

# 82

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
~~~



# 83

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
    private void delete(ListNode node){
        if (node!=null){
            while (node.next!=null && node.next.val == node.val){
                node.next = node.next.next;
            }
            delete(node.next);
        }
    }
    public ListNode deleteDuplicates(ListNode head) {
        delete(head);
        return head;
    }
}
~~~



# 84 单调栈

~~~
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
~~~

# 85单调栈

~~~

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
}
~~~

# 88  Arrays.sort(int[]);

~~~
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i=0;i<n;i++){
            nums1[m+i] = nums2[i];
        }
        Arrays.sort(nums1);
        return;
    }
}
~~~







# 90

~~~
class Solution {
    Set<List<Integer>> lists = new HashSet<List<Integer>>();

    private void dfs(int current,int[] nums,List<Integer> list){
        if (current==nums.length){
            Collections.sort(list);
            lists.add(list);
        }else{
            List<Integer> templist1 = new ArrayList<Integer>(list);
            templist1.add(nums[current]);
            List<Integer> templist2 = new ArrayList<Integer>(list);

            dfs(current+1,nums,templist1);
            dfs(current+1,nums,templist2);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();

        if (nums.length==0)  return null;
        dfs(0,nums,list);

        List<List<Integer>> answer = new ArrayList<List<Integer>>();

        for (List<Integer> i: lists){
            answer.add(i);
        }

        return answer;
    }
}
~~~



# 92 部分 反转链表 链表转线性表

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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;

        ListNode realHead = new ListNode();
        realHead.next = head;

        list.add(realHead);

        while(node != null){
            list.add(node);
            node = node.next;
        }

        list.get(left-1).next = list.get(right);
        list.get(left).next = list.get(right).next;
        for (int i = right;i>left;i--){
            list.get(i).next = list.get(i-1);
        }
        

    return realHead.next;
    }
}	
~~~



# 93

~~~
class Solution {
    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        List<String> list = new ArrayList<String>();

        if (length>12) return list;
        
        for (int i=0;i<length;i++){
            if (s.charAt(i)>'9' || s.charAt(i)<'0' )
            return list;
        }

        for (int i=0;i<length-1;i++)
            for (int j=i+1;j<length-1;j++)
                for (int k=j+1;k<length-1;k++){

                    //增加"."
                    String str = "";
                    for (int index = 0;index<length;index++){                
                        if (index == i) {
                            str = str+s.charAt(index)+".";
                            continue;
                        }
                        if (index == j) {
                            str = str+s.charAt(index)+".";
                            continue;
                        }
                        if (index == k){
                            str = str+s.charAt(index)+".";
                            continue;
                        } 
                        str = str+s.charAt(index);
                    }
                    //分离"."
                    String[] splits = str.split("\\.");
                    boolean check = true;
                    for (String splitstring: splits){

                        if (splitstring.length() > new Long(Long.parseLong(splitstring)).toString().length()){
                            check = false;
                            break;
                        };
                        if (Long.parseLong(splitstring)>255){
                            check = false;
                            break;
                        }
                    }
                    //增加答案
                    if (check ==true){
                        list.add(str);
                    }
                }
        return list;        
    }
}
~~~



# 94

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private void find(TreeNode tree){
        if (tree!=null){
            if (tree.left != null) find(tree.left);
            list.add(tree.val);
            if(tree.right != null)  find(tree.right);
        }
        return;
    }
    List<Integer> list = new ArrayList<Integer>();
    public List<Integer> inorderTraversal(TreeNode root) {
        find(root);
        return list;
    }
}
~~~

# 98

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int[] value = new int[1000000];
    int valueCount = -1;
    private void find(TreeNode tree){
        if (tree!=null){
            if (tree.left!=null) find(tree.left);
            valueCount+=1;
            value[valueCount]=tree.val;
            if (tree.right!=null) find(tree.right);
        }
    }
    public boolean isValidBST(TreeNode root) {
        find(root);
        for (int i=1;i<=valueCount;i++){
            if (value[i]<=value[i-1]) return false;
        }
        return true;
    }
}
~~~

# 101

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}
~~~

# 102

~~~
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        
        return ret;
    }
}

~~~

# 103 广度优先搜索 二维List

~~~
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}
~~~



# 104

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxn = 0;
    private void find(TreeNode tree, int K){
        if (K>maxn) maxn=K;
        if (tree.left!=null){find(tree.left,K+1);}
        if (tree.right!=null){find(tree.right,K+1);}
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        find(root,1);
        return maxn;
    }
}
~~~

# 105

```
class Solution {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
```

# 107  二叉树层序遍历 队列

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxLevel = 0;
    Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(TreeNode tree, int level){
        if (tree !=null){
            maxLevel = Math.max(maxLevel,level);
            if (map.containsKey(level)){
                map.get(level).add(tree.val);
                find(tree.left,level+1);
                find(tree.right,level+1);
            }else{
                map.put(level,new ArrayList<Integer>());
                map.get(level).add(tree.val);

                System.out.println(map.get(level));

                find(tree.left,level+1);
                find(tree.right,level+1);
            }
            
        }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        find(root,1);
        for (int i=1;i<=maxLevel;i++){
            lists.add(map.get(i));
        }
        Collections.reverse(lists);
        return lists;
    }
}
~~~

~~~
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            levelOrder.add(0, level);
        }
        return levelOrder;
    }
}
~~~

# 110

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    boolean ifBalanced = true;
    private int find(TreeNode tree){
        if (tree==null) return 0;
        int l = find(tree.left);
        int r = find(tree.right);
        
        if (Math.abs(l-r)>1) ifBalanced = false;
        return Math.max(l,r)+1;

    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        find(root);
        return ifBalanced;
    }
}
~~~



# 111

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int minLevel = 9999999;
    private void  find(TreeNode tree, int level){
        if (tree!=null){
            if (tree.left==null && tree.right==null){
                minLevel = Math.min(minLevel,level);
            }
            find(tree.left,level+1);
            find(tree.right,level+1);
        }
    }
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        find(root,1);
        return minLevel;
    }
}
~~~

# 116

~~~
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root ==null) return null;

        Queue<Node> q = new LinkedList<Node>();
        Queue<Integer> level = new LinkedList<Integer>();
        q.add(root);
        level.add(1);
        while (!q.isEmpty()){
            Node current = q.poll();
            int currentLevel = level.poll();
            if (q.size()==0){
                current.next=null;
            }else{
                if (currentLevel<level.peek()){
                    current.next=null;
                }else{
                    current.next = q.peek();
                }
            }
            if (current.left!=null){
                q.add(current.left);
                level.add(currentLevel+1);
            }
            if (current.right!=null){
                q.add(current.right);
                level.add(currentLevel+1);
            }
        }
    return root;


    }
}
~~~





# 117 层序遍历 复杂操作

~~~
class Solution {
    public Node connect_1(Node root) {
        if (root == null) {
            return null;
        }
        // 借助队列实现层次遍历
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.remove();
                if (size > 0) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            if (root.right != null) {
                // 若右子树不为空，则左子树的 next 即为右子树
                root.left.next = root.right;
            } else {
                // 若右子树为空，则右子树的 next 由根节点的 next 得出
                root.left.next = nextNode(root.next);
            }
        }
        if (root.right != null) {
            // 右子树的 next 由根节点的 next 得出
            root.right.next = nextNode(root.next);
        }
        // 先确保 root.right 下的节点的已完全连接，因 root.left 下的节点的连接
        // 需要 root.left.next 下的节点的信息，若 root.right 下的节点未完全连
        // 接（即先对 root.left 递归），则 root.left.next 下的信息链不完整，将
        // 返回错误的信息。可能出现的错误情况如下图所示。此时，底层最左边节点将无
        // 法获得正确的 next 信息：
        //                  o root
        //                 / \
        //     root.left  o —— o  root.right
        //               /    / \
        //              o —— o   o
        //             /        / \
        //            o        o   o
        connect(root.right);
        connect(root.left);
        return root;
    }

    private Node nextNode(Node node) {
        while (node != null) {
            if (node.left != null) {
                return node.left;
            }
            if (node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }
}
~~~



# 118

~~~
class Solution {
    List<List<Integer>> answer = new ArrayList<List<Integer>>();
    List<Integer> list;
    public List<List<Integer>> generate(int numRows) {
        int[][] f = new int[numRows+1][numRows+1];
        
        f[1][1] = 1;
        list = new ArrayList<Integer>();
        list.add(1);
        answer.add(list);
        if (numRows==1) return answer;

        f[2][1] = 1;
        f[2][2] = 1;
        list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        answer.add(list);
        if (numRows==2) return answer;

        for (int i=3;i<=numRows;i++){
            f[i][1] = 1;
            f[i][i] = 1;
            for (int j=2;j<=i-1;j++){
                f[i][j] = f[i-1][j-1]+f[i-1][j];
            }
            list = new ArrayList<Integer>();
            for (int j=1;j<=i;j++){
                list.add(f[i][j]);
            }
            answer.add(list);
        }

        return answer;
    }
}
~~~



# 121

```

public class Solution {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];

        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }
}
```

# 124

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxn=-99999;
    private int find(TreeNode tree){
        int leftmax=0;
        int rightmax=0;
        if (tree!=null){
            leftmax=0;
            rightmax=0;
            if (tree.left!=null) leftmax = Math.max(0,find(tree.left));
            if (tree.right!=null) rightmax = Math.max(0,find(tree.right));



            maxn = Math.max(tree.val+leftmax,maxn);
            maxn = Math.max(tree.val+rightmax,maxn);
            maxn = Math.max(tree.val+leftmax+rightmax,maxn);

            return Math.max(tree.val+leftmax,tree.val+rightmax);
        }
        return 0;
    }

    public int maxPathSum(TreeNode root) {
        find(root);

        return maxn;
    }
}
```

# 127  哈希表建图+双向宽搜

~~~
class Solution {
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
}

~~~



# 128

```
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
```

# 132 回文 难

~~~
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii/solution/fen-ge-hui-wen-chuan-ii-by-leetcode-solu-norx/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
~~~





# 135

~~~
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}
~~~







# 136

```
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
```

# 139

```
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

# 143 链表转线性表

~~~
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}
~~~





# 144

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> list = new ArrayList<Integer>();
    private void treeSearch(TreeNode tree){
        if (tree!=null){
            list.add(tree.val);
            treeSearch(tree.left);
            treeSearch(tree.right);
        }
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        treeSearch(root);
        return list;
    }
}
~~~



# 145 树的后序遍历 人工栈

~~~
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}

~~~



# 148

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
```

# 151 Java String+List语言特性

~~~
class Solution {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
~~~



# 152

~~~
class Solution {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
~~~

# 153

~~~
class Solution {
    public int findMin(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int middle = (left + right) / 2;
        if (nums[middle] < nums[right]) {
            // middle可能是最小值
            right = middle;
        } else {
            // middle肯定不是最小值
            left = middle + 1;
        }
    }
    return nums[left];
}
}
~~~



# 155

~~~
class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }
    
    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }
    
    public void pop() {
        xStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return xStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
~~~

# 160

~~~
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
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
~~~

# 165

~~~
class Solution {
    public int compareVersion(String version1, String version2) {
        int v1_point=0;
        int v2_point=0;
        int mark = 1;
        for (int i=0;i<version1.length();i++){
            if (version1.charAt(i)=='.'){
                v1_point++;
            }
        }
        for (int i=0;i<version2.length();i++){
            if (version2.charAt(i)=='.'){
                v2_point++;
            }
        }
        if (v1_point<v2_point){
            String tempStr = version1;
            version1 = version2;
            version2 = tempStr;
            int tempInt = v1_point;
            v1_point = v2_point;
            v2_point = tempInt;

            mark = -1;
        }
        //System.out.println(v1_point-v2_point);
        for (int i=1;i<=(v1_point-v2_point);i++){
            version2+=".0";
        }
        
        // System.out.println(version1);
        // System.out.println(version2);

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int answer = 0;
        for (int i=0;i<v1.length;i++){
            Integer x = Integer.parseInt(v1[i]);
            Integer y = Integer.parseInt(v2[i]);
            if (x>y) return 1*mark;
            if (x<y) return -1*mark;
        } 
        return 0;
    }
}
~~~



# 168

~~~
class Solution {
    public String convertToTitle(int columnNumber) {
      String str = "";
      if (columnNumber<=26) return new Character((char)('A'+columnNumber-1)).toString();
        while (columnNumber>26){
            int mod;
            mod = columnNumber % 26;
            if (mod == 0) {
                mod = 26;
                columnNumber = columnNumber / 26 -1;
            }else{
                columnNumber = columnNumber / 26;
            }
            str += (char) (mod + 'A' -1);
            
        }
        str += (char) (columnNumber + 'A' -1);

        str = new StringBuilder(str).reverse().toString();

        return str;
    }
}



~~~



# 169

~~~
class Solution {
    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }
}
~~~

~~~c++
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        unordered_map<int, int> counts;
        int majority = 0, cnt = 0;
        for (int num: nums) {
            ++counts[num];
            if (counts[num] > cnt) {
                majority = num;
                cnt = counts[num];
            }
        }
        return majority;
    }
};
~~~

# 171

~~~
class Solution {
    public int titleToNumber(String columnTitle) {
        String str = new StringBuilder(columnTitle).reverse().toString();
        int carry = 1;
        int sum = 0;
        for (int i=0;i<str.length();i++){
            sum+=carry*(str.charAt(i)-'A'+1);
            carry*=26;
        }
        return sum;
    }
}
~~~



# 172

~~~‘
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        for (int i=5;i<=n;i++){
            int temp = i;
            while (temp%5==0){
                ans++;
                temp/=5;              
            }
        }
        return ans;
    }
}
~~~



# 179

~~~
class Solution {
    public String largestNumber(int[] nums) {
        
        String[] list = new String[nums.length];
        
        for (int i=0;i<nums.length;i++){
             list[i] = String.valueOf(nums[i]);
        }

        for (int i=0;i<list.length;i++)
            for (int j=i+1;j<list.length;j++){
                if ((list[i]+list[j]).compareTo((list[j]+list[i]))<0){
                    String temp;
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
            
        String ans ="";
        for (int i=0;i<list.length;i++){
            ans+=list[i];
        }

        boolean zero = true;
        for (int i=0;i<ans.length();i++){
            if (ans.charAt(i)!='0'){
                zero = false;
                break;
            }
        }
        if (zero == true) return "0";

        return ans;
    }
}
~~~



# 189

~~~
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
}
~~~



# 198

~~~
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        
        if (n==0) return 0;
        if (n==1) return nums[0];

        f[0] = nums[0];
        f[1] = Math.max(nums[0],nums[1]);
        
        for (int i=2;i<n;i++)
            f[i] = Math.max(f[i-2]+nums[i],f[i-1]);
            
        return f[n-1];
    }
}
~~~

# 199

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    Set<Integer> set = new HashSet<Integer>();
    List<Integer> list = new ArrayList<Integer>();

    private void find(TreeNode tree, int level){
        if (tree !=null){
            if (!set.add(level)){
                find(tree.right,level+1);
                find(tree.left,level+1);
            }else{
                list.add(tree.val);
                find(tree.right,level+1);
                find(tree.left,level+1);
            }
        }
        return ;  
    }

    public List<Integer> rightSideView(TreeNode root) {
        find(root,1);
        return list;
    }
}
```

200

```
class Solution {
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
```

# 205

~~~
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map1 =new HashMap<Character,Character>();
        Map<Character,Character> map2 =new HashMap<Character,Character>();

        if (s.length()!=t.length()) return false;
        for (int i=0;i<s.length();i++){
            char x,y;
            x=s.charAt(i);
            y=t.charAt(i);
            if (!map1.containsKey(x)){
                map1.put(x,y);
            }else{
                if (y!=map1.get(x)){
                    return false;
                }
            }

            if (!map2.containsKey(y)){
                map2.put(y,x);
            }else{
                if (x!=map2.get(y)){
                    return false;
                }
            }
        }
        return true;
    }
}
~~~

# 206

```
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```

# 207

```
class Solution {
    List<List<Integer>> edges;
    int[] indeg;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }
}
```

# 208

```
class Trie {
private:
    bool isEnd;
    Trie* next[26];
public:
    Trie() {
        isEnd = false;
        memset(next, 0, sizeof(next));
    }
    
    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            if (node->next[c-'a'] == NULL) {
                node->next[c-'a'] = new Trie();
            }
            node = node->next[c-'a'];
        }
        node->isEnd = true;
    }
    
    bool search(string word) {
        Trie* node = this;
        for (char c : word) {
            node = node->next[c - 'a'];
            if (node == NULL) {
                return false;
            }
        }
        return node->isEnd;
    }
    
    bool startsWith(string prefix) {
        Trie* node = this;
        for (char c : prefix) {
            node = node->next[c-'a'];
            if (node == NULL) {
                return false;
            }
        }
        return true;
    }
};
```

# 210

~~~
class Solution {
    // 存储有向图
    List<List<Integer>> edges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result;
    // 判断有向图中是否有环
    boolean valid = true;
    // 栈下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v: edges.get(u)) {
            // 如果「未搜索」那么搜索相邻节点
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 如果「搜索中」说明找到了环
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        // 将节点入栈
        result[index--] = u;
    }
}
~~~



# 212

~~~
class Solution {
    int m;
    int n;
    char[][] f;
    int [][] block;
    Set<String> set = new HashSet<String>();
    List<String> list = new ArrayList<String>();


    private void find(int x,int y , String word, int position){
        if (x>=0 && x<=m-1 && y>=0 && y<=n-1 && block[x][y]==0){
            if (position==word.length()-1){
                if (f[x][y]==word.charAt(position)){
                set.add(word);
                }
            }else{
                if (f[x][y]==word.charAt(position)){
                    block[x][y] = 1;
                    find(x+1,y,word,position+1);
                    find(x-1,y,word,position+1);
                    find(x,y+1,word,position+1);
                    find(x,y-1,word,position+1);
                    block[x][y] = 0;
                }
            }
        }
        
    }
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        f = board;
        block = new int[m][n];

        for (String word : words){
            char first = word.charAt(0);
            for (int i=0;i<m;i++)
                for (int j=0;j<n;j++)

                    if (first == board[i][j]){                       
                        find(i,j,word,0);
                    }
        }

        for (String i: set){
            list.add(i);
        }
        
        return list;
    }
}
~~~



# 215

```
import java.util.PriorityQueue;

public class Solution {

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 len 个元素的最大堆，lambda 表达式应写成：(a, b) -> b - a
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(len, (a, b) -> b - a);
        for (int i = 0; i < len; i++) {
            maxHeap.add(nums[i]);
        }
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }
}
```

# 218 复杂优先队列

~~~
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int[] building : buildings) {
            pq.offer(new int[] { building[0], -building[2] });
            pq.offer(new int[] { building[1], building[2] });
        }

        List<List<Integer>> res = new ArrayList<>();

        TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
        heights.put(0, 1);
        int left = 0, height = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            if (arr[1] < 0) {
                heights.put(-arr[1], heights.getOrDefault(-arr[1], 0) + 1);
            } else {
                heights.put(arr[1], heights.get(arr[1]) - 1);
                if (heights.get(arr[1]) == 0) heights.remove(arr[1]);
            }
            int maxHeight = heights.keySet().iterator().next();
            if (maxHeight != height) {
                left = arr[0];
                height = maxHeight;
                res.add(Arrays.asList(left, height));
            }
        }

        return res;
    }
}
~~~



# 221

```
class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
```

# 225

~~~
class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}


~~~



# 226

```
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
```

# 230

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int K;
    int Count = 0;
    int ans;

    private void find(TreeNode tree){
        if (tree !=null){
            find(tree.left);
            Count+=1;
            if (Count == K) ans = tree.val;
            find(tree.right);
        }
        return;
    }

    public int kthSmallest(TreeNode root, int k) {
        K = k;
        find(root);
        return ans;

    }
}
```

# 234

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
```

# 235

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    TreeNode answer;
    boolean lock = true;
    Set<TreeNode> set = new HashSet<TreeNode>();
    private boolean findleft(TreeNode tree, TreeNode left){
        if (tree!=null){
            if (tree == left || findleft(tree.left,left) || findleft(tree.right,left))  {
                set.add(tree);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean findright(TreeNode tree,TreeNode right){
        if (tree!=null){
            if (tree == right ||findright(tree.left,right) || findright(tree.right,right))  {
                if (lock==true && set.contains(tree)){
                    answer = tree;
                    lock = false;
                }
                return true;
            }
            return false;

        }
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<TreeNode> set = new HashSet<TreeNode>();
        findleft(root,p);
        findright(root,q);
        return answer;
    }
}
~~~



# 239 单调队列和优先队列

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
```

# 240

```
class Solution {
    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = (lo + hi)/2;
            if (vertical) { // searching a column
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }
        
        return false; 
    }
}
```

# 253 优先队列 双元素快排 单调栈 穷举

~~~
class Solution {
    public int minMeetingRooms(int[][] intervals) {

    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }

    // Min heap
    PriorityQueue<Integer> allocator =
        new PriorityQueue<Integer>(
            intervals.length,
            new Comparator<Integer>() {
              public int compare(Integer a, Integer b) {
                return a - b;
              }
            });

    // Sort the intervals by start time
    Arrays.sort(
        intervals,
        new Comparator<int[]>() {
          public int compare(final int[] a, final int[] b) {
            return a[0] - b[0];
          }
        });

    // Add the first meeting
    allocator.add(intervals[0][1]);

    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {

      // If the room due to free up the earliest is free, assign that room to this meeting.
      if (intervals[i][0] >= allocator.peek()) {
        allocator.poll();
      }

      // If a new room is to be assigned, then also we add to the heap,
      // If an old room is allocated, then also we have to add to the heap with updated end time.
      allocator.add(intervals[i][1]);
    }

    // The size of the heap tells us the minimum rooms required for all the meetings.
    return allocator.size();
  }
}
~~~

```
class Solution {
    public int minMeetingRooms(int[][] intervals) {

    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }

    Integer[] start = new Integer[intervals.length];
    Integer[] end = new Integer[intervals.length];

    for (int i = 0; i < intervals.length; i++) {
      start[i] = intervals[i][0];
      end[i] = intervals[i][1];
    }

    // Sort the intervals by end time
    Arrays.sort(
        end,
        new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
            return a - b;
          }
        });

    // Sort the intervals by start time
    Arrays.sort(
        start,
        new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
            return a - b;
          }
        });

    // The two pointers in the algorithm: e_ptr and s_ptr.
    int startPointer = 0, endPointer = 0;

    // Variables to keep track of maximum number of rooms used.
    int usedRooms = 0;

    // Iterate over intervals.
    while (startPointer < intervals.length) {

      // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
      if (start[startPointer] >= end[endPointer]) {
        usedRooms -= 1;
        endPointer += 1;
      }

      // We do this irrespective of whether a room frees up or not.
      // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
      // remain the same in that case. If no room was free, then this would increase used_rooms
      usedRooms += 1;
      startPointer += 1;

    }

    return usedRooms;
  }
}

```



```python
#  Copyright (c) 2021
#  @Author: xiaoweixiang
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        n, ans, tmp, length, b = 0, 0, [], len(intervals), []
        while n < length:
            for t in intervals:
                if not tmp or tmp[-1][1] <= t[0]:
                    tmp.append(t)
                    n += 1
                else:
                    b.append(t)
            ans, intervals, tmp, b = ans + 1, b, [], []
        return ans
```

~~~
class Solution {
    int answer = 0;
    public int minMeetingRooms(int[][] intervals) {
        for (int i=0;i<intervals.length;i++){
            int count = 1;
            for (int j=0;j<intervals.length;j++)
                if (i!=j){
                    if ((intervals[i][0]<intervals[j][1] && intervals[i][0]>=intervals[j][0]) || 
                        (intervals[i][1]<intervals[j][1] && intervals[i][0]>intervals[j][0])){
                        count++;
                    }
                }
            answer =Math.max(answer,count);    
        }
        return answer;
    }
}
~~~



# 268

~~~
class Solution {
    public int missingNumber(int[] nums) {
        long l1 = 0;
        long l2 = 0;
        int n = nums.length;
        for (int i:nums){
            l1+=i;
        }
        l2 = n*(n+1)/2;

        int ans = (int)(l2-l1);
        return ans;
    }
}
~~~



# 273  优美的字符串处理

~~~
class Solution {
    private final String[] THOUSAND = {"", "Thousand", "Million", "Billion"};
    private final String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] HUNDRED = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num) {
        if(num == 0) return "Zero";

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while(num > 0) {
            if(num % 1000 != 0) {
                StringBuilder tmp = new StringBuilder();
                helper(num % 1000, tmp);
                sb.insert(0, tmp.append(THOUSAND[index]).append(" "));
            }
            index++;
            num /= 1000;
        }
        return sb.toString().trim();
    }

    private void helper(int num, StringBuilder tmp) {
        if(num == 0) return;
        if(num < 20) {
            tmp.append(LESS_THAN_TWENTY[num]).append(" ");
        }else if(num < 100) {
            tmp.append(HUNDRED[num / 10]).append(" ");
            helper(num % 10, tmp);
        }else {
            tmp.append(LESS_THAN_TWENTY[num / 100]).append(" Hundred").append(" ");
            helper(num % 100, tmp);
        }
    }
}
~~~



# 277

~~~
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0;i<n;i++) set.add(i);

        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                if (i!=j){
                    if (knows(i,j)==true){
                        set.remove(i);
                        break;
                    }
                }

        if (set.size()==0) return -1;

        Set<Integer> newset = new HashSet<Integer>(set);
        for (int i: set){
            for (int j=0;j<n;j++){
                if (knows(j,i)==false){
                    newset.remove(i);
                }
            }
        }

        set = newset;

        if (set.size()==0 || set.size()>1) return -1;

        for (int i: set){
            return i;  //在这里退出
        }

        return 0;
    }
}
~~~



# 279

```
class Solution {
    int ans = 9999999;
    private void find(int remain,int step){
        if (remain == 0) {
            ans = Math.min(ans,step);
        }else{
            if (step<ans){
                int currentMaxSqrt = (int) Math.round(Math.sqrt(remain));
                for (int i = currentMaxSqrt;i>=1;i--){
                    if (remain>=i*i){
                        find(remain-i*i,step+1);
                    }
                }
            }
        }
    }
    public int numSquares(int n) {
        find(n,0);   
        return ans;
    }
}
```

# 283

```
class Solution {
    public void moveZeroes(int[] nums) {
        int indexNow = 0;
        int indexNum = 0;
        int m = nums.length;

        while(indexNum<m){
            if(nums[indexNum] != 0) {
                nums[indexNow++] = nums[indexNum];
            }
            ++indexNum;
        }

        for(int i = indexNow; i < m; i++){
            nums[i] = 0;
        }
    }
}
```

# 287

```
class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<Integer>();
        for (int i : nums){
            if (!hash.add(i)) {
                return i;
            }
        }
        return 0;
    }
}
```

# 295 双优先队列

```
import java.util.PriorityQueue;

public class MedianFinder {

    /**
     * 当前大顶堆和小顶堆的元素个数之和
     */
    private int count;
    private PriorityQueue<Integer> maxheap;
    private PriorityQueue<Integer> minheap;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        count = 0;
        maxheap = new PriorityQueue<>((x, y) -> y - x);
        minheap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        count += 1;
        maxheap.offer(num);
        minheap.add(maxheap.poll());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if ((count & 1) != 0) {
            maxheap.add(minheap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double) (maxheap.peek() + minheap.peek()) / 2;
        } else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double) maxheap.peek();
        }
    }
}

```

# 297

~~~

public class Codec {
    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }
  
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }
  
    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }
  
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);
    
        return root;
    }
  
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }
}
~~~

# 300

~~~
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
~~~

# 309

~~~
class Solution {
    public int maxProfit(int[] prices) {

        int n = prices.length;
        if (n==0) return 0;

        int[][] f = new int[n][2];
        f[0][0] = -prices[0];
        f[0][1] = 0;
        
        for (int i=1;i<n;i++){
            if (i == 1){
                f[1][0] = Math.max(f[0][0],-prices[1]);
                f[1][1] = Math.max(f[0][0]+prices[1],0);
            }
            if (i >= 2){
                f[i][0] =Math.max(f[i-1][0],f[i-2][1]-prices[i]);
                f[i][1] =Math.max(f[i-1][1],f[i-1][0]+prices[i]);
            }
        }


        return f[n-1][1];
    }
}
~~~

# 322

```
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

# 331 用栈来处理二叉树

~~~
class Solution {
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }
}

~~~



# 338

```
class Solution {
    public int[] countBits(int num) {

        int[] f = new int[num+1];

        f[0]=0;
        for(int i = 0; i<num/2;i++){

            f[2*i] = f[i];
            f[2*i+1] = f[i] +1;
        }
        if (num%2 == 0){
            f[num] = f[num/2];
        }else{
            f[num-1] = f[num/2];
            f[num] = f[num/2]+1; 
        }
        return f;
    }
}
```

# 344

~~~
class Solution {
    public void reverseString(char[] s) {
        for(int i=0;i<s.length/2;i++){
            char temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
        return ;
    }
}
~~~



# 347

```

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}
```

# 348

~~~
class TicTacToe {
    int n;
    int[][] rows;
    int[][] columns;
    int[][] diagonals;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[3][n]; // 3 表示 player1 和 player2，索引 0 是无用的
        columns = new int[3][n];
        diagonals = new int[3][2];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (++rows[player][row] == n) {
            // 某玩家在在第 row 行上放了 n 个棋子
            return player;
        }
        if (++columns[player][col] == n) {
            // 某玩家在在第 col 列上放了 n 个棋子
            return player;
        }
        if (row == col && ++diagonals[player][0] == n) {
            // 某玩家在在正对角线上上放了 n 个棋子
            return player;
        }
        if ((row + col == n - 1) && ++diagonals[player][1] == n) {
            // 某玩家在负对角线上放了 n 个棋子
            return player;
        }

        return 0;
    }
}
/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
~~~



# 349

~~~
class Solution {
    Set<Integer> set1 = new HashSet<Integer>();
    Set<Integer> set2 = new HashSet<Integer>();

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i:nums1){
            set1.add(i);
        }
        for (int i:nums2){
            set2.add(i);
        }
        for (int i:set1){
            if (set2.contains(i)){
                list.add(i);
            }
        }

        int[] ans = new int[list.size()];
        for (int i=0;i<list.size();i++){
            ans[i]=list.get(i);
        }
        return  ans;
    }
}
~~~





# 378

~~~
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        List<Integer> list =new  ArrayList<Integer>();
        int n = matrix.length;
        for (int i=0;i<n;i++)
            for (int j:matrix[i]){
                list.add(j);
            }
        Collections.sort(list);
        return list.get(k-1);    
    }
}
~~~



# 394 递归 括号处理

```
class Solution {
    String src;
    int ptr;

    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits(); 
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString(); 
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }
        
        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }
}

```

# 402 贪心+单调栈

~~~
class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        
        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }
        
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
~~~



# 406

~~~
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        boolean[] selected = new boolean[n];
        int[][] order = new int[n][2];
        int[][] newpeople = new int[n][2];

        for (int i=0;i<n;i++)
            for (int j=0;j<=1;j++)
                newpeople[i][j] = people[i][j];


        for (int i=0;i<n;i++){
            int x,y;
            x=99999999;
            y=99999999;
            for (int j=0;j<n;j++){
                if (!selected[j]){
                    if (people[j][1]<y){
                        x=j;
                        y=people[j][1];
                        continue;
                    }
                    if (people[j][1]==y && people[j][0]<people[x][0]){
                        x=j;
                        //y=people[j][1];
                        continue;
                    }
                }
            }
            order[i] = newpeople[x];
            selected[x] = true;

            for (int j=0;j<n;j++)
                if (!selected[j] && j!=x && people[j][0]<=people[x][0]){
                    people[j][1] -= 1;
                }

        }

        return order;

    }
}
~~~

~~~
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
~~~

# 415 高精度加法

~~~
class Solution {
    public String addStrings(String num1, String num2) {
        String temp;
        if (num1.length()<num2.length()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int[] a = new int[num1.length()+1];
        int[] b = new int[num1.length()+1];

        for (int i = num1.length()-1;i>=0;i--){
            a[num1.length()-i-1] = num1.charAt(i)-48;
        }
           
        for (int i = num2.length()-1;i>=0;i--){
            b[num2.length()-i-1] = num2.charAt(i)-48;
        }
        


        for (int i = 0;i<num1.length();i++){
            a[i]+=b[i];
            if (a[i]>=10){
                a[i]-=10;
                a[i+1]+=1;
            }
        }

        String answer = "";
        if (a[num1.length()]!=0){
            answer += (char) (a[num1.length()] + 48);
        }
        for (int i = num1.length()-1;i>=0;i--){
            answer += (char) (a[i] + 48);
        }
       return answer;
    }
}
~~~



# 416

~~~
class Solution {
    public boolean canPartition(int[] nums) {
        int Sum=0;
        for (int i: nums){
            Sum += i;
        }
        if (Sum % 2 ==1) return false;

        Set<Integer> set = new HashSet<Integer>();
        set.add(0);

        int[] x = new int[20001];
        x[0]=0;
        int xLength=1;

        for (int i : nums){
            int xLengthIncrease;
            xLengthIncrease = 0;
            for (int j=0;j<xLength;j++)
                if (!set.contains(i+x[j])){
                    set.add(i+x[j]);
                    xLengthIncrease +=1;
                    x[xLength+xLengthIncrease-1] = (i+x[j]);
                }
            xLength += xLengthIncrease; 
        }

        return set.contains(Sum/2);

    }
}
~~~

# 419

~~~
class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    board[i][j] = '.';
                    int a = i + 1;
                    int b = j;
                    // 遍历行
                    while (a < board.length && board[a][b] == 'X') {
                        board[a++][b] = '.';
                    }
                    a = i;
                    b = j + 1;
                    // 遍历列
                    while (b < board[0].length && board[a][b] == 'X') {
                        board[a][b++] = '.';
                    }
                }
            }
        }
        return count;
    }
}
~~~



# 424 双指针 滑动窗口

~~~
public class Solution {

    public int characterReplacement(String s, int k) {
        int len = s.length();
        if (len < 2) {
            return len;
        }

        char[] charArray = s.toCharArray();
        int left = 0;
        int right = 0;

        int res = 0;
        int maxCount = 0;
        int[] freq = new int[26];
        // [left, right) 内最多替换 k 个字符可以得到只有一种字符的子串
        while (right < len){
            freq[charArray[right] - 'A']++;
            // 在这里维护 maxCount，因为每一次右边界读入一个字符，字符频数增加，才会使得 maxCount 增加
            maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
            right++;

            if (right - left > maxCount + k){
              	// 说明此时 k 不够用
                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                // 移出滑动窗口的时候，频数数组须要相应地做减法
                freq[charArray[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}

~~~





# 437 前缀和 hashmap

~~~
**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
~~~

# 438 滑动窗口

```
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();
        // 计算字符串p中各元素的出现次数
        int[] pFreq = new int[26];
        for(int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i)-'a']++;
        }
        // 窗口区间为[start,end]
        int start = 0, end = -1;
        while (start <s.length()) {
            // 还有剩余元素未考察，且窗口内字符串长度小于字符串p的长度
            // 则扩大窗口右侧边界
            if (end+1 < s.length() && end-start+1 <p.length()) {
                end++;
            }else {
                // 右侧边界不能继续扩大或窗口内字符串长度等于字符串p的长度
                // 则缩小左侧边界
                start++;
            }
            // 当窗口内字符串长度等于字符串p的长度时，则判断其是不是字符串p的字母异位词子串
            if (end-start+1 == p.length() && isAnagrams(s.substring(start,end+1), pFreq)) {
                resultList.add(start);
            }
        }
        return resultList;
    }
    // 判断当前子串是不是字符串p的字母异位词
    private boolean isAnagrams(String window, int[] pFreq) {
        // 计算窗口内字符串各元素的出现次数
        int[] windowFreq = new int[26];
        for(int i = 0; i < window.length(); i++) {
            windowFreq[window.charAt(i)-'a']++;
        }
        // 比较窗口内各元素的出现次数和字符串p中各元素的出现次数是否一样
        // 如果都一样，则说明窗口内的字符串是字符串p的字母异位词子串
        // 如果不一样，则说明不是其子串
        for(int j = 0; j < 26; j++) {
            if (windowFreq[j] != pFreq[j]) {
                return false;
            }
        }
        return true;
    }
}
```

# 443

~~~
class Solution {
    public int compress(char[] chars) {
        char current;
        int num;
        String str="";
        current = chars[0];
        num = 1;

        for (int i=1;i<chars.length;i++){
            if (current == chars[i]){
                num++;
            }else{
                if (num==1){
                    str += current;
                }else{
                    str = str+ current + num;
                }
                current = chars[i];
                num=1;
            }
        }

        if (num==1){
            str += current;
        }else{
            str = str+current+num;
        }

        for (int i=0;i<str.length();i++){
            chars[i] = str.charAt(i);
        }

        
        return str.length();


    }
}
~~~





# 445

~~~
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
~~~



# 448

~~~
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        Set<Integer> set = new HashSet<Integer>();
        for (int i:nums) set.add(i);
        for (int i=1;i<=nums.length;i++) 
            if (!set.contains(i)){
                list.add(i);
            }
        return list;    
    }
}
~~~

# 450  二叉搜索树 删除操作

~~~
class Solution {
  /*
  One step right and then always left
  */
  public int successor(TreeNode root) {
    root = root.right;
    while (root.left != null) root = root.left;
    return root.val;
  }

  /*
  One step left and then always right
  */
  public int predecessor(TreeNode root) {
    root = root.left;
    while (root.right != null) root = root.right;
    return root.val;
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;

    // delete from the right subtree
    if (key > root.val) root.right = deleteNode(root.right, key);
    // delete from the left subtree
    else if (key < root.val) root.left = deleteNode(root.left, key);
    // delete the current node
    else {
      // the node is a leaf
      if (root.left == null && root.right == null) root = null;
      // the node is not a leaf and has a right child
      else if (root.right != null) {
        root.val = successor(root);
        root.right = deleteNode(root.right, root.val);
      }
      // the node is not a leaf, has no right child, and has a left child    
      else {
        root.val = predecessor(root);
        root.left = deleteNode(root.left, root.val);
      }
    }
    return root;
  }
}

~~~





# 474 背包问题

~~~
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        //f[i][j][k]=f[i-one[k]][j-zero[k]][k-1]+1;
        int[] zero = new int[strs.length];
        int[] one = new int [strs.length];
        for (int i=0;i<strs.length;i++){
            for (int j=0;j<strs[i].length();j++){
                if (strs[i].charAt(j) == '0'){
                    zero[i]++;
                }else{
                    one[i]++;
                }
            }
        }
        
        int[][][] f = new int[m+1][n+1][strs.length+1];

        for (int k=1;k<=strs.length;k++)
            for (int i=0;i<=m;i++)
                for (int j=0;j<=n;j++){
                    f[i][j][k] = f[i][j][k-1];
                    if (i-zero[k-1]>=0 && j-one[k-1]>=0){
                        f[i][j][k] = Math.max(f[i][j][k],f[i-zero[k-1]][j-one[k-1]][k-1]+1);
                    }
                }
        // System.out.println(m);
        // for (int k=1;k<=strs.length;k++){
        //     for (int i=0;i<=m;i++)
        //         for (int j=0;j<=n;j++){
        //             System.out.print(f[i][j][k]);
        //         }
        //     System.out.println();
        // }

        return f[m][n][strs.length];




    }
}
~~~





# 494

~~~
class Solution {
    int count = 0;
    int n;
    int s;
    private void find(int mark, int carry, int sum,int[] nums){
        if (carry == n){
            if (sum == s){
                count += 1;
                return ;
            }else{
                return;
            }         
        }else{
            find(1,carry+1,sum+mark*nums[carry],nums);
            find(-1,carry+1,sum+mark*nums[carry],nums);
        }
        return ;
    }
    public int findTargetSumWays(int[] nums, int S) {
        n = nums.length;
        s = S;

        find(1,0,0,nums);
        find(-1,0,0,nums);

        return count/2;
    }
}
~~~

# 503 简单的单调栈

~~~
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int _2n = n*2;
        int[] _2nums = new int[_2n];
        int[] ans = new int[_2n];
        int[] answer = new int[n];

        for (int i=0;i<n;i++) {
            _2nums[i] = nums[i];
            _2nums[i+n] = nums[i];
        }
        Stack<Integer> monotonestack = new Stack<Integer>();
        for (int i=_2n-1;i>=0;i--){
            while (!monotonestack.isEmpty()){
                if (_2nums[monotonestack.peek()]<=_2nums[i]){
                    monotonestack.pop();
                }else{
                    ans[i] = _2nums[monotonestack.peek()];
                    monotonestack.push(i);
                    break;
                }
            }
            if (monotonestack.isEmpty()){
                ans[i]=-1;
                monotonestack.push(i);
            }

        }
        for (int i=0;i<n;i++) answer[i] = ans[i];

        return answer;
    }
}
~~~

# 509

~~~
class Solution {
    public int fib(int n) {
        int[] f = new int [31];
        f[0]=0;
        f[1]=1;
        for (int i=2;i<=n;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f[n];
    }
}
~~~

# 523   哈希+动态规划

~~~
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;

        // key：区间 [0..i] 里所有元素的和 % k
        // value：下标 i
        Map<Integer, Integer> map = new HashMap<>();
        // 理解初始化的意义
        map.put(0, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }

        }
        return false;
    }
}
~~~





# 543

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int length = -1;
    private int find(TreeNode tree){
        if (tree == null){
            return 0;
        }else{
            int x = find(tree.left);
            int y = find(tree.right);
            length = Math.max(length,x+y+1);
            return Math.max(x+1,y+1);
        }
    }
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        find(root);       
        return length-1;
    }
}
~~~

# 547 并查集

~~~
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
~~~



# 560 HashMap

~~~
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
~~~

# 617

```
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }
}

```

# 621

```
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] temp = new int[26];
        int countMaxTask = 0;
        int maxTask=0;
        for(char c:tasks){
            temp[c-'A']++;
            maxTask = Math.max(temp[c-'A'],maxTask);
        }
        for(int i=0;i<26;i++){
            if(temp[i]==maxTask){
                countMaxTask++;
            }
        }
        return Math.max(tasks.length,(maxTask-1)*(n+1)+countMaxTask);
    }
}
```

# 647

~~~
class Solution {
  public int countSubstrings(String s) {
    int count = 0;
    for (int i=0;i<s.length();i++)
        for (int j=i;j<s.length();j++){
            String str = s.substring(i,j+1);
            String A = str;
            String B = new StringBuilder(str).reverse().toString();
            if (A.equals(B)) {count++;
        } 
    }
    return count;
  }
}
~~~

# 739 单调栈

```
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[T[i]] = i;
        }
        return ans;
    }
}

```

```
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
```

# 743 SPFA + 邻接表

~~~
class Solution {
   // SPFA：用邻接表写
public int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> map = new HashMap<>();
    // 构建邻接表
    for (int[] arr : times) {
        List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
        list.add(new int[]{arr[1], arr[2]});
        map.put(arr[0], list);
    }
    // 初始化dis数组和vis数组
    int[] dis = new int[N + 1];
    int INF = 0x3f3f3f3f;
    Arrays.fill(dis, INF);  
    boolean[] vis = new boolean[N + 1];
    dis[K] = dis[0] = 0;

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(K);

    while (!queue.isEmpty()) {
        // 取出队首节点
        Integer poll = queue.poll();
        // 可以重复入队
        vis[poll] = false;
        // 遍历起点的邻居,更新距离
        List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
        for (int[] arr : list) {
            int next = arr[0];
            // 如果没更新过，或者需要更新距离()
            if (dis[next] == INF || dis[next] > dis[poll] + arr[1]) {
                // 更新距离
                dis[next] = dis[poll] + arr[1];
                // 如果队列中没有，就不需要再次入队了 （那么判断入度可以在这里做文章）
                if (!vis[next]) {
                    vis[next] = true;
                    queue.offer(next);
                }
            }
        }        
    }
    int res = Arrays.stream(dis).max().getAsInt();
    return res == INF ? -1 : res;
}

}
~~~



# 763

```
class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
```

# 867

~~~
class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[n][m];

        for (int i=0;i<n;i++)
            for (int j=0;j<m;j++)
                f[i][j] = matrix[j][i];
        return f;
    }
}
~~~



# 912

~~~
class Solution {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}
~~~



# 1006

~~~
class Solution {
    public int clumsy(int N) {
        if(N<=2)return N;
        if(N==3)return 6;
        int sum=N*(N-1)/(N-2)+N-3;
        N-=4;
        while(N>=4){
         sum+=(-N*(N-1)/(N-2)+N-3);
         N-=4;
        }
        return sum-clumsy(N);
    }
}
~~~



# 1114 并发 信号量

~~~
class Foo {
    public Semaphore seam1 = new Semaphore(0);  
    public Semaphore seam2 = new Semaphore(0);

    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        
        printFirst.run();
        seam1.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam1.acquire();
        printSecond.run();
        seam2.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        seam2.acquire();
        printThird.run();
    }
}
~~~



# 1047

~~~
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<Character>();
        char[] charArray = S.toCharArray();
        for (char c : charArray){
            if (stack.isEmpty()){
                stack.push(c);
                continue;
            }
            if (stack.peek()==c){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        String str = new String();
        while (!stack.isEmpty()){
            str+=stack.pop();
        }
        
        return new String(new StringBuilder(str).reverse());
    }
}
~~~



# 1143

~~~
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] f = new int[text1.length()+1][text2.length()+1];
        for (int i = 1;i<=text1.length();i++)
            for (int j = 1;j<=text2.length();j++){
                int equal=0;
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    equal=1;
                }
                f[i][j] = Math.max(f[i-1][j-1]+equal,f[i][j]);
                f[i][j] = Math.max(f[i-1][j],f[i][j]);
                f[i][j] = Math.max(f[i][j-1],f[i][j]);
            }
        return f[text1.length()][text2.length()];
    }
}
~~~



# 1239

~~~
class Solution {
    List<String> _arr;
    int answer = -1;

    private void find(int current,int length, List<String> list){
        if (current == _arr.size()){
            answer = Math.max(answer,length);
        }else{
            Set<Character> set = new HashSet<Character>();
            for (String s :list)
                for (int i=0;i<s.length();i++){
                    set.add(s.charAt(i));
                }
            String currentString = _arr.get(current);
            boolean check = true;
            for (int i=0;i<currentString.length();i++)
                if (!set.add(currentString.charAt(i))){
                    check = false;
                    break;
                }
            if (check ==true){
                List<String> tempList = new ArrayList<String>(list);
                tempList.add(currentString);
                find(current+1,length+currentString.length(),tempList);
            }    
            find(current+1,length,new ArrayList<String>(list));
        }   
            
        return ;
    }

    public int maxLength(List<String> arr) {
        _arr = arr;
        find(0,0,new ArrayList<String>());
        return answer;
    }
}
~~~



# 1411 组合数学

~~~

class Solution {
    static final int MOD = 1000000007;

    public int numOfWays(int n) {
        long fi0 = 6, fi1 = 6;
        for (int i = 2; i <= n; ++i) {
            long newFi0 = (2 * fi0 + 2 * fi1) % MOD;
            long newFi1 = (2 * fi0 + 3 * fi1) % MOD;
            fi0 = newFi0;
            fi1 = newFi1;
        }
        return (int) ((fi0 + fi1) % MOD);
    }
}

~~~

# 1773

~~~
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index=0;
        int ans = 0;
        if (ruleKey.equals("type")){
            index = 0;
        }
        if (ruleKey.equals("color")){
            index = 1;
        }
        if (ruleKey.equals("name")){
            index = 2;
        }

        for (List<String> list:items){
            if (ruleValue.equals(list.get(index)))
                ans++;
        }
        return ans;
    }
}
~~~

# 1774

~~~
class Solution {
    int n;
    int m;
    int ans = 9999999;
    int temp;
    int _toppingCosts[];

    private void find(int current,int sum,int target){
        if(current == m){
            if (Math.abs(sum+temp-target)<Math.abs(ans-target)){
                // System.out.println(sum);
                // System.out.println(temp);
                ans = sum + temp;
                return ;
                
            }
            if (Math.abs(sum+temp-target)==Math.abs(ans-target) && sum+temp<ans){
                ans = sum + temp;
                return ;
            }
        }else{
            //System.out.println(current);
            find(current+1,sum,target);
          //  System.out.println(sum);
            find(current+1,sum+_toppingCosts[current],target);
          //  System.out.println(sum+_toppingCosts[current]);
            find(current+1,sum+2*_toppingCosts[current],target);
          //  System.out.println(sum+2*_toppingCosts[current]);
        }
    }
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        n = baseCosts.length;
        m = toppingCosts.length;
        int[] num = new int[m];
        _toppingCosts = toppingCosts;

        for (int i=0;i<n;i++){
            

            temp = baseCosts[i];
            find(0,0,target);
        }

        return ans;
    }
}
~~~

# 1775

~~~
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (6*n<m) return -1;
        if (6*m<n) return -1;
        int sum1 = 0;
        int sum2 = 0;
        for (int i:nums1){
            sum1+=i;
        }
        for (int i:nums2){
            sum2+=i;
        }

        if (sum1==sum2) return 0;

        if (sum1<sum2){
            int[] tempArray;
            int temp;

            tempArray = nums1;
            nums1 = nums2;
            nums2 = tempArray;

            temp = sum1;
            sum1 = sum2;
            sum2 = temp; 
        } 
        PriorityQueue<Integer> n2 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> n1 = new PriorityQueue<Integer>((a,b)->b-a);
        
        for (int i:nums1){
            n1.add(i);
        }
        for (int i:nums2){
            n2.add(i);
        }
        int ans = 0;
        while (sum2<sum1){
            int x = n1.peek();
            int y = n2.peek();
            if ( (x-1) > (6-y) ){
                sum1 = sum1 + 1 -x;
                n1.poll();
                n1.add(1);
            }else{
                sum2 = sum2 - y+6;
                n2.poll();
                n2.add(6);
            }
            ans++;
        }
        return ans;
    }
}
~~~

# 1776.cpp

~~~
class Solution {
public:
    vector<double> getCollisionTimes(vector<vector<int>>& cars) {
        int n = cars.size();
        vector<double> res(n);
        res[n-1] = -1;
        stack<int> st;
        st.push(n-1);
        for(int i = n-2; i >= 0; --i) {
            while(st.size()) {
                // 此处的详细说明：
                // 1. 如果当前车的车速 <= 栈顶车的车速，则当前车永远无法追上栈顶车，因此总是可以 pop 出栈顶车；
                // 2. 否则，如果当前车的车速 > 栈顶车：
                //    2.1 如果栈顶车的追上更右侧车辆的时间为 -1 (永远追不上)，则不能 pop 出;
                //    2.2 否则，则判断在 “理想状态(即栈顶车不会追上更右侧车)”下 ，当前车的追上栈顶车的时间 T
                //        (也就是下面代码里的式子)，如果 T > res[st.top()](即栈顶车的实际追及时间)，
                //        说明不能在右侧车追上更右侧车之前追上，应当 pop；否则能在碰撞前追上。
                if(cars[i][1] <= cars[st.top()][1] || (res[st.top()] > 1e-9 && (double)(cars[st.top()][0] - cars[i][0]) / (double)(cars[i][1] - cars[st.top()][1]) > res[st.top()])) {
                    st.pop();
                } else {
                    break;
                }
            }
            if(st.size()) {
                res[i] = (double)(cars[st.top()][0] - cars[i][0]) / (double)(cars[i][1] - cars[st.top()][1]);
                st.push(i);
            } else {
                res[i] = -1;
                st.push(i);
            }
        }
        return res;
    }
};

~~~



# Sword03

~~~
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i:nums){
            if (!set.add(i)){
                return i;
            }
        }
        return 0;
    }
}
~~~

# Sword04

~~~
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while(row < m && col >= 0) {
            if(matrix[row][col] > target) {
                col--;
            }else if(matrix[row][col] < target) {
                row++;
            }else {
                return true;
            }
        }
        return false;
    }
}
~~~



# Sword07 先序中序 构建树

~~~
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode Create(int[] preorder,int[] inorder){
        if (preorder.length>0){
            TreeNode node = new TreeNode(preorder[0]);
            int index=0;
            
            for (int i=0;i<inorder.length;i++){
                if (inorder[i]==preorder[0]){
                    index = i;
                }
            }

            if (index>0){
                int[] p = new int[index];
                int[] q = new int[index];
                for (int i=0;i<index;i++){
                    p[i]=preorder[i+1];
                    q[i]=inorder[i];
                    
                }
                node.left = Create(p,q);
            }
            if (preorder.length-1-index>0){
                int[] p = new int[preorder.length-1-index];
                int[] q = new int[preorder.length-1-index];
                for (int i=0;i<preorder.length-1-index;i++){
                    p[i]= preorder[i+index+1];
                    q[i]= inorder[i+index+1]; 
                }
                node.right = Create(p,q);
            }


            return node;
        }
        return null;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode tree = Create(preorder,inorder);
        return tree;
    }
}
~~~



# Sword09 双栈模拟队列

~~~
class CQueue {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    public CQueue() {

    }
    
    public void appendTail(int value) {
        s1.push(value);
    }
    
    public int deleteHead() {
        if (s1.size()==0 && s2.size()==0) return -1;
        if (s2.size()==0){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
~~~



# Sword20 有限状态自动机

~~~
class Solution {
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType type = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(type)) {
                return false;
            } else {
                state = transfer.get(state).get(type);
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END,
    }

    enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_SPACE,
        CHAR_ILLEGAL,
    }
}
~~~



# Sword22

~~~
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        List<ListNode> list = new ArrayList<ListNode>();
        while (head!=null){
            list.add(head);
            head=head.next;
        }
        return list.get(list.size()-k);
    }
}
~~~

# Sword24  链表转线性表

~~~
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;

        while (node != null) {
            list.add(node);
            node = node.next;
        }

        for (int i = list.size()-1;i>=1;i--){
            list.get(i).next = list.get(i-1);
            list.get(0).next = null;
        }
        return list.get(list.size()-1);
    }
}
~~~

# Sword25

~~~
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
~~~



# Sword29

~~~
class Solution {
    int[][] map;
    int[][] _matrix;
    
    int[] directX={0,1,0,-1};
    int[] directY={1,0,-1,0};
    
    int m,n;
    List<Integer> list =new ArrayList<Integer>();
    
    private void find(int x, int y,int direct){
        map[x][y] = 1;
        list.add(_matrix[x][y]);
        if (x+directX[direct]>=m || x+directX[direct]<0 || y+directY[direct]>=n || y+directY[direct]<0){
            direct = (direct+1)%4;
        }else if(map[x+directX[direct]][y+directY[direct]] == 1){
            direct = (direct+1)%4;
        }
        if (x+directX[direct]<m && x+directX[direct]>=0 && y+directY[direct]<n && y+directY[direct]>=0 && map[x+directX[direct]][y+directY[direct]] == 0){
            find(x+directX[direct],y+directY[direct],direct);
        }
        
    } 
    public int[] spiralOrder(int[][] matrix) {
        _matrix = matrix;

        if (matrix.length==0) return new int[0];
        m=matrix.length;
        n=matrix[0].length;
        map = new int[m][n];
        find(0,0,0);

        int[]  answer = new int[list.size()];
        for (int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        return answer;

    }
}
~~~



# Sword38 全排列

~~~
class Solution {
    String str;
    Set<String> set = new HashSet<String>();

    private void arrangement(int carry, int[] a){
        if (carry == str.length()-1){
            String tempString = "";
            for (int i=0;i<str.length();i++){
                tempString += str.charAt(a[i]);
            }
            set.add(tempString);
            return;
        }

        int[] check = new int[str.length()];
        for (int i=0;i<=carry;i++){
            check[a[i]] = 1;
        }

        for (int i=0;i<str.length();i++)
            if (check[i] == 0){
                a[carry+1]=i;
                arrangement(carry+1,a);
            }
        return;    
    }
    public String[] permutation(String s) {
        str = s;
        int[] a = new int[s.length()];
        arrangement(-1,a);

        String[] strs = new String[set.size()];
        int index = -1;
        for (String strinSet: set){
            index++;
            strs[index] = strinSet;
        }
        
        return strs;
    }
}
~~~



# Sword42 简单动态规划

~~~
class Solution {
    public int maxSubArray(int[] nums) {
        int[] f = new int[nums.length];
        f[0]=nums[0];
        int ans = f[0];
        for (int i=1;i<nums.length;i++){
            f[i] = Math.max(nums[i],f[i-1]+nums[i]);
            ans  = Math.max(ans,f[i]);
        }    
        return ans;
    }
}
~~~

# Sword51 离散化树状数组

~~~
class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        // 离散化
        Arrays.sort(tmp);
        for (int i = 0; i < n; ++i) {
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        }
        // 树状数组统计逆序对
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            ans += bit.query(nums[i] - 1);
            bit.update(nums[i]);
        }
        return ans;
    }
}

class BIT {
    private int[] tree;
    private int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += tree[x];
            x -= lowbit(x);
        }
        return ret;
    }

    public void update(int x) {
        while (x <= n) {
            ++tree[x];
            x += lowbit(x);
        }
    }
}
~~~



# Interview_0101

~~~
class Solution {
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<Character>();
        for (int i=0;i<astr.length();i++){
            if (!set.add(astr.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
~~~



# Interview_1625

~~~
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
~~~

# Interview_0203

~~~
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode current = node;
        while (current.next.next!=null){
            current.val = current.next.val;
            current = current.next;
        }
        current.val = current.next.val;
        current.next = null;
        return ;
    }
}
~~~



# Interview_0205

~~~
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
~~~





8 40 103 135 132 sword20 sword51 402 117  145 743 127 273 218