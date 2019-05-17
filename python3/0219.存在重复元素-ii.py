#
# @lc app=leetcode.cn id=219 lang=python3
#
# [219] 存在重复元素 II
#
# https://leetcode-cn.com/problems/contains-duplicate-ii/description/
#
# algorithms
# Easy (32.55%)
# Total Accepted:    11.7K
# Total Submissions: 34.5K
# Testcase Example:  '[1,2,3,1]\n3'
#
# 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j
# 的差的绝对值最大为 k。
#
# 示例 1:
#
# 输入: nums = [1,2,3,1], k = 3
# 输出: true
#
# 示例 2:
#
# 输入: nums = [1,0,1,1], k = 1
# 输出: true
#
# 示例 3:
#
# 输入: nums = [1,2,3,1,2,3], k = 2
# 输出: false
#
#


class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        has_dict = {}
        for index, num in enumerate(nums):
            if num in has_dict.keys() and abs(index - has_dict[num]) <= k:
                return True

            has_dict[num] = index

        return False
