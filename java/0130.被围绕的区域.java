/*
 * @lc app=leetcode.cn id=130 lang=java
 *
 * [130] 被围绕的区域
 *
 * https://leetcode.cn/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (45.96%)
 * Likes:    866
 * Dislikes: 0
 * Total Accepted:    198.7K
 * Total Submissions: 432.2K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X'
 * 填充。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：board =
 * [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == board.length
 * n == board[i].length
 * 1 
 * board[i][j] 为 'X' 或 'O'
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    private final char signal = 'A';

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int i = 1; i < n - 1; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == signal)
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        int m = board.length, n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n)
            return;
        if (board[x][y] != 'O')
            return;

        board[x][y] = signal;
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }
}
// @lc code=end
