

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

