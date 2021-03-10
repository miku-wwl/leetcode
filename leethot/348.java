class TicTacToe {
    int n;
    int[][] rows;
    int[][] columns;
    int[][] diagonals;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[3][n]; // 3 表示 player1 和 player2，索引 0 是无用的
        columns = new int[3][n];
        diagonals = new int[3][2];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (++rows[player][row] == n) {
            // 某玩家在在第 row 行上放了 n 个棋子
            return player;
        }
        if (++columns[player][col] == n) {
            // 某玩家在在第 col 列上放了 n 个棋子
            return player;
        }
        if (row == col && ++diagonals[player][0] == n) {
            // 某玩家在在正对角线上上放了 n 个棋子
            return player;
        }
        if ((row + col == n - 1) && ++diagonals[player][1] == n) {
            // 某玩家在负对角线上放了 n 个棋子
            return player;
        }

        return 0;
    }
}
/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */