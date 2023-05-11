#
# @lc app=leetcode.cn id=59 lang=python3
#
# [59] 螺旋矩阵 II
#
# https://leetcode.cn/problems/spiral-matrix-ii/description/
#
# algorithms
# Medium (74.71%)
# Likes:    1049
# Dislikes: 0
# Total Accepted:    314.3K
# Total Submissions: 429.6K
# Testcase Example:  '3'
#
# 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
#
#
#
# 示例 1：
#
#
# 输入：n = 3
# 输出：[[1,2,3],[8,9,4],[7,6,5]]
#
#
# 示例 2：
#
#
# 输入：n = 1
# 输出：[[1]]
#
#
#
#
# 提示：
#
#
# 1
#
#
#


# @lc code=start
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        matrix = [[0] * n for _ in range(n)]

        r, c, m, n, num = 0, 0, n, n, 1
        while r < m and c < n:
            # 从左到右
            for i in range(c, n):
                matrix[r][i] = num
                num += 1
            # 从上到下
            for i in range(r + 1, m):
                matrix[i][n - 1] = num
                num += 1

            if r < m - 1 and c < n - 1:
                # 从右到左
                for i in range(n - 2, c - 1, -1):
                    matrix[m - 1][i] = num
                    num += 1
                # 从下到上
                for i in range(m - 2, r, -1):
                    matrix[i][c] = num
                    num += 1

            r, m, c, n = r + 1, m - 1, c + 1, n - 1

        return matrix


# @lc code=end
