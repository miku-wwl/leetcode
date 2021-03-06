class Solution {
    public int reverse(int x) {
        int mark=1;
        Long longX = new Long(x);
        if  (longX<0){
            mark=-1;
            longX=-longX;
        }

        String str = longX.toString();
        str = new StringBuilder(str).reverse().toString();

        Long longint = Long.parseLong(str);

        if (longint*mark<-Math.pow(2,31) || longint*mark>Math.pow(2,31)-1) return 0;
        
        return mark*Integer.parseInt(str);
    }
}