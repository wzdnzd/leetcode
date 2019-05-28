#
# @lc app=leetcode.cn id=42 lang=python3
#
# [42] 接雨水
#
# https://leetcode-cn.com/problems/trapping-rain-water/description/
#
# algorithms
# Hard (40.78%)
# Likes:    414
# Dislikes: 0
# Total Accepted:    14.5K
# Total Submissions: 32.9K
# Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
#
# 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
#
#
#
# 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
# Marcos 贡献此图。
#
# 示例:
#
# 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
# 输出: 6
#
#


class Solution:
    def trap(self, height: List[int]) -> int:
        l, r, result, min_height = 0, len(height) - 1, 0, 0
        while l < r:
            min_height = min(height[l], height[r])
            while l < r and height[l] <= min_height:
                result += min_height - height[l]
                l += 1
            while l < r and height[r] <= min_height:
                result += min_height - height[r]
                r -= 1

        return result
