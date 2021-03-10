class Solution {
    public int compareVersion(String version1, String version2) {
        int v1_point=0;
        int v2_point=0;
        int mark = 1;
        for (int i=0;i<version1.length();i++){
            if (version1.charAt(i)=='.'){
                v1_point++;
            }
        }
        for (int i=0;i<version2.length();i++){
            if (version2.charAt(i)=='.'){
                v2_point++;
            }
        }
        if (v1_point<v2_point){
            String tempStr = version1;
            version1 = version2;
            version2 = tempStr;
            int tempInt = v1_point;
            v1_point = v2_point;
            v2_point = tempInt;

            mark = -1;
        }
        //System.out.println(v1_point-v2_point);
        for (int i=1;i<=(v1_point-v2_point);i++){
            version2+=".0";
        }
        
        // System.out.println(version1);
        // System.out.println(version2);

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int answer = 0;
        for (int i=0;i<v1.length;i++){
            Integer x = Integer.parseInt(v1[i]);
            Integer y = Integer.parseInt(v2[i]);
            if (x>y) return 1*mark;
            if (x<y) return -1*mark;
        } 
        return 0;
    }
}