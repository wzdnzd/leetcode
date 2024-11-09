/*
 * @lc app=leetcode.cn id=3242 lang=java
 *
 * [3242] 设计相邻元素求和服务
 *
 * https://leetcode.cn/problems/design-neighbor-sum-service/description/
 *
 * algorithms
 * Easy (82.52%)
 * Likes:    6
 * Dislikes: 0
 * Total Accepted:    6K
 * Total Submissions: 7.4K
 * Testcase Example:  '["NeighborSum","adjacentSum","adjacentSum","diagonalSum","diagonalSum"]\n' +
  '[[[[0,1,2],[3,4,5],[6,7,8]]],[1],[4],[4],[8]]'
 *
 * 给你一个 n x n 的二维数组 grid，它包含范围 [0, n^2 - 1] 内的不重复元素。
 * 
 * 实现 neighborSum 类：
 * 
 * 
 * neighborSum(int [][]grid) 初始化对象。
 * int adjacentSum(int value) 返回在 grid 中与 value 相邻的元素之和，相邻指的是与 value
 * 在上、左、右或下的元素。
 * int diagonalSum(int value) 返回在 grid 中与 value 对角线相邻的元素之和，对角线相邻指的是与 value
 * 在左上、右上、左下或右下的元素。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：
 * 
 * ["neighborSum", "adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"]
 * 
 * [[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]
 * 
 * 输出： [null, 6, 16, 16, 4]
 * 
 * 解释：
 * 
 * 
 * 
 * 
 * 1 的相邻元素是 0、2 和 4。
 * 4 的相邻元素是 1、3、5 和 7。
 * 4 的对角线相邻元素是 0、2、6 和 8。
 * 8 的对角线相邻元素是 4。
 * 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：
 * 
 * ["neighborSum", "adjacentSum", "diagonalSum"]
 * 
 * [[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15],
 * [9]]
 * 
 * 输出： [null, 23, 45]
 * 
 * 解释：
 * 
 * 
 * 
 * 
 * 15 的相邻元素是 0、10、7 和 6。
 * 9 的对角线相邻元素是 4、12、14 和 15。
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= n == grid.length == grid[0].length <= 10
 * 0 <= grid[i][j] <= n^2 - 1
 * 所有 grid[i][j] 值均不重复。
 * adjacentSum 和 diagonalSum 中的 value 均在范围 [0, n^2 - 1] 内。
 * 最多会调用 adjacentSum 和 diagonalSum 总共 2 * n^2 次。
 * 
 * 
 */

// @lc code=start
class NeighborSum {
    private static int[][] adjacentDirectories = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static int[][] diagonalDirectories = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    private int[][] grid;
    private int[][] positions;

    public NeighborSum(int[][] grid) {
        this.grid = grid;

        int n = grid.length;
        this.positions = new int[n * n][];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                positions[grid[i][j]] = new int[] { i, j };
        }
    }

    public int adjacentSum(int value) {
        int n = this.grid.length, sum = 0;
        int[] position = positions[value];

        for (int[] directories : adjacentDirectories) {
            int row = position[0] + directories[0], col = position[1] + directories[1];
            if (row >= 0 && row < n && col >= 0 && col < n)
                sum += grid[row][col];
        }

        return sum;
    }

    public int diagonalSum(int value) {
        int n = this.grid.length, sum = 0;
        int[] position = positions[value];

        for (int[] directories : diagonalDirectories) {
            int row = position[0] + directories[0], col = position[1] + directories[1];
            if (row >= 0 && row < n && col >= 0 && col < n)
                sum += grid[row][col];

        }

        return sum;
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */
// @lc code=end
