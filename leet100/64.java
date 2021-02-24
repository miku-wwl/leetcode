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