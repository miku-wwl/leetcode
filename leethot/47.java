class Solution {
    Set<List<Integer>> set = new HashSet<List<Integer>>();
    int length;
    int[] _nums;
    private void dfs(int current,int[] a){
        if (current==length-1){
            List<Integer> list = new ArrayList<Integer>();
            for (int i : a){
                list.add(_nums[i]);
            }
            set.add(list);
            return;
        }else{
            int[] check = new int[length];
            for (int i=0;i<=current;i++){
            check[a[i]] = 1;
            }

            for (int i=0;i<length;i++)
                if (check[i]==0){
                    a[current+1]=i;
                    dfs(current+1,a);
                }
    

        
            return;    
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        length = nums.length;
        _nums = nums;
        int[] a = new int[length];

        dfs(-1,a);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        for (List<Integer> i:set){
            answer.add(i);
        }
        return answer;
    }
}