import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        // 创建 HashMap 对象 Sites
        HashMap<String, String> Sites = new HashMap<String, String>();
        // 添加键值对
        Sites.put("1", "Google");
        Sites.put("2", "Runoob");
        Sites.put("3", "Taobao");
        Sites.put("4", "Zhihu");
        System.out.println(Sites.size());
        Sites.remove("3");
        System.out.println(Sites);

        for (String i: Sites.keySet()){
            System.out.println(i);
            System.out.println(Sites.get(i));
        }
        for (String i: Sites.values()){
            System.out.println(i);
        }


        HashMap<String, String> CloneSites = (HashMap<String, String>) Sites.clone();

        Sites.clear();
        System.out.println(Sites);
        System.out.println(CloneSites);


        System.out.println(CloneSites.containsKey("1"));	
        System.out.println(CloneSites.containsKey("3"));

        CloneSites.replace("1", CloneSites.get("1"), "123");

        System.out.println(CloneSites);
    }
}
