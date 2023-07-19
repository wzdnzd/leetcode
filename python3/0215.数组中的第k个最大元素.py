#
# @lc app=leetcode.cn id=215 lang=python3
#
# [215] 数组中的第K个最大元素
#
# https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
#
# algorithms
# Medium (63.64%)
# Likes:    2215
# Dislikes: 0
# Total Accepted:    884.6K
# Total Submissions: 1.4M
# Testcase Example:  '[3,2,1,5,6,4]\n2'
#
# 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
#
# 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
#
# 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
#
#
#
# 示例 1:
#
#
# 输入: [3,2,1,5,6,4], k = 2
# 输出: 5
#
#
# 示例 2:
#
#
# 输入: [3,2,3,1,2,4,5,5,6], k = 4
# 输出: 4
#
#
#
# 提示：
#
#
# 1 <= k <= nums.length <= 10^5
# -10^4 <= nums[i] <= 10^4
#
#
#

# @lc code=start
from random import random


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        return self.__findKthLargestHelper(nums, 0, len(nums) - 1, len(nums) - k)

    def __findKthLargestHelper(
        self, nums: List[int], start: int, end: int, k: int
    ) -> int:
        if start >= end:
            return nums[start]

        index = self.__partition(nums, start, end)
        if index == k:
            return nums[index]
        if index < k:
            return self.__findKthLargestHelper(nums, index + 1, end, k)

        return self.__findKthLargestHelper(nums, start, index - 1, k)

    def __partition(self, nums: List[int], start: int, end: int):
        index = start + int((end - start) * random())
        nums[start], nums[index] = nums[index], nums[start]
        pivot, left, right = nums[start], start, end

        while left < right:
            while left < right and nums[right] >= pivot:
                right -= 1
            while left < right and nums[left] <= pivot:
                left += 1

            nums[left], nums[right] = nums[right], nums[left]

        nums[start], nums[left] = nums[left], nums[start]
        return left


# @lc code=end
