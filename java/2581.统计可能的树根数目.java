/*
 * @lc app=leetcode.cn id=2581 lang=java
 *
 * [2581] 统计可能的树根数目
 *
 * https://leetcode.cn/problems/count-number-of-possible-root-nodes/description/
 *
 * algorithms
 * Hard (58.85%)
 * Likes:    49
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 6.5K
 * Testcase Example:  '[[0,1],[1,2],[1,3],[4,2]]\n[[1,3],[0,1],[1,0],[2,4]]\n3'
 *
 * Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。树用一个长度为 n - 1 的二维整数数组 edges 表示，其中
 * edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。
 * 
 * Alice 想要 Bob 找到这棵树的根。她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：
 * 
 * 
 * 选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
 * Bob 猜测树中 u 是 v 的 父节点 。
 * 
 * 
 * Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。
 * 
 * Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。
 * 
 * 给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。如果没有这样的树，则返回 0。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k
 * = 3
 * 输出：3
 * 解释：
 * 根为节点 0 ，正确的猜测为 [1,3], [0,1], [2,4]
 * 根为节点 1 ，正确的猜测为 [1,3], [1,0], [2,4]
 * 根为节点 2 ，正确的猜测为 [1,3], [1,0], [2,4]
 * 根为节点 3 ，正确的猜测为 [1,0], [2,4]
 * 根为节点 4 ，正确的猜测为 [1,3], [1,0]
 * 节点 0 ，1 或 2 为根时，可以得到 3 个正确的猜测。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k
 * = 1
 * 输出：5
 * 解释：
 * 根为节点 0 ，正确的猜测为 [3,4]
 * 根为节点 1 ，正确的猜测为 [1,0], [3,4]
 * 根为节点 2 ，正确的猜测为 [1,0], [2,1], [3,4]
 * 根为节点 3 ，正确的猜测为 [1,0], [2,1], [3,2], [3,4]
 * 根为节点 4 ，正确的猜测为 [1,0], [2,1], [3,2]
 * 任何节点为根，都至少有 1 个正确的猜测。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * edges.length == n - 1
 * 2 <= n <= 10^5
 * 1 <= guesses.length <= 10^5
 * 0 <= ai, bi, uj, vj <= n - 1
 * ai != bi
 * uj != vj
 * edges 表示一棵有效的树。
 * guesses[j] 是树中的一条边。
 * guesses 是唯一的。
 * 0 <= k <= guesses.length
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private final long K = 10000;
    private int result = 0;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;

        @SuppressWarnings("unchecked")
        List<Integer>[] neighbors = new ArrayList[n];

        for (int i = 0; i < n; i++)
            neighbors[i] = new ArrayList<>();

        for (int[] e : edges) {
            int x = e[0], y = e[1];
            neighbors[x].add(y);
            neighbors[y].add(x);
        }

        Map<Long, Integer> guessesIds = new HashMap<>();
        for (int[] g : guesses) {
            int u = g[0], v = g[1];
            guessesIds.put(u * K + v, 1);
        }

        int zeroRootCorrect = getZeroRootCorrect(0, -1, neighbors, guessesIds);
        changeRoot(0, -1, k, zeroRootCorrect, neighbors, guessesIds);
        return result;
    }

    private int getZeroRootCorrect(int node, int parent, List<Integer>[] neighbors, Map<Long, Integer> guessesIds) {
        int correct = 0;
        for (int child : neighbors[node]) {
            if (child != parent) {
                correct += guessesIds.getOrDefault(node * K + child, 0);
                correct += getZeroRootCorrect(child, node, neighbors, guessesIds);
            }
        }
        return correct;
    }

    private void changeRoot(int node, int parent, int k, int correct, List<Integer>[] neighbors,
            Map<Long, Integer> guessesIds) {
        if (correct >= k)
            result++;

        for (int child : neighbors[node]) {
            if (child != parent) {
                changeRoot(child, node, k,
                        correct - guessesIds.getOrDefault(node * K + child, 0)
                                + guessesIds.getOrDefault(child * K + node, 0),
                        neighbors, guessesIds);
            }
        }
    }
}
// @lc code=end
