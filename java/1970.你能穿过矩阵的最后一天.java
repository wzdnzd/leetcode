/*
 * @lc app=leetcode.cn id=1970 lang=java
 *
 * [1970] 你能穿过矩阵的最后一天
 *
 * https://leetcode.cn/problems/last-day-where-you-can-still-cross/description/
 *
 * algorithms
 * Hard (53.60%)
 * Likes:    66
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 10.8K
 * Testcase Example:  '2\n2\n[[1,1],[2,1],[1,2],[2,2]]'
 *
 * 给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。同时给你 row 和 col 分别表示矩阵中行和列的数目。
 * 
 * 一开始在第 0 天，整个 矩阵都是 陆地 。但每一天都会有一块新陆地被 水 淹没变成水域。给你一个下标从 1 开始的二维数组 cells ，其中
 * cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1
 * ）。
 * 
 * 你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。你可以从最上面一行的 任意 格子出发，到达最下面一行的 任意
 * 格子。你只能沿着 四个 基本方向移动（也就是上下左右）。
 * 
 * 请返回只经过陆地格子能从最 上面 一行走到最 下面 一行的 最后一天 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * 输出：2
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 2 天。
 * 
 * 
 * 示例 2：
 * 
 * 输入：row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
 * 输出：1
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 1 天。
 * 
 * 
 * 示例 3：
 * 
 * 输入：row = 3, col = 3, cells =
 * [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
 * 输出：3
 * 解释：上图描述了矩阵从第 0 天开始是如何变化的。
 * 可以从最上面一行到最下面一行的最后一天是第 3 天。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 2 <= row, col <= 2 * 10^4
 * 4 <= row * col <= 2 * 10^4
 * cells.length == row * col
 * 1 <= ri <= row
 * 1 <= ci <= col
 * cells 中的所有格子坐标都是 唯一 的。
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    private static final int LAND = 0, WATER = 1;
    private static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int latestDayToCross(int row, int col, int[][] cells) {
        int latestDay = -1;
        int[][] matrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                Arrays.fill(matrix[i], WATER);
        }

        int total = row * col;
        UnionFind uf = new UnionFind(total + 2);

        for (int i = total - 1; i >= 0 && latestDay < 0; i--) {
            int currentRow = cells[i][0] - 1, currentCol = cells[i][1] - 1;
            matrix[currentRow][currentCol] = LAND;
            int currIndex = getIndex(currentRow, currentCol, col);

            if (currentRow == 0)
                uf.union(currIndex, total);

            if (currentRow == row - 1)
                uf.union(currIndex, total + 1);

            for (int[] direction : DIRECTIONS) {
                int nextRow = currentRow + direction[0], nextCol = currentCol + direction[1];

                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                        && matrix[nextRow][nextCol] == LAND) {
                    int nextIndex = getIndex(nextRow, nextCol, col);
                    uf.union(currIndex, nextIndex);
                }
            }

            if (uf.find(total) == uf.find(total + 1))
                latestDay = i;
        }

        return latestDay;
    }

    private int getIndex(int currRow, int currCol, int col) {
        return currRow * col + currCol;
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
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (ranks[rootX] > ranks[rootY])
                parents[rootY] = rootX;
            else if (ranks[rootX] < ranks[rootY])
                parents[rootX] = rootY;
            else {
                parents[rootY] = rootX;
                ranks[rootX]++;
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
