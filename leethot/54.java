class Solution {
    int[][] map;
    int[][] _matrix;
    
    int[] directX={0,1,0,-1};
    int[] directY={1,0,-1,0};
    
    int m,n;
    List<Integer> list =new ArrayList<Integer>();
    
    private void find(int x, int y,int direct){
        map[x][y] = 1;
        list.add(_matrix[x][y]);
        if (x+directX[direct]>=m || x+directX[direct]<0 || y+directY[direct]>=n || y+directY[direct]<0){
            direct = (direct+1)%4;
        }else if(map[x+directX[direct]][y+directY[direct]] == 1){
            direct = (direct+1)%4;
        }
        if (x+directX[direct]<m && x+directX[direct]>=0 && y+directY[direct]<n && y+directY[direct]>=0 && map[x+directX[direct]][y+directY[direct]] == 0){
            find(x+directX[direct],y+directY[direct],direct);
        }
        
    } 
    public List<Integer> spiralOrder(int[][] matrix) {
        _matrix = matrix;

        m=matrix.length;
        n=matrix[0].length;
        map = new int[m][n];
        find(0,0,0);

        return list;

    }
}