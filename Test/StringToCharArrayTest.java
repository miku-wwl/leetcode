import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringToCharArrayTest {
    public static void main(String[] args){
        Set<String> set =new HashSet<String>();
        
        String str ="gfedcba";
        char[] chars =str.toCharArray();
        Arrays.sort(chars);
        str = String.valueOf(chars);

        set.add(str);

        for (String s:set){
            System.out.println(s);
       }

        System.out.println(chars);
    }
}
