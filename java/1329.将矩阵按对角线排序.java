/*
 * @lc app=leetcode.cn id=1329 lang=java
 *
 * [1329] 将矩阵按对角线排序
 *
 * https://leetcode.cn/problems/sort-the-matrix-diagonally/description/
 *
 * algorithms
 * Medium (77.92%)
 * Likes:    95
 * Dislikes: 0
 * Total Accepted:    14K
 * Total Submissions: 17.8K
 * Testcase Example:  '[[3,3,1,1],[2,2,1,2],[1,1,1,2]]'
 *
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 mat 有 6 行 3 列，从
 * mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 * 
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：mat =
 * [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * 
 * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] array = new int[Math.min(m, n)];

        // https://leetcode.cn/problems/sort-the-matrix-diagonally/solutions/2760094/dui-jiao-xian-pai-xu-fu-yuan-di-pai-xu-p-uts8
        for (int k = 1 - n; k < m; k++) {
            int lower = Math.max(k, 0);
            int upper = Math.min(k + n, m);

            for (int i = lower; i < upper; i++)
                array[i - lower] = mat[i][i - k];

            Arrays.sort(array, 0, upper - lower);
            for (int i = lower; i < upper; i++)
                mat[i][i - k] = array[i - lower];
        }

        return mat;
    }
}
// @lc code=end
