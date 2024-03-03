/*
 * @lc app=leetcode.cn id=1722 lang=java
 *
 * [1722] 执行交换操作后的最小汉明距离
 *
 * https://leetcode.cn/problems/minimize-hamming-distance-after-swap-operations/description/
 *
 * algorithms
 * Medium (51.45%)
 * Likes:    67
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 13K
 * Testcase Example:  '[1,2,3,4]\n[2,1,4,5]\n[[0,1],[2,3]]'
 *
 * 给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i]
 * = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。注意，你可以按 任意 顺序 多次
 * 交换一对特定下标指向的元素。
 * 
 * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。形式上，其值等于满足 source[i] !=
 * target[i] （下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。
 * 
 * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * 输出：1
 * 解释：source 可以按下述方式转换：
 * - 交换下标 0 和 1 指向的元素：source = [2,1,3,4]
 * - 交换下标 2 和 3 指向的元素：source = [2,1,4,3]
 * source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * 输出：2
 * 解释：不能对 source 执行交换操作。
 * source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。
 * 
 * 示例 3：
 * 
 * 输入：source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps =
 * [[0,4],[4,2],[1,3],[1,4]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == source.length == target.length
 * 1 <= n <= 10^5
 * 1 <= source[i], target[i] <= 10^5
 * 0 <= allowedSwaps.length <= 10^5
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UnionFind uf = new UnionFind(n);

        for (int[] swap : allowedSwaps)
            uf.union(swap[0], swap[1]);

        Map<Integer, Map<Integer, Integer>> allGroupElements = new HashMap<Integer, Map<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);

            allGroupElements.putIfAbsent(root, new HashMap<Integer, Integer>());

            Map<Integer, Integer> groupElements = allGroupElements.get(root);
            groupElements.put(source[i], groupElements.getOrDefault(source[i], 0) + 1);
            groupElements.put(target[i], groupElements.getOrDefault(target[i], 0) - 1);
        }

        int totalDifference = 0;
        Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = allGroupElements.entrySet();
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
            Map<Integer, Integer> groupElements = entry.getValue();
            Set<Map.Entry<Integer, Integer>> group = groupElements.entrySet();

            for (Map.Entry<Integer, Integer> groupEntry : group) {
                int count = groupEntry.getValue();
                totalDifference += Math.abs(count);
            }
        }

        return totalDifference / 2;
    }
}

class UnionFind {
    private int[] parents;
    private int[] ranks;

    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;

        ranks = new int[n];
    }

    public void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a != b) {
            if (ranks[a] > ranks[b])
                parents[b] = a;
            else if (ranks[a] < ranks[b])
                parents[a] = b;
            else {
                parents[b] = a;
                ranks[a]++;
            }
        }
    }

    public int find(int x) {
        if (parents[x] != x)
            parents[x] = find(parents[x]);

        return parents[x];
    }
}
// @lc code=end
