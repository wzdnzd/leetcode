/*
 * @lc app=leetcode.cn id=928 lang=java
 *
 * [928] 尽量减少恶意软件的传播 II
 *
 * https://leetcode.cn/problems/minimize-malware-spread-ii/description/
 *
 * algorithms
 * Hard (43.86%)
 * Likes:    74
 * Dislikes: 0
 * Total Accepted:    7.7K
 * Total Submissions: 15.2K
 * Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]\n[0,1]'
 *
 * 给定一个由 n 个节点组成的网络，用 n x n 个邻接矩阵 graph 表示。在节点网络中，只有当 graph[i][j] = 1 时，节点 i
 * 能够直接连接到另一个节点 j。
 * 
 * 一些节点 initial
 * 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 
 * 我们可以从 initial 中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。
 * 
 * 请返回移除后能够使 M(initial) 最小化的节点。如果有多个节点满足条件，返回索引 最小的节点 。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 * 输出：0
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：graph = [[1,1,0],[1,1,1],[0,1,1]], initial = [0,1]
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：graph = [[1,1,0,0],[1,1,1,0],[0,1,1,1],[0,0,1,1]], initial = [0,1]
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 300
 * graph[i][j] 是 0 或 1.
 * graph[i][j] == graph[j][i]
 * graph[i][i] == 1
 * 1 <= initial.length < n
 * 0 <= initial[i] <= n - 1
 * initial 中每个整数都不同
 * 
 * 
 */

// @lc code=start
class Solution {
    private int size;
    private int nodeId;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] isInitial = new boolean[n];

        int minNodeId = Integer.MAX_VALUE;
        for (int x : initial) {
            isInitial[x] = true;
            minNodeId = Math.min(minNodeId, x);
        }

        int[] records = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] || isInitial[i])
                continue;

            size = 0;
            nodeId = -1;

            dfs(i, graph, visited, isInitial);
            if (nodeId >= 0)
                records[nodeId] += size;
        }

        int maxCnt = 0, nodeId = -1;
        for (int i = 0; i < n; i++) {
            if (records[i] > 0 && (records[i] > maxCnt || records[i] == maxCnt && i < nodeId)) {
                maxCnt = records[i];
                nodeId = i;
            }
        }

        return nodeId < 0 ? minNodeId : nodeId;
    }

    private void dfs(int x, int[][] graph, boolean[] visited, boolean[] isInitial) {
        visited[x] = true;
        size++;

        for (int y = 0; y < graph.length; y++) {
            if (graph[x][y] == 0)
                continue;

            if (isInitial[y]) {
                if (nodeId != -2 && nodeId != y)
                    nodeId = nodeId == -1 ? y : -2;
            } else if (!visited[y])
                dfs(y, graph, visited, isInitial);
        }
    }
}
// @lc code=end
