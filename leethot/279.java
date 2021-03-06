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