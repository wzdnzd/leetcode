#
# @lc app=leetcode.cn id=209 lang=python3
#
# [209] 长度最小的子数组
#
# https://leetcode.cn/problems/minimum-size-subarray-sum/description/
#
# algorithms
# Medium (46.67%)
# Likes:    1782
# Dislikes: 0
# Total Accepted:    575.8K
# Total Submissions: 1.2M
# Testcase Example:  '7\n[2,3,1,2,4,3]'
#
# 给定一个含有 n 个正整数的数组和一个正整数 target 。
#
# 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
# ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
#
#
#
# 示例 1：
#
#
# 输入：target = 7, nums = [2,3,1,2,4,3]
# 输出：2
# 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
#
#
# 示例 2：
#
#
# 输入：target = 4, nums = [1,4,4]
# 输出：1
#
#
# 示例 3：
#
#
# 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
# 输出：0
#
#
#
#
# 提示：
#
#
# 1
# 1
# 1
#
#
#
#
# 进阶：
#
#
# 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
#
#
#


# @lc code=start
from typing import List


class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        prefix, n = [0], len(nums)
        for i in range(n):
            prefix.append(prefix[i] + nums[i])

        if prefix[-1] < target:
            return 0

        if prefix[-1] == target:
            return n

        ans, l, r = float("inf"), 0, 0
        while True:
            while r <= n and prefix[r] - prefix[l] < target:
                r += 1

            if r == n + 1:
                break

            while l < r and prefix[r] - prefix[l] >= target:
                l += 1

            flag = 0 if prefix[r] - prefix[l] >= target else 1
            ans = min(ans, r - l + flag)

        return ans


# @lc code=end
