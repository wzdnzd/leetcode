import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=886 lang=java
 *
 * [886] 可能的二分法
 *
 * https://leetcode.cn/problems/possible-bipartition/description/
 *
 * algorithms
 * Medium (49.53%)
 * Likes:    194
 * Dislikes: 0
 * Total Accepted:    20.7K
 * Total Submissions: 41.8K
 * Testcase Example:  '4\n[[1,2],[1,3],[2,4]]'
 *
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和
 * bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * dislikes 中每一组都 不同
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        if (dislikes == null || dislikes.length == 0)
            return true;

        boolean[] visited = new boolean[n + 1];
        boolean[] colors = new boolean[n + 1];
        boolean ans = true;
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                ans = ans && dfs(graph, visited, colors, i);
                if (!ans)
                    break;
            }
        }

        return ans;
    }

    private boolean dfs(List<Integer>[] graph, boolean[] visited, boolean[] colors, int v) {
        boolean flag = true;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                visited[w] = true;
                colors[w] = !colors[v];
                flag = flag && dfs(graph, visited, colors, w);
            } else {
                if (colors[w] == colors[v])
                    return false;
            }
        }

        return flag;
    }

    @SuppressWarnings("unchecked")
    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : dislikes) {
            graph[edge[1]].add(edge[0]);
            graph[edge[0]].add(edge[1]);
        }

        return graph;
    }
}
// @lc code=end
