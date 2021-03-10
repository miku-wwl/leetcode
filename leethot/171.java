class Solution {
    public int titleToNumber(String columnTitle) {
        String str = new StringBuilder(columnTitle).reverse().toString();
        int carry = 1;
        int sum = 0;
        for (int i=0;i<str.length();i++){
            sum+=carry*(str.charAt(i)-'A'+1);
            carry*=26;
        }
        return sum;
    }
}