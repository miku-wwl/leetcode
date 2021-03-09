class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack =new Stack<String>();

        
        for (String i: path.split("\\/+")){
            if (i.equals("")) continue;
            if (i.equals(".")) continue;
            if (i.equals("..")){
                if (stack.size()==0) continue;
                stack.pop();
                continue;
            }    
            stack.push(i);
        }
        
        if (stack.size()==0) return"/";
        
        List<String> list = new ArrayList<String>();
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        Collections.reverse(list);
        
        String str="";
        for (String s: list){
            str=str+"/"+s;
        }
        return str;
    }
}