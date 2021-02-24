class Solution {
    public int trap(int[] height) {
        int n=height.length;
        if (n==0) return 0;
        int[] leftwall = new int[n];
        int[] rightwall = new int[n];
      
        leftwall[0]=height[0];
        rightwall[n-1]=height[n-1];
        for (int i=1;i<n-1;i++)  leftwall[i] = Math.max(height[i],leftwall[i-1]);
        for (int i=n-2;i>0;i--)  rightwall[i] = Math.max(height[i],rightwall[i+1]);

        for (int i=1;i<n-1;i++)  rightwall[i] = Math.min(leftwall[i],rightwall[i]);
        int ans=0;
        for (int i=1;i<n-1;i++)  ans+=rightwall[i]-height[i];
        return ans;     
    }
}