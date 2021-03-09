class Solution {
    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        List<String> list = new ArrayList<String>();

        if (length>12) return list;
        
        for (int i=0;i<length;i++){
            if (s.charAt(i)>'9' || s.charAt(i)<'0' )
            return list;
        }

        for (int i=0;i<length-1;i++)
            for (int j=i+1;j<length-1;j++)
                for (int k=j+1;k<length-1;k++){

                    //增加"."
                    String str = "";
                    for (int index = 0;index<length;index++){                
                        if (index == i) {
                            str = str+s.charAt(index)+".";
                            continue;
                        }
                        if (index == j) {
                            str = str+s.charAt(index)+".";
                            continue;
                        }
                        if (index == k){
                            str = str+s.charAt(index)+".";
                            continue;
                        } 
                        str = str+s.charAt(index);
                    }
                    //分离"."
                    String[] splits = str.split("\\.");
                    boolean check = true;
                    for (String splitstring: splits){

                        if (splitstring.length() > new Long(Long.parseLong(splitstring)).toString().length()){
                            check = false;
                            break;
                        };
                        if (Long.parseLong(splitstring)>255){
                            check = false;
                            break;
                        }
                    }
                    //增加答案
                    if (check ==true){
                        list.add(str);
                    }
                }
        return list;        
    }
}