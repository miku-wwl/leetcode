class Solution {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(int[] nums, List<Integer> list ){
        if (list.size() == nums.length) {
            lists.add(list);
            return;
        }else{
            for (int i: nums){
                if (!list.contains(i)){

                    List<Integer> templist = new ArrayList<Integer>(list);
                    
                    templist.add(i);
                    find(nums,templist);
                }
            }
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        find(nums,list);
        return lists;
    }
}