class Solution {
    public int compress(char[] chars) {
        char current;
        int num;
        String str="";
        current = chars[0];
        num = 1;

        for (int i=1;i<chars.length;i++){
            if (current == chars[i]){
                num++;
            }else{
                if (num==1){
                    str += current;
                }else{
                    str = str+ current + num;
                }
                current = chars[i];
                num=1;
            }
        }

        if (num==1){
            str += current;
        }else{
            str = str+current+num;
        }

        for (int i=0;i<str.length();i++){
            chars[i] = str.charAt(i);
        }

        
        return str.length();


    }
}