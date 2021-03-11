class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (6*n<m) return -1;
        if (6*m<n) return -1;
        int sum1 = 0;
        int sum2 = 0;
        for (int i:nums1){
            sum1+=i;
        }
        for (int i:nums2){
            sum2+=i;
        }

        if (sum1==sum2) return 0;

        if (sum1<sum2){
            int[] tempArray;
            int temp;

            tempArray = nums1;
            nums1 = nums2;
            nums2 = tempArray;

            temp = sum1;
            sum1 = sum2;
            sum2 = temp; 
        } 
        PriorityQueue<Integer> n2 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> n1 = new PriorityQueue<Integer>((a,b)->b-a);
        
        for (int i:nums1){
            n1.add(i);
        }
        for (int i:nums2){
            n2.add(i);
        }
        int ans = 0;
        while (sum2<sum1){
            int x = n1.peek();
            int y = n2.peek();
            if ( (x-1) > (6-y) ){
                sum1 = sum1 + 1 -x;
                n1.poll();
                n1.add(1);
            }else{
                sum2 = sum2 - y+6;
                n2.poll();
                n2.add(6);
            }
            ans++;
        }
        return ans;
    }
}