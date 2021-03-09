class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<Character>();
        char[] charArray = S.toCharArray();
        for (char c : charArray){
            if (stack.isEmpty()){
                stack.push(c);
                continue;
            }
            if (stack.peek()==c){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        String str = new String();
        while (!stack.isEmpty()){
            str+=stack.pop();
        }
        
        return new String(new StringBuilder(str).reverse());
    }
}