#
# @lc app=leetcode.cn id=54 lang=python3
#
# [54] 螺旋矩阵
#
# https://leetcode.cn/problems/spiral-matrix/description/
#
# algorithms
# Medium (49.19%)
# Likes:    1348
# Dislikes: 0
# Total Accepted:    357K
# Total Submissions: 724.7K
# Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
#
# 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
#
#
#
# 示例 1：
#
#
# 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,3,6,9,8,7,4,5]
#
#
# 示例 2：
#
#
# 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
# 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
#
#
#
#
# 提示：
#
#
# m == matrix.length
# n == matrix[i].length
# 1
# -100
#
#
#


# @lc code=start


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or len(matrix[0]) == 0:
            return []

        r, c, m, n, ans = 0, 0, len(matrix), len(matrix[0]), []
        while r < m and c < n:
            # 从左到右
            ans.extend([matrix[r][i] for i in range(c, n)])
            # 从上到下
            ans.extend([matrix[i][n - 1] for i in range(r + 1, m)])

            if r < m - 1 and c < n - 1:
                # 从右到左
                ans.extend([matrix[m - 1][i] for i in range(n - 2, c - 1, -1)])
                # 从下到上
                ans.extend([matrix[i][c] for i in range(m - 2, r, -1)])

            r, m, c, n = r + 1, m - 1, c + 1, n - 1

        return ans


# @lc code=end
