 public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];

                    map.put(a, map.get(a) - 1);
                    map.put(b, map.get(b) - 1);
                    map.put(c, map.get(c) - 1);

                    if (map.getOrDefault(target - a - b - c, 0) > 0) {

                        map.put(a, map.get(a) + 1);
                        map.put(b, map.get(b) + 1);
                        map.put(c, map.get(c) + 1);

                        List<Integer> list = new ArrayList<Integer>();
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        list.add(target - a - b - c);
                        Collections.sort(list);
                        if (!set.add(list)) {
                            continue;
                        }

                        ans.add(list);
                        continue;

                    }

                    map.put(a, map.get(a) + 1);
                    map.put(b, map.get(b) + 1);
                    map.put(c, map.get(c) + 1);

                }
        return ans;
    }