import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Student{
    public int score;
    Student(int score){
        this.score = score;
    }
    @Override
    public String toString() {
        return "StudentScore:" + score;
    }
}

public class ComparatorTest {
    public static void main(String[] args){
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
             @Override
             public int compare(Integer a, Integer b) {
                
                 return b-a;
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        queue.add(1);
        queue.add(2);
        queue.add(3);

        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        
        System.out.println(queue.peek());
        System.out.println(maxHeap.peek());

        PriorityQueue<Student> studentpq = new PriorityQueue<>((a, b) -> b.score - a.score);
        studentpq.add(new Student(99));
        studentpq.add(new Student(199));
        studentpq.add(new Student(299));
        System.out.println(studentpq.peek().toString());
        
        Integer[] x = new Integer[5];
        x[0] =0; x[1]=1; x[2] =2; x[3]=3; x[4] =4;

        Arrays.sort(x, (a,b) -> b-a);
        for(int i:x){
            System.out.print(i);
        }        
    
    }
}


