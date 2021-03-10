class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    board[i][j] = '.';
                    int a = i + 1;
                    int b = j;
                    // 遍历行
                    while (a < board.length && board[a][b] == 'X') {
                        board[a++][b] = '.';
                    }
                    a = i;
                    b = j + 1;
                    // 遍历列
                    while (b < board[0].length && board[a][b] == 'X') {
                        board[a][b++] = '.';
                    }
                }
            }
        }
        return count;
    }
}