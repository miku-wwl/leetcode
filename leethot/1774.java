class Solution {
    int n;
    int m;
    int ans = 9999999;
    int temp;
    int _toppingCosts[];

    private void find(int current,int sum,int target){
        if(current == m){
            if (Math.abs(sum+temp-target)<Math.abs(ans-target)){
                // System.out.println(sum);
                // System.out.println(temp);
                ans = sum + temp;
                return ;
                
            }
            if (Math.abs(sum+temp-target)==Math.abs(ans-target) && sum+temp<ans){
                ans = sum + temp;
                return ;
            }
        }else{
            //System.out.println(current);
            find(current+1,sum,target);
          //  System.out.println(sum);
            find(current+1,sum+_toppingCosts[current],target);
          //  System.out.println(sum+_toppingCosts[current]);
            find(current+1,sum+2*_toppingCosts[current],target);
          //  System.out.println(sum+2*_toppingCosts[current]);
        }
    }
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        n = baseCosts.length;
        m = toppingCosts.length;
        int[] num = new int[m];
        _toppingCosts = toppingCosts;

        for (int i=0;i<n;i++){
            

            temp = baseCosts[i];
            find(0,0,target);
        }

        return ans;
    }
}