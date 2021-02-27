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