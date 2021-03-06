class Solution {
    public int findDuplicate(int[] nums) {
        Set<Integer> hash = new HashSet<Integer>();
        for (int i : nums){
            if (!hash.add(i)) {
                return i;
            }
        }
        return 0;
    }
}