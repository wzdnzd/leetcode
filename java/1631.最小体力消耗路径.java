import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=1631 lang=java
 *
 * [1631] 最小体力消耗路径
 *
 * https://leetcode.cn/problems/path-with-minimum-effort/description/
 *
 * algorithms
 * Medium (50.46%)
 * Likes:    314
 * Dislikes: 0
 * Total Accepted:    36.4K
 * Total Submissions: 72.1K
 * Testcase Example:  '[[1,2,2],[3,8,2],[5,3,5]]'
 *
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子
 * (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从
 * 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * rows == heights.length
 * columns == heights[i].length
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int length = m * n;

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));

        pq.offer(new int[] { 0, 0, 0 });
        int[] distances = new int[length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        boolean[] visited = new boolean[length];
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], weight = edge[2];
            int index = x * n + y;

            // 已经到达右下角
            if (x == m - 1 && y == n - 1)
                break;
            // 节点已经访问过
            if (visited[index])
                continue;

            visited[index] = true;

            for (int[] direction : directions) {
                int dx = x + direction[0];
                int dy = y + direction[1];

                if (dx < 0 || dx >= m || dy < 0 || dy >= n)
                    continue;
                index = dx * n + dy;
                int val = Math.max(weight, Math.abs(heights[x][y] - heights[dx][dy]));
                if (val < distances[index]) {
                    distances[index] = val;
                    pq.offer(new int[] { dx, dy, val });
                }
            }
        }

        return distances[length - 1];
    }
}
// @lc code=end
