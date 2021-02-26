class Solution {
    public void moveZeroes(int[] nums) {
        int indexNow = 0;
        int indexNum = 0;
        int m = nums.length;

        while(indexNum<m){
            if(nums[indexNum] != 0) {
                nums[indexNow++] = nums[indexNum];
            }
            ++indexNum;
        }

        for(int i = indexNow; i < m; i++){
            nums[i] = 0;
        }
    }
}