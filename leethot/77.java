class Solution {
    List<List<Integer>> answer = new ArrayList<List<Integer>>();
    int _n;
    int _k;

    private void find(int carry, int[] a){
        if (carry==_k-1){
            List<Integer> list = new ArrayList<Integer>();
            for (int i: a){
                list.add(i);
            }
            answer.add(list);
            return;
        }else{
            if (carry==-1){
                for (int i=1;i<=_n;i++){
                    a[carry+1]=i;
                    find(carry+1,a);
                }
            }else{
                for (int i=a[carry]+1;i<=_n;i++){
                    a[carry+1]=i;
                    find(carry+1,a);

                }
            }
        }
    }
    public List<List<Integer>> combine(int n, int k) {
        int[] a = new int[k];
        _n = n;
        _k = k;

        find(-1,a);
        return answer;
    }
}