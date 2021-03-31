class Solution {
    Set<List<Integer>> lists = new HashSet<List<Integer>>();

    private void dfs(int current,int[] nums,List<Integer> list){
        if (current==nums.length){
            Collections.sort(list);
            lists.add(list);
        }else{
            List<Integer> templist1 = new ArrayList<Integer>(list);
            templist1.add(nums[current]);
            List<Integer> templist2 = new ArrayList<Integer>(list);

            dfs(current+1,nums,templist1);
            dfs(current+1,nums,templist2);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();

        if (nums.length==0)  return null;
        dfs(0,nums,list);

        List<List<Integer>> answer = new ArrayList<List<Integer>>();

        for (List<Integer> i: lists){
            answer.add(i);
        }

        return answer;
    }
}