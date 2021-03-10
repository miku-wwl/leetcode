class Solution {
    int m;
    int n;
    char[][] f;
    int [][] block;
    Set<String> set = new HashSet<String>();
    List<String> list = new ArrayList<String>();


    private void find(int x,int y , String word, int position){
        if (x>=0 && x<=m-1 && y>=0 && y<=n-1 && block[x][y]==0){
            if (position==word.length()-1){
                if (f[x][y]==word.charAt(position)){
                set.add(word);
                }
            }else{
                if (f[x][y]==word.charAt(position)){
                    block[x][y] = 1;
                    find(x+1,y,word,position+1);
                    find(x-1,y,word,position+1);
                    find(x,y+1,word,position+1);
                    find(x,y-1,word,position+1);
                    block[x][y] = 0;
                }
            }
        }
        
    }
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        f = board;
        block = new int[m][n];

        for (String word : words){
            char first = word.charAt(0);
            for (int i=0;i<m;i++)
                for (int j=0;j<n;j++)

                    if (first == board[i][j]){                       
                        find(i,j,word,0);
                    }
        }

        for (String i: set){
            list.add(i);
        }
        
        return list;
    }
}