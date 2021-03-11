class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index=0;
        int ans = 0;
        if (ruleKey.equals("type")){
            index = 0;
        }
        if (ruleKey.equals("color")){
            index = 1;
        }
        if (ruleKey.equals("name")){
            index = 2;
        }

        for (List<String> list:items){
            if (ruleValue.equals(list.get(index)))
                ans++;
        }
        return ans;
    }
}