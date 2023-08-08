#
# @lc app=leetcode.cn id=1749 lang=python3
#
# [1749] 任意子数组和的绝对值的最大值
#
# https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/
#
# algorithms
# Medium (54.48%)
# Likes:    46
# Dislikes: 0
# Total Accepted:    10.5K
# Total Submissions: 17.9K
# Testcase Example:  '[1,-3,2,3,-4]'
#
# 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为
# abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
#
# 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
#
# abs(x) 定义如下：
#
#
# 如果 x 是负整数，那么 abs(x) = -x 。
# 如果 x 是非负整数，那么 abs(x) = x 。
#
#
#
#
# 示例 1：
#
#
# 输入：nums = [1,-3,2,3,-4]
# 输出：5
# 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
#
#
# 示例 2：
#
#
# 输入：nums = [2,-5,1,-4,3,-2]
# 输出：8
# 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
#
#
#
#
# 提示：
#
#
# 1
# -10^4
#
#
#


# @lc code=start
from itertools import accumulate


class Solution:
    def maxAbsoluteSum(self, nums: List[int]) -> int:
        # prefix = list(accumulate(nums, initial=0))
        # return max(prefix) - min(prefix)

        min_v, max_v, ans = 0, 0, 0
        for num in nums:
            max_v = max(max_v, 0) + num
            min_v = min(min_v, 0) + num

            ans = max(ans, -min_v, max_v)

        return ans


# @lc code=end
