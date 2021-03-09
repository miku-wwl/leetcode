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