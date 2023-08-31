#
# @lc app=leetcode.cn id=498 lang=python3
#
# [498] 对角线遍历
#
# https://leetcode.cn/problems/diagonal-traverse/description/
#
# algorithms
# Medium (55.88%)
# Likes:    462
# Dislikes: 0
# Total Accepted:    112.8K
# Total Submissions: 201.9K
# Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
#
# 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
#
#
#
# 示例 1：
#
#
# 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,4,7,5,3,6,8,9]
#
#
# 示例 2：
#
#
# 输入：mat = [[1,2],[3,4]]
# 输出：[1,2,3,4]
#
#
#
#
# 提示：
#
#
# m == mat.length
# n == mat[i].length
# 1 <= m, n <= 10^4
# 1 <= m * n <= 10^4
# -10^5 <= mat[i][j] <= 10^5
#
#
#


# @lc code=start
from typing import List

class Solution:
    def findDiagonalOrder(self, mat: List[List[int]]) -> List[int]:
        m, n, result = len(mat), len(mat[0]), []
        for i in range(m + n - 1):
            if i % 2 == 0:
                x = i if i < m else m - 1
                y = 0 if i < m else i - m + 1
                while x >= 0 and y < n:
                    result.append(mat[x][y])
                    x -= 1
                    y += 1
            else:
                x = 0 if i < n else i - n + 1
                y = i if i < n else n - 1
                while x < m and y >= 0:
                    result.append(mat[x][y])
                    x += 1
                    y -= 1
        
        return result

# @lc code=end
