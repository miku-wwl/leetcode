class Solution {
    int answer = 0;
    public int minMeetingRooms(int[][] intervals) {
        for (int i=0;i<intervals.length;i++){
            int count = 1;
            for (int j=0;j<intervals.length;j++)
                if (i!=j){
                    if ((intervals[i][0]<intervals[j][1] && intervals[i][0]>=intervals[j][0]) || 
                        (intervals[i][1]<intervals[j][1] && intervals[i][0]>intervals[j][0])){
                        count++;
                    }
                }
            answer =Math.max(answer,count);    
        }
        return answer;
    }
}