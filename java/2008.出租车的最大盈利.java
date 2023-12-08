/*
 * @lc app=leetcode.cn id=2008 lang=java
 *
 * [2008] 出租车的最大盈利
 *
 * https://leetcode.cn/problems/maximum-earnings-from-taxi/description/
 *
 * algorithms
 * Medium (47.77%)
 * Likes:    112
 * Dislikes: 0
 * Total Accepted:    11.6K
 * Total Submissions: 22K
 * Testcase Example:  '5\n[[2,5,4],[1,5,1]]'
 *
 * 你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n
 * ，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
 * 
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i
 * 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
 * 
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。
 * 
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
 * 
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：n = 5, rides = [[2,5,4],[1,5,1]]
 * 输出：7
 * 解释：我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
 * 
 * 
 * 示例 2：
 * 
 * 输入：n = 20, rides =
 * [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * 输出：20
 * 解释：我们可以接以下乘客的订单：
 * - 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
 * - 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
 * - 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
 * 我们总共获得 9 + 5 + 6 = 20 元。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^5
 * 1 <= rides.length <= 3 * 10^4
 * rides[i].length == 3
 * 1 <= starti < endi <= n
 * 1 <= tipi <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        long[] dp = new long[n + 1];
        int index = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            while (index < rides.length && rides[index][1] == i) {
                dp[i] = Math.max(dp[i], dp[rides[index][0]] + rides[index][1] - rides[index][0] + rides[index][2]);
                index++;
            }
        }

        return dp[n];
    }
}
// @lc code=end
