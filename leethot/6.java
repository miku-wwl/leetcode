class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)  retrun s;

        String strs[] = new String[numRows];
        for (int i=0;i<strs.length();i++) strs[i]="";
        int index = 0;
        int direction = 1;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (index ==0) {
                direction = 1;
            }
            if (index ==numRows-1){
                direction = -1;
            }
            strs[index] += c;
            index += direction;
        }

        String ans = "";
        for (int i=0;i<numRows;i++){
            ans += strs[i];
        }

        return ans;
    }