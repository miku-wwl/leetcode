class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++){
                if (obstacleGrid[i][j]==1){
                    f[i][j]=0;
                  //  System.out.print(f[i][j]);
                    continue;
                }      
                if (!(i==0&&j==0)){
                    int x=0;
                    int y=0;
                    x = i-1>=0?f[i-1][j]:0;
                    y = j-1>=0?f[i][j-1]:0;
                    f[i][j]=x+y;

                //    System.out.print(f[i][j]);
                }
                
            }
        return f[m-1][n-1];
    }
}