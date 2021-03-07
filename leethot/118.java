class Solution {
    List<List<Integer>> answer = new ArrayList<List<Integer>>();
    List<Integer> list;
    public List<List<Integer>> generate(int numRows) {
        int[][] f = new int[numRows+1][numRows+1];
        
        f[1][1] = 1;
        list = new ArrayList<Integer>();
        list.add(1);
        answer.add(list);
        if (numRows==1) return answer;

        f[2][1] = 1;
        f[2][2] = 1;
        list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        answer.add(list);
        if (numRows==2) return answer;

        for (int i=3;i<=numRows;i++){
            f[i][1] = 1;
            f[i][i] = 1;
            for (int j=2;j<=i-1;j++){
                f[i][j] = f[i-1][j-1]+f[i-1][j];
            }
            list = new ArrayList<Integer>();
            for (int j=1;j<=i;j++){
                list.add(f[i][j]);
            }
            answer.add(list);
        }

        return answer;
    }
}