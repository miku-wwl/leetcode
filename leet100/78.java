class Solution {
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    private void find(int[] nums, int K,List<Integer> list){
        int n = nums.length;
        if (K==n){
            lists.add(list);
        }else{
            List<Integer> list1 = new ArrayList<Integer>(list);
            List<Integer> list2 = new ArrayList<Integer>(list);
            list2.add(nums[K]);

            find(nums,K+1,list1);
            find(nums,K+1,list2);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        find(nums,0,new ArrayList<Integer>());
        return lists;
    }
}