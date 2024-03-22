/*
 * @lc app=leetcode.cn id=2617 lang=java
 *
 * [2617] 网格图中最少访问的格子数
 *
 * https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid/description/
 *
 * algorithms
 * Hard (32.48%)
 * Likes:    34
 * Dislikes: 0
 * Total Accepted:    5.1K
 * Total Submissions: 14K
 * Testcase Example:  '[[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]'
 *
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 * 
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 * 
 * 
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 
 * 
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
 * 输出：4
 * 解释：上图展示了到达右下角格子经过的 4 个格子。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
 * 输出：3
 * 解释：上图展示了到达右下角格子经过的 3 个格子。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：grid = [[2,1,0],[1,0,0]]
 * 输出：-1
 * 解释：无法到达右下角格子。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] < m * n
 * grid[m - 1][n - 1] == 0
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] distances = new int[m][n];
        for (int i = 0; i < m; ++i)
            Arrays.fill(distances[i], -1);
        distances[0][0] = 1;

        List<PriorityQueue<int[]>> rows = new ArrayList<>(m);
        List<PriorityQueue<int[]>> cols = new ArrayList<>(n);

        for (int i = 0; i < m; ++i)
            rows.add(new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        for (int i = 0; i < n; ++i)
            cols.add(new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));

        for (int i = 0; i < m; ++i) {
            PriorityQueue<int[]> rpq = rows.get(i);

            for (int j = 0; j < n; ++j) {
                while (!rpq.isEmpty() && rpq.peek()[1] + grid[i][rpq.peek()[1]] < j)
                    rpq.poll();
                if (!rpq.isEmpty())
                    distances[i][j] = update(distances[i][j], distances[i][rpq.peek()[1]] + 1);

                PriorityQueue<int[]> cpq = cols.get(j);

                while (!cpq.isEmpty() && cpq.peek()[1] + grid[cpq.peek()[1]][j] < i)
                    cpq.poll();
                if (!cpq.isEmpty())
                    distances[i][j] = update(distances[i][j], distances[cpq.peek()[1]][j] + 1);

                if (distances[i][j] != -1) {
                    rpq.offer(new int[] { distances[i][j], j });
                    cpq.offer(new int[] { distances[i][j], i });
                }
            }
        }

        return distances[m - 1][n - 1];
    }

    private int update(int x, int y) {
        return x == -1 || y < x ? y : x;
    }
}
// @lc code=end
