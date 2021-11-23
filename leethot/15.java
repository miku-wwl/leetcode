 class Solution{
  public static List<List<Integer>> threeSum(int[] nums) {

        if (nums.length<3) return new ArrayList<List<Integer>>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Map<Integer,Integer> third = new HashMap<Integer,Integer>();

        for (int num : nums) {
            third.put(num,third.getOrDefault(num,0)+1);
        }

       // System.out.println(third);

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++) {
                int x = -1*(nums[i]+nums[j]);
                if (third.getOrDefault(x,0)<=0){
                    continue;
                }
                if (x==nums[i] && third.get(nums[i])<2){
                    continue;
                }
                if (x==nums[j] && third.get(nums[j])<2){
                    continue;
                }
                if (x==nums[j] && x==nums[i] && third.get(nums[j])<3){
                    continue;
                }


                List<Integer> list = new ArrayList<Integer>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(x);
                Collections.sort(list);
                if (set.contains(list)) {
                    continue;
                } else {
                    ans.add(list);
                    set.add(list);
                }
            }
        return ans;
  }
  }