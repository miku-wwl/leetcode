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