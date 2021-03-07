class Solution {
    String str;
    Set<String> set = new HashSet<String>();

    private void arrangement(int carry, int[] a){
        if (carry == str.length()-1){
            String tempString = "";
            for (int i=0;i<str.length();i++){
                tempString += str.charAt(a[i]);
            }
            set.add(tempString);
            return;
        }

        int[] check = new int[str.length()];
        for (int i=0;i<=carry;i++){
            check[a[i]] = 1;
        }

        for (int i=0;i<str.length();i++)
            if (check[i] == 0){
                a[carry+1]=i;
                arrangement(carry+1,a);
            }
        return;    
    }
    public String[] permutation(String s) {
        str = s;
        int[] a = new int[s.length()];
        arrangement(-1,a);

        String[] strs = new String[set.size()];
        int index = -1;
        for (String strinSet: set){
            index++;
            strs[index] = strinSet;
        }
        
        return strs;
    }
}