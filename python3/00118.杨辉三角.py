#
# @lc app=leetcode.cn id=118 lang=python3
#
# [118] 杨辉三角
#
# https://leetcode-cn.com/problems/pascals-triangle/description/
#
# algorithms
# Easy (59.28%)
# Total Accepted:    19.9K
# Total Submissions: 32.6K
# Testcase Example:  '5'
#
# 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
#
#
#
# 在杨辉三角中，每个数是它左上方和右上方的数的和。
#
# 示例:
#
# 输入: 5
# 输出:
# [
# ⁠    [1],
# ⁠   [1,1],
# ⁠  [1,2,1],
# ⁠ [1,3,3,1],
# ⁠[1,4,6,4,1]
# ]
#
#


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        result = []
        for i in range(1, numRows+1):
            array = []
            for j in range(i):
                if j < 1 or j == i - 1:
                    array.append(1)
                else:
                    array.append(result[i-2][j-1] + result[i-2][j])

            result.append(array)

        return result
