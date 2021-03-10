class Solution {
    List<String> _arr;
    int answer = -1;

    private void find(int current,int length, List<String> list){
        if (current == _arr.size()){
            answer = Math.max(answer,length);
        }else{
            Set<Character> set = new HashSet<Character>();
            for (String s :list)
                for (int i=0;i<s.length();i++){
                    set.add(s.charAt(i));
                }
            String currentString = _arr.get(current);
            boolean check = true;
            for (int i=0;i<currentString.length();i++)
                if (!set.add(currentString.charAt(i))){
                    check = false;
                    break;
                }
            if (check ==true){
                List<String> tempList = new ArrayList<String>(list);
                tempList.add(currentString);
                find(current+1,length+currentString.length(),tempList);
            }    
            find(current+1,length,new ArrayList<String>(list));
        }   
            
        return ;
    }

    public int maxLength(List<String> arr) {
        _arr = arr;
        find(0,0,new ArrayList<String>());
        return answer;
    }
}