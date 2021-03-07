class Solution {
    String str;
    int n;
    int strLength;
    String[] strs;
    private void deploy(int index, int local, int direction){
        if (strLength == index) return;

        int newDirection = direction;
        if (local == 0 ) newDirection = 1;
        if (local == n-1) newDirection = -1;

        strs[local] += str.charAt(index);
        deploy(index+1,local+newDirection,newDirection); 
        return;
    }
    
    public String convert(String s, int numRows) {
        if (numRows==1) {
            return s;
        }

        strs = new String[numRows];
        for (int i=0;i<numRows;i++){
            strs[i] =  "";
        }

        str = s;
        n = numRows;
        strLength = s.length();
        deploy(0,0,1);


        for (int i=1;i<numRows;i++){
            strs[0] += strs[i];
        }
        return strs[0];
    }
}