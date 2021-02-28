import java.util.ArrayList;
import java.util.List;

public class ArrayParameterTest {

    static int[] array1 = new int[5];
    static int[] array2 = new int[5];
    static List<Integer> list2 = new ArrayList<Integer>();

    private static void array1test(int[] array, int n) {
        if (n==5) return;
        array[n]=n;
        int[] temp = (int[]) array.clone();
        array1test(temp,n+1);
        }

    private static void array2test(int[] array, int n) {
        if (n==5) return;
        array[n]=n;

        array2test(array,n+1);
    }



    public static void main(String[] args) {

        array1test(array1, 0);
        array2test(array2,0);

        for (int i: array1){
            System.out.print(i);
        }
        System.out.println();

        for (int i: array2){
            System.out.print(i);
        }
        System.out.println();

        return;
    }
}
