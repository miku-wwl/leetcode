class Solution {
    public boolean canPartition(int[] nums) {
        int Sum=0;
        for (int i: nums){
            Sum += i;
        }
        if (Sum % 2 ==1) return false;

        Set<Integer> set = new HashSet<Integer>();
        set.add(0);

        int[] x = new int[20001];
        x[0]=0;
        int xLength=1;

        for (int i : nums){
            int xLengthIncrease;
            xLengthIncrease = 0;
            for (int j=0;j<xLength;j++)
                if (!set.contains(i+x[j])){
                    set.add(i+x[j]);
                    xLengthIncrease +=1;
                    x[xLength+xLengthIncrease-1] = (i+x[j]);
                }
            xLength += xLengthIncrease; 
        }

        return set.contains(Sum/2);

    }
}