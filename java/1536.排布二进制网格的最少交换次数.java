/*
 * @lc app=leetcode.cn id=1536 lang=java
 *
 * [1536] 排布二进制网格的最少交换次数
 *
 * https://leetcode.cn/problems/minimum-swaps-to-arrange-a-binary-grid/description/
 *
 * algorithms
 * Medium (48.86%)
 * Likes:    63
 * Dislikes: 0
 * Total Accepted:    7.1K
 * Total Submissions: 14.4K
 * Testcase Example:  '[[0,0,1],[1,1,0],[1,0,0]]'
 *
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。
 * 
 * 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。
 * 
 * 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。
 * 
 * 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 * 输出：3
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * 输出：-1
 * 解释：所有行都是一样的，交换相邻行无法使网格符合要求。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] 要么是 0 要么是 1 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zerosCounts = new int[n];

        for (int i = 0; i < n; i++) {
            int[] row = grid[i];
            int zeros = 0;
            for (int j = n - 1; j >= 0 && row[j] == 0; j--)
                zeros++;

            zerosCounts[i] = zeros;
        }

        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int minZeros = n - i - 1;
            if (zerosCounts[i] >= minZeros)
                continue;

            int firstRowIndex = -1;
            for (int j = i + 1; j < n; j++) {
                if (zerosCounts[j] >= minZeros) {
                    firstRowIndex = j;
                    break;
                }
            }

            if (firstRowIndex < 0)
                return -1;

            for (int j = firstRowIndex; j > i; j--) {
                swapAdjacent(zerosCounts, j);
                swaps++;
            }
        }

        return swaps;
    }

    private void swapAdjacent(int[] zerosCounts, int index) {
        int temp = zerosCounts[index];
        zerosCounts[index] = zerosCounts[index - 1];
        zerosCounts[index - 1] = temp;
    }
}
// @lc code=end
