class Solution {
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<Character>();
        for (int i=0;i<astr.length();i++){
            if (!set.add(astr.charAt(i))){
                return false;
            }
        }
        return true;
    }
}