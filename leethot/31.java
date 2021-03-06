class Solution {
    public void nextPermutation(int[] nums) {
        boolean check = true;
        int n = nums.length;
        int point=0;
        for (int i=0;i<n-1;i++){
            if (nums[i]<nums[i+1]){
               check = false;
               break;
            }
        }
        if (check) {
            Arrays.sort(nums);
            return;
        }


        Stack<Integer> stack = new Stack<Integer>();
        stack.push(n-1);

        for (int i=n-2;i>=0;i--){

            if (nums[i]>=nums[stack.peek()]){
                stack.push(i);
                continue;
            }

            int store = 0;
            while (!stack.isEmpty() && nums[i]<nums[stack.peek()]){
               store = stack.peek();
               stack.pop();
            }

            int temp = nums[i];
            nums[i] = nums[store];
            nums[store] = temp;
            point = i;
            break;
        }

        for (int i=point+1;i<n-1;i++)
            for (int j=i+1;j<n;j++){
                if (nums[i]>nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
    }
}




