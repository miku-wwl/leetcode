class Solution {
    public int[] countBits(int num) {

        int[] f = new int[num+1];

        f[0]=0;
        for(int i = 0; i<num/2;i++){

            f[2*i] = f[i];
            f[2*i+1] = f[i] +1;
        }
        if (num%2 == 0){
            f[num] = f[num/2];
        }else{
            f[num-1] = f[num/2];
            f[num] = f[num/2]+1; 
        }
        return f;
    }
}