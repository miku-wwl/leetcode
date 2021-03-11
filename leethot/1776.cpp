class Solution {
public:
    vector<double> getCollisionTimes(vector<vector<int>>& cars) {
        int n = cars.size();
        vector<double> res(n);
        res[n-1] = -1;
        stack<int> st;
        st.push(n-1);
        for(int i = n-2; i >= 0; --i) {
            while(st.size()) {
                // 此处的详细说明：
                // 1. 如果当前车的车速 <= 栈顶车的车速，则当前车永远无法追上栈顶车，因此总是可以 pop 出栈顶车；
                // 2. 否则，如果当前车的车速 > 栈顶车：
                //    2.1 如果栈顶车的追上更右侧车辆的时间为 -1 (永远追不上)，则不能 pop 出;
                //    2.2 否则，则判断在 “理想状态(即栈顶车不会追上更右侧车)”下 ，当前车的追上栈顶车的时间 T
                //        (也就是下面代码里的式子)，如果 T > res[st.top()](即栈顶车的实际追及时间)，
                //        说明不能在右侧车追上更右侧车之前追上，应当 pop；否则能在碰撞前追上。
                if(cars[i][1] <= cars[st.top()][1] || (res[st.top()] > 1e-9 && (double)(cars[st.top()][0] - cars[i][0]) / (double)(cars[i][1] - cars[st.top()][1]) > res[st.top()])) {
                    st.pop();
                } else {
                    break;
                }
            }
            if(st.size()) {
                res[i] = (double)(cars[st.top()][0] - cars[i][0]) / (double)(cars[i][1] - cars[st.top()][1]);
                st.push(i);
            } else {
                res[i] = -1;
                st.push(i);
            }
        }
        return res;
    }
};
