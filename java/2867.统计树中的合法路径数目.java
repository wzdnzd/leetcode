/*
 * @lc app=leetcode.cn id=2867 lang=java
 *
 * [2867] 统计树中的合法路径数目
 *
 * https://leetcode.cn/problems/count-valid-paths-in-a-tree/description/
 *
 * algorithms
 * Hard (34.60%)
 * Likes:    25
 * Dislikes: 0
 * Total Accepted:    3.6K
 * Total Submissions: 9.2K
 * Testcase Example:  '5\n[[1,2],[1,3],[2,4],[2,5]]'
 *
 * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i]
 * = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
 * 
 * 请你返回树中的 合法路径数目 。
 * 
 * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
 * 
 * 注意：
 * 
 * 
 * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
 * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
 * 输出：4
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * 只有 4 条合法路径。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
 * 输出：6
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * - (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
 * 只有 6 条合法路径。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * 输入保证 edges 形成一棵合法的树。
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int COUNT = 100001;
    private static boolean[] isPrimes = new boolean[COUNT];

    static {
        Arrays.fill(isPrimes, true);
        isPrimes[1] = false;

        for (int i = 2; i * i < COUNT; i++) {
            if (isPrimes[i]) {
                for (int j = i * i; j < COUNT; j += i)
                    isPrimes[j] = false;
            }
        }
    }

    public long countPaths(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            int i = edge[0], j = edge[1];

            graph.get(i).add(j);
            graph.get(j).add(i);
        }

        long count = 0;
        long[] records = new long[n + 1];
        List<Integer> seen = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!isPrimes[i])
                continue;

            long current = 0;
            for (int j : graph.get(i)) {
                if (isPrimes[j])
                    continue;

                if (records[j] == 0) {
                    seen.clear();
                    dfs(graph, seen, j, 0);

                    long size = seen.size();
                    for (int k : seen)
                        records[k] = size;
                }

                count += records[j] * current;
                current += records[j];
            }

            count += current;
        }

        return count;
    }

    private void dfs(List<List<Integer>> graph, List<Integer> seen, int i, int previous) {
        seen.add(i);

        for (int j : graph.get(i)) {
            if (j != previous && !isPrimes[j])
                dfs(graph, seen, j, i);
        }
    }
}
// @lc code=end
