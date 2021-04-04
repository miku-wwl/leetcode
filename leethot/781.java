class Solution {
    public int numRabbits(int[] answers) {
        int answer=0;
        int[] f = new int[1010];
        for (int i : answers){
            if (i==0) {
                answer++;
                
            }else{
                f[i]++;
                if (f[i]==(i+1)){
                    answer += (i+1);
                    f[i] = 0;
                }
            }
        
        }
        for (int i=0;i<1000;i++){
            if (f[i]!=0){
                answer+=(i+1);
            }
            
        }
        return answer;
    }
}