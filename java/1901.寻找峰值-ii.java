/*
 * @lc app=leetcode.cn id=1901 lang=java
 *
 * [1901] 寻找峰值 II
 *
 * https://leetcode.cn/problems/find-a-peak-element-ii/description/
 *
 * algorithms
 * Medium (58.21%)
 * Likes:    95
 * Dislikes: 0
 * Total Accepted:    9.5K
 * Total Submissions: 15.9K
 * Testcase Example:  '[[1,4],[3,2]]'
 *
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * 
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并
 * 返回其位置 [i,j] 。
 * 
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * 
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * 
 * 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 
 * 
 * 输入: mat = [[1,4],[3,2]]
 * 输出: [0,1]
 * 解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 
 * 
 * 输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * 输出: [1,1]
 * 解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 10^5
 * 任意两个相邻元素均不相等.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int low = 0, high = m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int i = -1, maxVal = -1;

            for (int j = 0; j < n; j++) {
                if (mat[mid][j] > maxVal) {
                    maxVal = mat[mid][j];
                    i = j;
                }
            }

            if (mid - 1 >= 0 && mat[mid - 1][i] > maxVal)
                high = mid - 1;
            else if (mid + 1 < m && mat[mid + 1][i] > maxVal)
                low = mid + 1;
            else
                return new int[] { mid, i };
        }

        return new int[] { -1, -1 };
    }
}
// @lc code=end
