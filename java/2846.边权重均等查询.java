/*
 * @lc app=leetcode.cn id=2846 lang=java
 *
 * [2846] 边权重均等查询
 *
 * https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree/description/
 *
 * algorithms
 * Hard (47.16%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    3.3K
 * Total Submissions: 6K
 * Testcase Example:  '7\n' +
  '[[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]]\n' +
  '[[0,3],[3,6],[2,6],[0,6]]'
 *
 * 现有一棵由 n 个节点组成的无向树，节点按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中
 * edges[i] = [ui, vi, wi] 表示树中存在一条位于节点 ui 和节点 vi 之间、权重为 wi 的边。
 * 
 * 另给你一个长度为 m 的二维整数数组 queries ，其中 queries[i] = [ai, bi] 。对于每条查询，请你找出使从 ai 到 bi
 * 路径上每条边的权重相等所需的 最小操作次数 。在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。
 * 
 * 注意：
 * 
 * 
 * 查询之间 相互独立 的，这意味着每条新的查询时，树都会回到 初始状态 。
 * 从 ai 到 bi的路径是一个由 不同 节点组成的序列，从节点 ai 开始，到节点 bi 结束，且序列中相邻的两个节点在树中共享一条边。
 * 
 * 
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是第 i 条查询的答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]], queries
 * = [[0,3],[3,6],[2,6],[0,6]]
 * 输出：[0,0,1,3]
 * 解释：第 1 条查询，从节点 0 到节点 3 的路径中的所有边的权重都是 1 。因此，答案为 0 。
 * 第 2 条查询，从节点 3 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 0 。
 * 第 3 条查询，将边 [2,3] 的权重变更为 2 。在这次操作之后，从节点 2 到节点 6 的路径中的所有边的权重都是 2 。因此，答案为 1 。
 * 第 4 条查询，将边 [0,1]、[1,2]、[2,3] 的权重变更为 2 。在这次操作之后，从节点 0 到节点 6 的路径中的所有边的权重都是 2
 * 。因此，答案为 3 。
 * 对于每条查询 queries[i] ，可以证明 answer[i] 是使从 ai 到 bi 的路径中的所有边的权重相等的最小操作次数。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 8, edges = [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]],
 * queries = [[4,6],[0,4],[6,5],[7,4]]
 * 输出：[1,2,2,3]
 * 解释：第 1 条查询，将边 [1,3] 的权重变更为 6 。在这次操作之后，从节点 4 到节点 6 的路径中的所有边的权重都是 6 。因此，答案为 1
 * 。
 * 第 2 条查询，将边 [0,3]、[3,1] 的权重变更为 6 。在这次操作之后，从节点 0 到节点 4 的路径中的所有边的权重都是 6 。因此，答案为
 * 2 。
 * 第 3 条查询，将边 [1,3]、[5,2] 的权重变更为 6 。在这次操作之后，从节点 6 到节点 5 的路径中的所有边的权重都是 6 。因此，答案为
 * 2 。
 * 第 4 条查询，将边 [0,7]、[0,3]、[1,3] 的权重变更为 6 。在这次操作之后，从节点 7 到节点 4 的路径中的所有边的权重都是 6
 * 。因此，答案为 3 。
 * 对于每条查询 queries[i] ，可以证明 answer[i] 是使从 ai 到 bi 的路径中的所有边的权重相等的最小操作次数。 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^4
 * edges.length == n - 1
 * edges[i].length == 3
 * 0 <= ui, vi < n
 * 1 <= wi <= 26
 * 生成的输入满足 edges 表示一棵有效的树
 * 1 <= queries.length == m <= 2 * 10^4
 * queries[i].length == 2
 * 0 <= ai, bi < n
 * 
 * 
 */

// @lc code=start
class Solution {
    private int[] edges, nexts, heads, weights;

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int m = edges.length, index = 0;

        this.edges = new int[m << 1];
        this.weights = new int[m << 1];
        this.nexts = new int[m << 1];
        this.heads = new int[n];

        for (int i = 0; i < n; ++i)
            this.heads[i] = -1;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2] - 1;
            add(u, v, w, index);
            add(v, u, w, index + 1);

            index += 2;
        }

        int maxBits = 0, num = n;
        while (num > 0) {
            num /= 2;
            maxBits++;
        }

        int[][] parents = new int[maxBits][n];
        int[][] weightCounts = new int[n][26];
        int[] depths = new int[n];

        dfs(0, 0, 0, parents, depths, weightCounts);

        for (int i = 1; i < maxBits; ++i) {
            for (int node = 0; node < n; ++node) {
                int p = parents[i - 1][node];
                parents[i][node] = parents[i - 1][p];
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int u = queries[i][0], v = queries[i][1], l = lca(u, v, parents, depths);

            int max = 0, distance = depths[u] + depths[v] - 2 * depths[l];
            for (int k = 0; k < weightCounts[u].length; ++k) {
                int count = weightCounts[u][k] + weightCounts[v][k] - weightCounts[l][k] * 2;

                if (count > max)
                    max = count;
            }

            result[i] = distance - max;
        }

        return result;
    }

    private void dfs(int u, int parent, int depth, int[][] parents, int[] depths, int[][] weightCounts) {
        parents[0][u] = parent;
        depths[u] = depth;

        for (int index = this.heads[u]; index != -1; index = this.nexts[index]) {
            int v = this.edges[index], w = this.weights[index];
            if (v == parent)
                continue;

            for (int i = 0; i < weightCounts[v].length; ++i)
                weightCounts[v][i] = weightCounts[u][i];

            weightCounts[v][w]++;

            dfs(v, u, depth + 1, parents, depths, weightCounts);
        }
    }

    private int lca(int u, int v, int[][] parent, int[] depths) {
        if (depths[u] > depths[v])
            return lca(v, u, parent, depths);

        int level = depths[v] - depths[u];
        while (level > 0) {
            int bit = Integer.bitCount((level & (-level)) - 1);
            v = parent[bit][v];
            level &= level - 1;
        }

        if (u == v)
            return u;

        int maxBits = parent.length;
        for (int p = maxBits - 1; p >= 0; --p) {
            if (parent[p][u] != parent[p][v]) {
                u = parent[p][u];
                v = parent[p][v];
            }
        }

        return parent[0][u];
    }

    private void add(int u, int v, int w, int index) {
        this.edges[index] = v;
        this.weights[index] = w;
        this.nexts[index] = this.heads[u];
        this.heads[u] = index++;
    }
}
// @lc code=end
