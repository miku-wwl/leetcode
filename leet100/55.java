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