class Solution {
    Set<Integer> set1 = new HashSet<Integer>();
    Set<Integer> set2 = new HashSet<Integer>();

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i:nums1){
            set1.add(i);
        }
        for (int i:nums2){
            set2.add(i);
        }
        for (int i:set1){
            if (set2.contains(i)){
                list.add(i);
            }
        }

        int[] ans = new int[list.size()];
        for (int i=0;i<list.size();i++){
            ans[i]=list.get(i);
        }
        return  ans;
    }
}