#
# @lc app=leetcode.cn id=41 lang=python3
#
# [41] 缺失的第一个正数
#
# https://leetcode-cn.com/problems/first-missing-positive/description/
#
# algorithms
# Hard (35.99%)
# Likes:    292
# Dislikes: 0
# Total Accepted:    26.1K
# Total Submissions: 71K
# Testcase Example:  '[1,2,0]'
#
# 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
#
# 示例 1:
#
# 输入: [1,2,0]
# 输出: 3
#
#
# 示例 2:
#
# 输入: [3,4,-1,1]
# 输出: 2
#
#
# 示例 3:
#
# 输入: [7,8,9,11,12]
# 输出: 1
#
#
# 说明:
#
# 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
#
#

# @lc code=start


class Solution:
    # https://leetcode-cn.com/problems/first-missing-positive/comments/8020
    def firstMissingPositive(self, nums: List[int]) -> int:
        n = len(nums)
        i = 0
        while i < n:
            if 0 < nums[i] <= n and nums[nums[i] - 1] != nums[i]:
                nums[nums[i] - 1], nums[i] = nums[i], nums[nums[i] - 1]
            else:
                i += 1
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1
        return n + 1
# @lc code=end
