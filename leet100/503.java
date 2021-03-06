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