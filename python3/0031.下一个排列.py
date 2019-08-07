#
# @lc app=leetcode.cn id=31 lang=python3
#
# [31] 下一个排列
#
# https://leetcode-cn.com/problems/next-permutation/description/
#
# algorithms
# Medium (29.11%)
# Likes:    237
# Dislikes: 0
# Total Accepted:    17.9K
# Total Submissions: 57.5K
# Testcase Example:  '[1,2,3]'
#
# 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
#
# 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
#
# 必须原地修改，只允许使用额外常数空间。
#
# 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
# 1,2,3 → 1,3,2
# 3,2,1 → 1,2,3
# 1,1,5 → 1,5,1
#
#


class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        if n <= 1:
            return
        elif n == 2:
            nums[0], nums[1] = nums[1], nums[0]
            return
        i, j = n - 2, n - 1
        while i >= 0 and nums[i] >= nums[i+1]:
            i -= 1
        if i >= 0:
            while nums[i] >= nums[j]:
                j -= 1
            nums[i], nums[j] = nums[j], nums[i]

        nums[i+1:] = reversed(nums[i+1:])
