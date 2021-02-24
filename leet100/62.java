class Solution {
    public int uniquePaths(int m, int n) {
        m=m-1;
        n=n-1;
        if (m>n){
            int temp = m;
            m= n;
            n= temp;
        }

        int k=m+n;
        long ans=1;
        for (int i=n+1;i<=k;i++) ans*=i;
        for (int i=1;i<=m;i++) ans/=i;
        return (int) ans;
    }
}