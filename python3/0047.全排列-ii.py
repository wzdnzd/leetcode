#
# @lc app=leetcode.cn id=47 lang=python3
#
# [47] 全排列 II
#
# https://leetcode-cn.com/problems/permutations-ii/description/
#
# algorithms
# Medium (50.07%)
# Likes:    103
# Dislikes: 0
# Total Accepted:    12.6K
# Total Submissions: 24.1K
# Testcase Example:  '[1,1,2]'
#
# 给定一个可包含重复数字的序列，返回所有不重复的全排列。
#
# 示例:
#
# 输入: [1,1,2]
# 输出:
# [
# ⁠ [1,1,2],
# ⁠ [1,2,1],
# ⁠ [2,1,1]
# ]
#
#


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        if len(nums) <= 1:
            return [nums]

        nums.sort()

        result, index = [], 0
        for index in range(len(nums)):
            if index > 0 and nums[index] == nums[index-1]:
                continue

            tmp = nums[:]
            num = tmp.pop(index)

            for item in self.permuteUnique(tmp):
                result.append([num] + item)

        return result
