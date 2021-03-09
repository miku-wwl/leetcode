class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map1 =new HashMap<Character,Character>();
        Map<Character,Character> map2 =new HashMap<Character,Character>();

        if (s.length()!=t.length()) return false;
        for (int i=0;i<s.length();i++){
            char x,y;
            x=s.charAt(i);
            y=t.charAt(i);
            if (!map1.containsKey(x)){
                map1.put(x,y);
            }else{
                if (y!=map1.get(x)){
                    return false;
                }
            }

            if (!map2.containsKey(y)){
                map2.put(y,x);
            }else{
                if (x!=map2.get(y)){
                    return false;
                }
            }
        }
        return true;
    }
}