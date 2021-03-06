class Solution {
    public void sortColors(int[] nums) {
        int red=0;
        int white=0;
        int blue=0;
        for (int i: nums){
            switch (i){
                case 0: 
                    red+=1;
                    break;
                case 1:
                    white+=1;
                    break;
                default:
                    blue+=1; 
            }
        }

        int number = 0;
        for (int i=1;i<=red;i++) {nums[number]=0;number+=1;}
        for (int i=1;i<=white;i++) {nums[number]=1;number+=1;}
        for (int i=1;i<=blue;i++) {nums[number]=2;number+=1;}
    }
}