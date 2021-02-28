import java.util.ArrayList;
import java.util.List;

public class ListParameterTest {
    static List<Integer> list = new ArrayList<Integer>();
    static List<Integer> list2 = new ArrayList<Integer>();
    private static void listtest(List<Integer> list, int n) {
        if (n==5) return;
        list.add(n);
        System.out.println(list);

        List<Integer> temp = new ArrayList<Integer>(list);
        listtest(temp,n+1);
        System.out.println(list);
    }

    private static void list2test(List<Integer> list2, int n) {
        if (n==5) return;
        list2.add(n);
        System.out.println(list2);
        list2test(list2,n+1);
        System.out.println(list2);
    }


    public static void main(String[] args) {

        listtest(list, 1);
        list2test(list2,1);
        return;
    }
}
