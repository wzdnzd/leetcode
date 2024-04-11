/*
 * @lc app=leetcode.cn id=1766 lang=java
 *
 * [1766] 互质树
 *
 * https://leetcode.cn/problems/tree-of-coprimes/description/
 *
 * algorithms
 * Hard (40.54%)
 * Likes:    41
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 9.2K
 * Testcase Example:  '[2,3,3,2]\n[[0,1],[1,2],[1,3]]'
 *
 * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。树的 根节点 为
 * 0 号点。
 * 
 * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值，edges[j] = [uj, vj]
 * 表示节点 uj 和节点 vj 在树中有一条边。
 * 
 * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
 * 
 * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。
 * 
 * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的
 * ，如果不存在这样的祖先节点，ans[i] 为 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
 * 输出：[-1,0,0,1]
 * 解释：上图中，每个节点的值在括号中表示。
 * - 节点 0 没有互质祖先。
 * - 节点 1 只有一个祖先节点 0 。它们的值是互质的（gcd(2,3) == 1）。
 * - 节点 2 有两个祖先节点，分别是节点 1 和节点 0 。节点 1 的值与它的值不是互质的（gcd(3,3) == 3）但节点 0
 * 的值是互质的(gcd(2,3) == 1)，所以节点 0 是最近的符合要求的祖先节点。
 * - 节点 3 有两个祖先节点，分别是节点 1 和节点 0 。它与节点 1 互质（gcd(3,2) == 1），所以节点 1
 * 是离它最近的符合要求的祖先节点。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
 * 输出：[-1,0,-1,0,0,0,-1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * nums.length == n
 * 1 
 * 1 
 * edges.length == n - 1
 * edges[j].length == 2
 * 0 j, vj < n
 * uj != vj
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int MAX = 51;
    private static final int[][] coprimes = new int[MAX][MAX];

    static {
        for (int i = 1; i < MAX; i++) {
            int index = 0;
            for (int j = 1; j < MAX; j++)
                if (gcd(i, j) == 1)
                    coprimes[i][index++] = j;
        }
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        int[] valDepths = new int[MAX];
        int[] valNodeIds = new int[MAX];
        dfs(graph, nums, result, valDepths, valNodeIds, 0, -1, 1);

        return result;
    }

    private void dfs(List<List<Integer>> graph, int[] nums, int[] result, int[] valDepths, int[] valNodeIds, int node,
            int parent, int depth) {
        int value = nums[node], maxDepth = 0;

        for (int x : coprimes[value]) {
            if (x == 0)
                break;

            if (valDepths[x] > maxDepth) {
                maxDepth = valDepths[x];
                result[node] = valNodeIds[x];
            }
        }

        int tmpDepth = valDepths[value];
        int tmpNodeId = valNodeIds[value];

        valDepths[value] = depth;
        valNodeIds[value] = node;

        for (int i : graph.get(node)) {
            if (i != parent)
                dfs(graph, nums, result, valDepths, valNodeIds, i, node, depth + 1);
        }

        valDepths[value] = tmpDepth;
        valNodeIds[value] = tmpNodeId;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
// @lc code=end
