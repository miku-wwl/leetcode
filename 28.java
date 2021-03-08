class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        int answer = -1;
        for (int i=0;i<=haystack.length()-needle.length();i++){
            if (needle.equals(haystack.substring(i,i+needle.length()))){
                answer = i;
                break;
            }
        }
        return answer;
    }
}