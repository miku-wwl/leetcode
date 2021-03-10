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