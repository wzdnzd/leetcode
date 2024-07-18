/*
 * @lc app=leetcode.cn id=3112 lang=java
 *
 * [3112] 访问消失节点的最少时间
 *
 * https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes/description/
 *
 * algorithms
 * Medium (35.55%)
 * Likes:    7
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 10.1K
 * Testcase Example:  '3\n[[0,1,2],[1,2,1],[0,2,4]]\n[1,1,5]'
 *
 * 给你一个二维数组 edges 表示一个 n 个点的无向图，其中 edges[i] = [ui, vi, lengthi] 表示节点 ui 和节点 vi
 * 之间有一条需要 lengthi 单位时间通过的无向边。
 * 
 * 同时给你一个数组 disappear ，其中 disappear[i] 表示节点 i 从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。
 * 
 * 注意，图有可能一开始是不连通的，两个节点之间也可能有多条边。
 * 
 * 请你返回数组 answer ，answer[i] 表示从节点 0 到节点 i 需要的 最少 单位时间。如果从节点 0 出发 无法 到达节点 i ，那么
 * answer[i] 为 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]
 * 
 * 输出：[0,-1,4]
 * 
 * 解释：
 * 
 * 我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。
 * 
 * 
 * 对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。
 * 对于节点 1 ，我们需要至少 2 单位时间，通过 edges[0] 到达。但当我们到达的时候，它已经消失了，所以我们无法到达它。
 * 对于节点 2 ，我们需要至少 4 单位时间，通过 edges[2] 到达。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
 * 
 * 输出：[0,2,3]
 * 
 * 解释：
 * 
 * 我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。
 * 
 * 
 * 对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。
 * 对于节点 1 ，我们需要至少 2 单位时间，通过 edges[0] 到达。
 * 对于节点 2 ，我们需要至少 3 单位时间，通过 edges[0] 和 edges[1] 到达。
 * 
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 2, edges = [[0,1,1]], disappear = [1,1]
 * 
 * 输出：[0,-1]
 * 
 * 解释：
 * 
 * 当我们到达节点 1 的时候，它恰好消失，所以我们无法到达节点 1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 5 * 10^4
 * 0 <= edges.length <= 10^5
 * edges[i] == [ui, vi, lengthi]
 * 0 <= ui, vi <= n - 1
 * 1 <= lengthi <= 10^5
 * disappear.length == n
 * 1 <= disappear[i] <= 10^5
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], w = edge[2];
            graph.get(x).add(new int[] { y, w });
            graph.get(y).add(new int[] { x, w });
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, 0 });

        int[] result = new int[n];
        Arrays.fill(result, -1);
        result[0] = 0;

        while (!pq.isEmpty()) {
            int[] record = pq.poll();
            int w = record[0], u = record[1];

            if (w != result[u])
                continue;

            for (int[] tuple : graph.get(u)) {
                int v = tuple[0], t = w + tuple[1];

                if (t < disappear[v] && (result[v] == -1 || t < result[v])) {
                    pq.offer(new int[] { t, v });
                    result[v] = t;
                }
            }
        }

        return result;

    }
}
// @lc code=end
