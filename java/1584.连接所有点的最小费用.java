import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @lc app=leetcode.cn id=1584 lang=java
 *
 * [1584] 连接所有点的最小费用
 *
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/description/
 *
 * algorithms
 * Medium (66.29%)
 * Likes:    239
 * Dislikes: 0
 * Total Accepted:    41.7K
 * Total Submissions: 63K
 * Testcase Example:  '[[0,0],[2,2],[3,10],[5,2],[7,0]]'
 *
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示
 * val 的绝对值。
 * 
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * 
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：points = [[0,0]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(i, j, manhattonDistance(points, i, j)));
            }
        }

        Collections.sort(edges);
        int ans = 0, count = 1;
        for (Edge edge : edges) {
            if (ufs.union(edge.getPx(), edge.getPy())) {
                ans += edge.getLen();
                count++;
            }

            if (count == n)
                break;
        }

        return ans;
    }

    private int manhattonDistance(int[][] points, int i, int j) {
        if (points == null || i < 0 || j < 0 || i >= points.length || j >= points.length)
            return 0;

        int dx = Math.abs(points[i][0] - points[j][0]);
        int dy = Math.abs(points[i][1] - points[j][1]);
        return dx + dy;
    }
}

class Edge implements Comparable<Edge> {
    // 边连接的其中一个端点
    private int px;

    // 另一个端点
    private int py;

    // 权值
    private int len;

    public Edge(int px, int py, int len) {
        this.px = px;
        this.py = py;
        this.len = len;
    }

    /**
     * @return the px
     */
    public int getPx() {
        return px;
    }

    /**
     * @return the py
     */
    public int getPy() {
        return py;
    }

    /**
     * @return the len
     */
    public int getLen() {
        return len;
    }

    @Override
    public int compareTo(Edge o) {
        if (o == null)
            return -1;

        return this.len - ((Edge) o).len;
    }
}

class UnionFindSet {
    private int length;
    private int[] parents;

    public UnionFindSet(int length) {
        if (length <= 0)
            throw new IllegalArgumentException("length must great than 0");

        this.length = length;
        this.parents = new int[length];
        for (int i = 0; i < length; i++) {
            this.parents[i] = i;
        }
    }

    public boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py)
            return false;

        parents[py] = px;
        this.length--;
        return true;
    }

    private int find(int idx) {
        if (idx != parents[idx]) {
            parents[idx] = find(parents[idx]);
        }

        return parents[idx];
    }

    /**
     * @return the length
     */
    public int count() {
        return length;
    }
}
// @lc code=end
