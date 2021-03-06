#  Copyright (c) 2021
#  @Author: xiaoweixiang
class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: (x[0], -x[1]))
        n, ans, tmp, length, b = 0, 0, [], len(intervals), []
        while n < length:
            for t in intervals:
                if not tmp or tmp[-1][1] <= t[0]:
                    tmp.append(t)
                    n += 1
                else:
                    b.append(t)
            ans, intervals, tmp, b = ans + 1, b, [], []
        return ans