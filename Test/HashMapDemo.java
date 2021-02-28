import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {

        HashMap<String, String> Sites = new HashMap<String, String>();

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




         HashMap<String, Integer> prices = new HashMap<>();

         prices.put("Shoes", 200);
         prices.put("Bag", 300);
         prices.put("Pant", 150);
         System.out.println("HashMap: " + prices);
 
         int newPrice = prices.compute("Shoes", (key, value) -> value - value * 10/100);
         System.out.println("Discounted Price of Shoes: " + newPrice);
 

         System.out.println("Updated HashMap: " + prices);


         HashMap<String, Integer> test = new HashMap<>();
         test.put("1",1);
         System.out.println(test);
         if (test.containsKey("1")){
            test.put("1",test.get("1")+5);
         }
         
         System.out.println(test);
    }
}
