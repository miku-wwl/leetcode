class Solution {
    public String addStrings(String num1, String num2) {
        String temp;
        if (num1.length()<num2.length()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int[] a = new int[num1.length()+1];
        int[] b = new int[num1.length()+1];

        for (int i = num1.length()-1;i>=0;i--){
            a[num1.length()-i-1] = num1.charAt(i)-48;
        }
           
        for (int i = num2.length()-1;i>=0;i--){
            b[num2.length()-i-1] = num2.charAt(i)-48;
        }
        


        for (int i = 0;i<num1.length();i++){
            a[i]+=b[i];
            if (a[i]>=10){
                a[i]-=10;
                a[i+1]+=1;
            }
        }

        String answer = "";
        if (a[num1.length()]!=0){
            answer += (char) (a[num1.length()] + 48);
        }
        for (int i = num1.length()-1;i>=0;i--){
            answer += (char) (a[i] + 48);
        }
       return answer;
    }
}