class Solution {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        String str = new Integer(x).toString();
        String str2 = new StringBuilder(str).reverse().toString();
        if (str.equals(str2)) return true;
        return false;
    }
}