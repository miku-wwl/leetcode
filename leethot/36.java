public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<Character>();
        
        for (int i=0;i<9;i++)
            {
                set.clear();
                for (int j=0;j<9;j++){
                    if (board[i][j]!='.' && !set.add(board[i][j])) {
                        return false;
                    }
                }
            }
        for (int j=0;j<9;j++){
            set.clear();
            for (int i=0;i<9;i++){
                if (board[i][j]!='.' && !set.add(board[i][j])) {
                    return false;
                }
            }
        }

        for (int i=0;i<9;i=i+3)
            for (int j=0;j<9;j=j+3){
                set.clear();
                for (int k=i;k<=i+2;k++)
                    for (int l=j;l<=j+2;l++){
                        if (board[k][l]!='.' && !set.add(board[k][l]))
                            return false;
                    }

            }
        return true;          


        // (0,0) (2,2)
        // (3,0) (5,2)
        // (6,0) (8,2)

        // (0,3) (2,5)
        // (3,3) (5,5)
        // (6,3) (8,5)

        // (0,6) (2,8)
        // (3,6) (5,8)
        // (6,6) (8,8)



    }
}