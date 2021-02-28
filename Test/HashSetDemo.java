import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> sites = new HashSet<String>();
            sites.add("Google");
            sites.add("Runoob");
            sites.add("Taobao");
            sites.add("Zhihu");
            sites.add("Runoob");  // 重复的元素不会被添加
            System.out.println(sites.size());
            System.out.println(sites);

            for (String s : sites){
                System.out.println(s);
            }
            sites.remove("Taobao");
            System.out.println(sites);
            sites.clear();  
            System.out.println(sites);
        }
}
