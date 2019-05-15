#
# @lc app=leetcode.cn id=268 lang=python3
#
# [268] 缺失数字
#
# https://leetcode-cn.com/problems/missing-number/description/
#
# algorithms
# Easy (47.37%)
# Total Accepted:    17.7K
# Total Submissions: 35.8K
# Testcase Example:  '[3,0,1]'
#
# 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
#
# 示例 1:
#
# 输入: [3,0,1]
# 输出: 2
#
#
# 示例 2:
#
# 输入: [9,6,4,2,3,5,7,0,1]
# 输出: 8
#
#
# 说明:
# 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
#
#


class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        # method 1
        # nums = sorted(nums)
        # if nums[0] != 0:
        #     return 0

        # for i in range(1, len(nums)):
        #     if nums[i] - nums[i - 1] != 1:
        #         return nums[i] - 1

        # return len(nums)

        # method 2
        # return len(nums) * (len(nums) + 1) // 2 - sum(nums)

        # method 3

        count = len(nums)
        for i in range(count):
            count ^= nums[i]
            count ^= i

        return count
