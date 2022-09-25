import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=743 lang=java
 *
 * [743] 网络延迟时间
 *
 * https://leetcode.cn/problems/network-delay-time/description/
 *
 * algorithms
 * Medium (54.82%)
 * Likes:    596
 * Dislikes: 0
 * Total Accepted:    90.7K
 * Total Submissions: 165.3K
 * Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
 *
 * 有 n 个网络节点，标记为 1 到 n。
 * 
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 * 
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 * 
 * 
 */

// @lc code=start
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        for (int[] array : graph) {
            Arrays.fill(array, Integer.MAX_VALUE / 2);
        }

        for (int[] array : times) {
            graph[array[0] - 1][array[1] - 1] = array[2];
        }

        int[] weights = dijkstra(graph, k);
        int maxDelay = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (weights[i] == Integer.MAX_VALUE / 2)
                return -1;

            maxDelay = Math.max(maxDelay, weights[i]);
        }

        return maxDelay;
    }

    private int[] dijkstra(int[][] graph, int k) {
        if (graph == null || graph.length == 0 || k < 0 || k > graph.length)
            throw new IllegalArgumentException("graph cannot be empty");

        int n = graph.length;
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE / 2);
        distances[k - 1] = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int current = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (current == -1 || distances[j] < distances[current]))
                    current = j;
            }

            visited[current] = true;
            for (int idx = 0; idx < n; idx++) {
                distances[idx] = Math.min(distances[idx], distances[current] + graph[current][idx]);
            }
        }

        return distances;
    }
}
// @lc code=end
