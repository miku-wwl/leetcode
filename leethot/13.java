class Solution {
    public int romanToInt(String s) {
        boolean t = true;
        while(t){
            boolean f;
            f=false;
            for (int i=0;i+1<s.length();i++){
                if (s.charAt(i) =='I' && s.charAt(i+1) =='V'){
                    s = s.substring(0,i) + "IIII"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='I' && s.charAt(i+1) =='X'){
                    s = s.substring(0,i) + "VIIII"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='X' && s.charAt(i+1) =='L'){
                    s = s.substring(0,i) + "XXXX"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='X' && s.charAt(i+1) =='C'){
                    s = s.substring(0,i) + "LXXXX"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='C' && s.charAt(i+1) =='D'){
                    s = s.substring(0,i) + "CCCC"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
                if (s.charAt(i) =='C' && s.charAt(i+1) =='M'){
                    s = s.substring(0,i) + "DCCCC"+s.substring(i+2,s.length());
                    f=true;
                    break;
                }
            }
            t=f;
        }
    int ans = 0;
    for (int i=0;i<s.length();i++){
        if (s.charAt(i) == 'I'){
            ans+=1;
        }
        if (s.charAt(i) == 'V'){
            ans+=5;
        }
        if (s.charAt(i) == 'X'){
            ans+=10;
        }
        if (s.charAt(i) == 'L'){
            ans+=50;
        }
        if (s.charAt(i) == 'C'){
            ans+=100;
        }
        if (s.charAt(i) == 'D'){
            ans+=500;
        }
        if (s.charAt(i) == 'M'){
            ans+=1000;
        }
    }
    return ans;
    }
}


// IV = IIII
// IX = VIIII
// XL = XXXX
// XC = LXXXX
// CD = CCCC
// CM = DCCCC
