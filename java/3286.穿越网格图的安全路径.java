/*
 * @lc app=leetcode.cn id=3286 lang=java
 *
 * [3286] 穿越网格图的安全路径
 *
 * https://leetcode.cn/problems/find-a-safe-walk-through-a-grid/description/
 *
 * algorithms
 * Medium (45.90%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    6.6K
 * Total Submissions: 13.5K
 * Testcase Example:  '[[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]\n1'
 *
 * 给你一个 m x n 的二进制矩形 grid 和一个整数 health 表示你的健康值。
 * 
 * 你开始于矩形的左上角 (0, 0) ，你的目标是矩形的右下角 (m - 1, n - 1) 。
 * 
 * 你可以在矩形中往上下左右相邻格子移动，但前提是你的健康值始终是 正数 。
 * 
 * 对于格子 (i, j) ，如果 grid[i][j] = 1 ，那么这个格子视为 不安全 的，会使你的健康值减少 1 。
 * 
 * 如果你可以到达最终的格子，请你返回 true ，否则返回 false 。
 * 
 * 注意 ，当你在最终格子的时候，你的健康值也必须为 正数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1
 * 
 * 输出：true
 * 
 * 解释：
 * 
 * 沿着下图中灰色格子走，可以安全到达最终的格子。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health
 * = 3
 * 
 * 输出：false
 * 
 * 解释：
 * 
 * 健康值最少为 4 才能安全到达最后的格子。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5
 * 
 * 输出：true
 * 
 * 解释：
 * 
 * 沿着下图中灰色格子走，可以安全到达最终的格子。
 * 
 * 
 * 
 * 任何不经过格子 (1, 1) 的路径都是不安全的，因为你的健康值到达最终格子时，都会小于等于 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 2 
 * 1 <= health <= m + n
 * grid[i][j] 要么是 0 ，要么是 1 。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] costs = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(costs[i], Integer.MAX_VALUE);

        costs[0][0] = grid.get(0).get(0);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] { 0, 0, costs[0][0] });

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int row = cell[0], col = cell[1];

            for (int[] direction : directions) {
                int newRow = row + direction[0], newCol = col + direction[1];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n
                        && costs[row][col] + grid.get(newRow).get(newCol) < costs[newRow][newCol]) {
                    costs[newRow][newCol] = costs[row][col] + grid.get(newRow).get(newCol);
                    pq.offer(new int[] { newRow, newCol, costs[newRow][newCol] });
                }
            }
        }

        return costs[m - 1][n - 1] < health;
    }
}
// @lc code=end
