class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        List<Integer> list =new  ArrayList<Integer>();
        int n = matrix.length;
        for (int i=0;i<n;i++)
            for (int j:matrix[i]){
                list.add(j);
            }
        Collections.sort(list);
        return list.get(k-1);    
    }
}