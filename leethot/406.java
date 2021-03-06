class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        boolean[] selected = new boolean[n];
        int[][] order = new int[n][2];
        int[][] newpeople = new int[n][2];

        for (int i=0;i<n;i++)
            for (int j=0;j<=1;j++)
                newpeople[i][j] = people[i][j];


        for (int i=0;i<n;i++){
            int x,y;
            x=99999999;
            y=99999999;
            for (int j=0;j<n;j++){
                if (!selected[j]){
                    if (people[j][1]<y){
                        x=j;
                        y=people[j][1];
                        continue;
                    }
                    if (people[j][1]==y && people[j][0]<people[x][0]){
                        x=j;
                        //y=people[j][1];
                        continue;
                    }
                }
            }
            order[i] = newpeople[x];
            selected[x] = true;

            for (int j=0;j<n;j++)
                if (!selected[j] && j!=x && people[j][0]<=people[x][0]){
                    people[j][1] -= 1;
                }

        }

        return order;

    }
}