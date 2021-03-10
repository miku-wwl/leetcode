class Solution {
    public String convertToTitle(int columnNumber) {
      String str = "";
      if (columnNumber<=26) return Character.valueOf((char)('A'+columnNumber-1)).toString();

       //Character c = new Character.valueOf('A');
       //char[] c = new char[100]; 
       

        while (columnNumber>26){
            int mod;
            mod = columnNumber % 26;
            if (mod == 0) {
                mod = 26;
                columnNumber = columnNumber / 26 -1;
            }else{
                columnNumber = columnNumber / 26;
            }
            str += (char) (mod + 'A' -1);
            
        }
        str += (char) (columnNumber + 'A' -1);

        str = new StringBuilder(str).reverse().toString();

        return str;
    }
}


