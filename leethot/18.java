class Solution {

    Set<List<Integer>> set = new HashSet<List<Integer>>();
    List<List<Integer>> answer = new ArrayList<List<Integer>>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        for (int i=0;i<nums.length;i++)
            for (int j=i+1;j<nums.length;j++)
                for (int k=j+1;k<nums.length;k++)
                    for (int l=k+1;l<nums.length;l++){
                        if (nums[i]+nums[j]+nums[k]+nums[l] == target){
                            List<Integer> list = new ArrayList<Integer>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[l]);
                            Collections.sort(list);
                            
                            set.add(list);
                        }
                    }
        for (List<Integer> i: set){
            answer.add(i);
        }
        return answer;
    }
}