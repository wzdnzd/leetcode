#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Author: wzdnzd
Date: 2021-12-12 19:27:01
Description: 
Copyright (c) 2022 by wzdnzd, All Rights Reserved.
"""
#
# @lc app=leetcode.cn id=1 lang=python3
#
# [1] 两数之和
#
# https://leetcode-cn.com/problems/two-sum/description/
#
# algorithms
# Easy (44.24%)
# Likes:    5160
# Dislikes: 0
# Total Accepted:    368K
# Total Submissions: 801.3K
# Testcase Example:  '[2,7,11,15]\n9'
#
# 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
#
# 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
#
# 示例:
#
# 给定 nums = [2, 7, 11, 15], target = 9
#
# 因为 nums[0] + nums[1] = 2 + 7 = 9
# 所以返回 [0, 1]
#
#
#


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        lookup = {}
        for index, num in enumerate(nums):
            gap = target - num
            if lookup.get(gap) is not None:
                return [lookup.get(gap), index]
            else:
                lookup[num] = index
