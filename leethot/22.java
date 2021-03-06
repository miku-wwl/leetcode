class Solution {
    List<String> strs = new ArrayList<String>();

    private void find(int leftNumber,int rightNumber, String str){
        String string ="";
        if (leftNumber == rightNumber && 0 == leftNumber){
            strs.add(str);
            return;
        }
        if (leftNumber == rightNumber){
            find(leftNumber-1,rightNumber,str+"(");
            return;
        }
        if (leftNumber == 0){
            find(leftNumber,rightNumber-1,str+")" );
            return;
        }
        find(leftNumber-1,rightNumber,str+"(");
        find(leftNumber,rightNumber-1,str+")" );
        return;
    }

    
    public List<String> generateParenthesis(int n) {
        int leftNumber=n;
        int rightNumber=n;
        String str = "";
        find(leftNumber,rightNumber,str);
        return strs;
    }
}