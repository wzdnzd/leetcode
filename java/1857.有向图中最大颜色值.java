/*
 * @lc app=leetcode.cn id=1857 lang=java
 *
 * [1857] 有向图中最大颜色值
 *
 * https://leetcode.cn/problems/largest-color-value-in-a-directed-graph/description/
 *
 * algorithms
 * Hard (49.87%)
 * Likes:    76
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 12.7K
 * Testcase Example:  '"abaca"\n[[0,1],[0,2],[2,3],[3,4]]'
 *
 * 给你一个 有向图 ，它含有 n 个节点和 m 条边。节点编号从 0 到 n - 1 。
 * 
 * 给你一个字符串 colors ，其中 colors[i] 是小写英文字母，表示图中第 i 个节点的 颜色 （下标从 0 开始）。同时给你一个二维数组
 * edges ，其中 edges[j] = [aj, bj] 表示从节点 aj 到节点 bj 有一条 有向边 。
 * 
 * 图中一条有效 路径 是一个点序列 x1 -> x2 -> x3 -> ... -> xk ，对于所有 1 <= i < k ，从 xi 到 xi+1
 * 在图中有一条有向边。路径的 颜色值 是路径中 出现次数最多 颜色的节点数目。
 * 
 * 请你返回给定图中有效路径里面的 最大颜色值 。如果图中含有环，请返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * 输出：3
 * 解释：路径 0 -> 2 -> 3 -> 4 含有 3 个颜色为 "a" 的节点（上图中的红色节点）。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：colors = "a", edges = [[0,0]]
 * 输出：-1
 * 解释：从 0 到 0 有一个环。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 10^5
 * 0 <= m <= 10^5
 * colors 只含有小写英文字母。
 * 0 <= aj, bj < n
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        
        int[] degrees = new int[n];
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            degrees[e[1]]++;
        }

        int[] theColors = new int[n];
        for (int i = 0; i < n; i++)
            theColors[i] = colors.charAt(i) - 'a';

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 0)
                queue.offer(i);
        }

        int count = 0;
        int[][] cache = new int[n][26];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            cache[u][theColors[u]]++;

            for (int v : graph.get(u)) {
                degrees[v]--;
                for (int c = 0; c < 26; c++)
                    cache[v][c] = Math.max(cache[v][c], cache[u][c]);
                
                if (degrees[v] == 0)
                    queue.offer(v);
            }
        }

        if (count != n)
            return -1;

        int result = 0;
        for (int u = 0; u < n; u++) {
            for (int c = 0; c < 26; c++)
                result = Math.max(result, cache[u][c]);
        }

        return result;
    }
}
// @lc code=end

