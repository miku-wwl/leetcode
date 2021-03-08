class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] f = new int[text1.length()+1][text2.length()+1];
        for (int i = 1;i<=text1.length();i++)
            for (int j = 1;j<=text2.length();j++){
                int equal=0;
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    equal=1;
                }
                f[i][j] = Math.max(f[i-1][j-1]+equal,f[i][j]);
                f[i][j] = Math.max(f[i-1][j],f[i][j]);
                f[i][j] = Math.max(f[i][j-1],f[i][j]);
            }
        return f[text1.length()][text2.length()];
    }
}