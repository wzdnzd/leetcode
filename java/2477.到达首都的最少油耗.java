/*
 * @lc app=leetcode.cn id=2477 lang=java
 *
 * [2477] 到达首都的最少油耗
 *
 * https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/description/
 *
 * algorithms
 * Medium (53.37%)
 * Likes:    60
 * Dislikes: 0
 * Total Accepted:    6.3K
 * Total Submissions: 11.2K
 * Testcase Example:  '[[0,1],[0,2],[0,3]]\n5'
 *
 * 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。0
 * 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 * 
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 * 
 * 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
 * 
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
 * 
 * 请你返回到达首都最少需要多少升汽油。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：roads = [[0,1],[0,2],[0,3]], seats = 5
 * 输出：3
 * 解释：
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 2 直接到达首都，消耗 1 升汽油。
 * - 代表 3 直接到达首都，消耗 1 升汽油。
 * 最少消耗 3 升汽油。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * 输出：7
 * 解释：
 * - 代表 2 到达城市 3 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
 * - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
 * - 代表 1 直接到达首都，消耗 1 升汽油。
 * - 代表 5 直接到达首都，消耗 1 升汽油。
 * - 代表 6 到达城市 4 ，消耗 1 升汽油。
 * - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
 * 最少消耗 7 升汽油。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：roads = [], seats = 1
 * 输出：0
 * 解释：没有代表需要从别的城市到达首都。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^5
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads 表示一棵合法的树。
 * 1 <= seats <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<List<Integer>> graph = new ArrayList<>(n);

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        long[] result = dfs(graph, seats, 0, -1, 0L);
        return result[1];
    }

    private long[] dfs(List<List<Integer>> graph, int seats, int u, int p, long cumulative) {
        long count = 1L;
        for (int i : graph.get(u)) {
            if (i == p)
                continue;

            long[] result = dfs(graph, seats, i, u, cumulative);
            count += result[0];
            cumulative = result[1] + (result[0] + seats - 1) / seats;
        }

        return new long[] { count, cumulative };
    }
}
// @lc code=end
