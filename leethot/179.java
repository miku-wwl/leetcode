class Solution {
    public String largestNumber(int[] nums) {
        
        String[] list = new String[nums.length];
        
        for (int i=0;i<nums.length;i++){
             list[i] = String.valueOf(nums[i]);
        }

        for (int i=0;i<list.length;i++)
            for (int j=i+1;j<list.length;j++){
                if ((list[i]+list[j]).compareTo((list[j]+list[i]))<0){
                    String temp;
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
            
        String ans ="";
        for (int i=0;i<list.length;i++){
            ans+=list[i];
        }

        boolean zero = true;
        for (int i=0;i<ans.length();i++){
            if (ans.charAt(i)!='0'){
                zero = false;
                break;
            }
        }
        if (zero == true) return "0";

        return ans;
    }
}