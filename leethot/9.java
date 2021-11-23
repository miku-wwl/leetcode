class Solution {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        String str = new Integer(x).toString();
        String str2 = new StringBuilder(str).reverse().toString();
        if (str.equals(str2)) return true;
        return false;
    }
}


class Solution {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        String s1 = new Integer(x).toString();
        String s2 = new StringBuilder(s1).reverse().toString();
        if (s1.equals(s2)) return true;
        return false;
    }
}