class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i:nums){
            if (!set.add(i)){
                return i;
            }
        }
        return 0;
    }
}