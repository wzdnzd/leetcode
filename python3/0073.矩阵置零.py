#
# @lc app=leetcode.cn id=73 lang=python3
#
# [73] 矩阵置零
#
# https://leetcode.cn/problems/set-matrix-zeroes/description/
#
# algorithms
# Medium (62.53%)
# Likes:    876
# Dislikes: 0
# Total Accepted:    251.1K
# Total Submissions: 398K
# Testcase Example:  '[[1,1,1],[1,0,1],[1,1,1]]'
#
# 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
#
#
#
#
#
#
# 示例 1：
#
#
# 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
# 输出：[[1,0,1],[0,0,0],[1,0,1]]
#
#
# 示例 2：
#
#
# 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
# 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
#
#
#
#
# 提示：
#
#
# m == matrix.length
# n == matrix[0].length
# 1 <= m, n <= 200
# -2^31 <= matrix[i][j] <= 2^31 - 1
#
#
#
#
# 进阶：
#
#
# 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
# 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
# 你能想出一个仅使用常量空间的解决方案吗？
#
#
#


# @lc code=start
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        # m, n = len(matrix), len(matrix[0])
        # rows, cols = set(), set()
        # for i in range(m):
        #     for j in range(n):
        #         if matrix[i][j] == 0:
        #             rows.add(i)
        #             cols.add(j)

        # for r in rows:
        #     for c in range(n):
        #         matrix[r][c] = 0
        # for c in cols:
        #     for r in range(m):
        #         matrix[r][c] = 0

        m, n = len(matrix), len(matrix[0])

        # whether there is a 0 in the first row or column
        row = any(matrix[0][i] == 0 for i in range(n))
        col = any(matrix[j][0] == 0 for j in range(m))

        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[0][j] = matrix[i][0] = 0

        for i in range(1, m):
            for j in range(1, n):
                if matrix[0][j] == 0 or matrix[i][0] == 0:
                    matrix[i][j] = 0

        # first column contains 0
        if col:
            for i in range(m):
                matrix[i][0] = 0

        # first row contains 0
        if row:
            for j in range(n):
                matrix[0][j] = 0


# @lc code=end
