class Solution {
    public String multiply(String num1, String num2) {
        String temp;
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.length()<num2.length()){
            temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int[] a = new int[num1.length()];
        int[] b = new int[num2.length()];
        int[] c = new int[num1.length()*2+1];

        for (int i=0;i<num1.length();i++){
            a[i]=num1.charAt(num1.length()-1-i)-48;
        }
        for (int i=0;i<num2.length();i++){
            b[i]=num2.charAt(num2.length()-1-i)-48;
        }

        for (int j=0;j<b.length;j++)
            for(int i=0;i<a.length;i++){
                c[i+j] += a[i]*b[j];
                c[i+j+1] += c[i+j] /10;
                c[i+j] %=10;

            }
        for (int i=0;i<c.length-1;i++){
            c[i+1] += c[i] /10;
            c[i] %=10;
        }
        String str = "";
        boolean t = true;
        for (int i=c.length-1;i>=0;i--){
            if (c[i]==0 && t==true){
                continue;
            }else{
                t=false;
                str+=c[i];
            }
        }

        return str;

    }
}