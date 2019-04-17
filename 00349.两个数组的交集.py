#
# @lc app=leetcode.cn id=349 lang=python3
#
# [349] 两个数组的交集
#
# https://leetcode-cn.com/problems/intersection-of-two-arrays/description/
#
# algorithms
# Easy (58.97%)
# Total Accepted:    17.9K
# Total Submissions: 29.1K
# Testcase Example:  '[1,2,2,1]\n[2,2]'
#
# 给定两个数组，编写一个函数来计算它们的交集。
#
# 示例 1:
#
# 输入: nums1 = [1,2,2,1], nums2 = [2,2]
# 输出: [2]
#
#
# 示例 2:
#
# 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
# 输出: [9,4]
#
# 说明:
#
#
# 输出结果中的每个元素一定是唯一的。
# 我们可以不考虑输出结果的顺序。
#
#
#


class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        # if len(nums1) > len(nums2):
        #     nums1, nums2 = nums2, nums1

        # result = []
        # for num in nums1:
        #     if num in nums2 and num not in result:
        #         result.append(num)

        # return result

        return list(set(nums1) & set(nums2))
