import java.util.ArrayList;
import java.util.Collections;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        for (String s : sites){
            System.out.println(s);
        }

        System.out.println(sites);
        sites.set(2,"wiki");
        System.out.println(sites.get(2));
        sites.remove(1);
        System.out.println(sites.get(2));
        System.out.println(sites.size());

        System.out.println(sites);
        Collections.sort(sites);
        System.out.println(sites);
    }   
}
