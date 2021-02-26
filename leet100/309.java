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