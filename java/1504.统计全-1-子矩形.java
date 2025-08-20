/*
 * @lc app=leetcode.cn id=1504 lang=java
 *
 * [1504] 统计全 1 子矩形
 *
 * https://leetcode.cn/problems/count-submatrices-with-all-ones/description/
 *
 * algorithms
 * Medium (65.01%)
 * Likes:    219
 * Dislikes: 0
 * Total Accepted:    17.8K
 * Total Submissions: 27.4K
 * Testcase Example:  '[[1,0,1],[1,1,0],[1,1,0]]'
 *
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：mat = [[1,0,1],[1,1,0],[1,1,0]]
 * 输出：13
 * 解释：
 * 有 6 个 1x1 的矩形。
 * 有 2 个 1x2 的矩形。
 * 有 3 个 2x1 的矩形。
 * 有 1 个 2x2 的矩形。
 * 有 1 个 3x1 的矩形。
 * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 
 * 输入：mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
 * 输出：24
 * 解释：
 * 有 8 个 1x1 的子矩形。
 * 有 5 个 1x2 的子矩形。
 * 有 2 个 1x3 的子矩形。
 * 有 4 个 2x1 的子矩形。
 * 有 2 个 2x2 的子矩形。
 * 有 2 个 3x1 的子矩形。
 * 有 1 个 3x2 的子矩形。
 * 矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= m, n <= 150
 * mat[i][j] 仅包含 0 或 1
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int numSubmat(int[][] mat) {
        int submatrices = 0;
        int m = mat.length, n = mat[0].length;
        int[] heights = new int[n];

        for (int i = 0; i < m; i++) {
            int currSubmatrices = 0;
            Deque<int[]> stack = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    heights[j] = 0;
                else
                    heights[j]++;

                int width = 1, height = heights[j];
                while (!stack.isEmpty() && stack.peek()[1] >= height) {
                    int[] prev = stack.pop();
                    currSubmatrices -= prev[0] * (prev[1] - height);
                    width += prev[0];
                }

                currSubmatrices += height;
                submatrices += currSubmatrices;
                stack.push(new int[] { width, height });
            }
        }

        return submatrices;
    }
}
// @lc code=end
