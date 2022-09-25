import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=1514 lang=java
 *
 * [1514] 概率最大的路径
 *
 * https://leetcode.cn/problems/path-with-maximum-probability/description/
 *
 * algorithms
 * Medium (38.53%)
 * Likes:    110
 * Dislikes: 0
 * Total Accepted:    15.4K
 * Total Submissions: 39.7K
 * Testcase Example:  '3\n[[0,1],[1,2],[0,2]]\n[0.5,0.5,0.2]\n0\n2'
 *
 * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和
 * b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
 * 
 * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
 * 
 * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0,
 * end = 2
 * 输出：0.25000
 * 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0,
 * end = 2
 * 输出：0.30000
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * 输出：0.00000
 * 解释：节点 0 和 节点 2 之间不存在路径
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * 每两个节点之间最多有一条边
 * 
 * 
 */

// @lc code=start
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        if (succProb == null || succProb.length == 0)
            return 0;

        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.get(edge[0]).add(new Pair(edge[1], succProb[i]));
            graph.get(edge[1]).add(new Pair(edge[0], succProb[i]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(start, 1.0));
        double[] probabilities = new double[n];
        probabilities[start] = 1.0;

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int nid = pair.getNid();
            double prob = pair.getProb();
            if (prob < probabilities[nid])
                continue;
            for (Pair p : graph.get(nid)) {
                double val = probabilities[nid] * p.getProb();
                if (probabilities[p.getNid()] < val) {
                    probabilities[p.getNid()] = val;
                    pq.offer(new Pair(p.getNid(), val));
                }
            }
        }

        return probabilities[end];
    }
}

class Pair implements Comparable<Pair> {
    // 节点id
    private final int nid;

    // 概率
    private final double prob;

    public Pair(int nid, double prob) {
        this.nid = nid;
        this.prob = prob;
    }

    /**
     * @return the nid
     */
    public int getNid() {
        return nid;
    }

    /**
     * @return the prob
     */
    public double getProb() {
        return prob;
    }

    @Override
    public int compareTo(Pair o) {
        if (o == null)
            return -1;

        return Double.compare(o.prob, this.prob);
    }
}
// @lc code=end
