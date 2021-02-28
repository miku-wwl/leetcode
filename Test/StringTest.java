public class StringTest {
    public static void main(String args[]){
        StringBuilder sb = new StringBuilder(10);
        sb.append("Runoob..");
        System.out.println(sb);  
        sb.append("!");
        System.out.println(sb); 
        sb.insert(8, "Java");
        System.out.println(sb); 
        sb.delete(5,8);
        System.out.println(sb);  
        System.out.println(sb.reverse());  
        sb.deleteCharAt(0);
        System.out.println(sb);  

        StringBuffer sbuff = new StringBuffer();
        sbuff.append("Runoob..");
        System.out.println(sbuff);  
        sbuff.append("!");
        System.out.println(sbuff); 
        sbuff.insert(8, "Java");
        System.out.println(sbuff); 
        sbuff.delete(5,8);
        System.out.println(sbuff);  
        System.out.println(sbuff.reverse());  
        sbuff.deleteCharAt(0);
        System.out.println(sbuff);  

        
        String str;
        str = String.valueOf(false);
        System.out.println(str);
        str = String.valueOf('s');
        System.out.println(str);
        char[] data = new char[6];
        data[0]='s'; data[1]='t'; data[2]='r'; data[3]='i'; data[4]='n'; data[5]='g';
        str = String.valueOf(data);
        System.out.println(str);
        str = String.valueOf(data,1,2);
        System.out.println(str);
        str = String.valueOf(132132.222);
        System.out.println(str);
        str = String.valueOf(132113.6666666);
        System.out.println(str);
        str = String.valueOf(123154);
        System.out.println(str);
        str = String.valueOf((long) 546456465*11000000);
        System.out.println(str);

        System.out.println(Long.parseLong(str));

        return;
    }
}
