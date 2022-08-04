/*
 * @lc app=leetcode.cn id=2327 lang=java
 *
 * [9997] 房屋染色
 *
 * https://www.lintcode.com/problem/515
 *
 * algorithms
 * Medium (44.33%)
 * Likes:    36
 * Dislikes: 0
 * Total Accepted:    7.1K
 * Total Submissions: 15.9K
 * Testcase Example:  '6\n2\n4'
 *
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。
 *
 * 每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
 *
 *费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用，依此类推。找到油漆所有房子的最低成本。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入: [[14,2,11],[11,14,5],[14,3,10]]
 * 输出: 10
 * 解释: 第一个屋子染蓝色，第二个染绿色，第三个染蓝色，最小花费：2 + 5 + 3 = 10.
 * 
 * 
 * 示例 2：
 * 
 * 输入: [[1,2,3],[1,4,6]]
 * 输出: 3
 * 
 * 提示：
 * 
 * 
 * 所有费用都是正整数
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        if (costs[0].length == 0)
            return 0;

        int m = costs.length;
        int[][] dp = new int[m + 1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (k == j)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                }
            }
        }

        return Math.min(dp[m][0], Math.min(dp[m][1], dp[m][2]));
    }
}
// @lc code=end
