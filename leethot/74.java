public class 74 {
    
}
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int column=0;
        for (int i=0;i<m;i++){
            if (matrix[i][n-1]>=target){
                column = i;
                break;
            }
        }
        boolean check = false;
        for (int i=0;i<n;i++){
            if (matrix[column][i] == target){
                check = true;
            }
        }
        return check;
    }
}