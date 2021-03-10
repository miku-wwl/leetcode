/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

      public class Solution extends Relation {
        public int findCelebrity(int n) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i=0;i<n;i++) set.add(i);
    
            for (int i=0;i<n;i++)
                for (int j=0;j<n;j++)
                    if (i!=j){
                        if (knows(i,j)==true){
                            set.remove(i);
                            break;
                        }
                    }
    
            if (set.size()==0) return -1;
    
            Set<Integer> newset = new HashSet<Integer>(set);
            for (int i: set){
                for (int j=0;j<n;j++){
                    if (knows(j,i)==false){
                        newset.remove(i);
                    }
                }
            }
    
            set = newset;
    
            if (set.size()==0 || set.size()>1) return -1;
    
            for (int i: set){
                return i;  //在这里退出
            }
    
            return 0;
        }
    }