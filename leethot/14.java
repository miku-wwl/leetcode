class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";

        int miniLength=9999;
        for (String str : strs){
            miniLength = Math.min(miniLength,str.length());
        }

        String answer = "";
 
        for (int i=0;i<miniLength ;i++){
            char c = strs[0].charAt(i);
            for (String str : strs){
                char strC = str.charAt(i);
                if (c!=strC){
                    return answer;
                }
            }

                answer += c;
          
        }

        return answer;
    }
}