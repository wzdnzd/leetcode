#
# @lc app=leetcode.cn id=503 lang=python3
#
# [503] 下一个更大元素 II
#
# https://leetcode.cn/problems/next-greater-element-ii/description/
#
# algorithms
# Medium (65.99%)
# Likes:    705
# Dislikes: 0
# Total Accepted:    164.1K
# Total Submissions: 248.7K
# Testcase Example:  '[1,2,1]'
#
# 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的
# 下一个更大元素 。
#
# 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1
# 。
#
#
#
# 示例 1:
#
#
# 输入: nums = [1,2,1]
# 输出: [2,-1,2]
# 解释: 第一个 1 的下一个更大的数是 2；
# 数字 2 找不到下一个更大的数；
# 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
#
#
# 示例 2:
#
#
# 输入: nums = [1,2,3,4,3]
# 输出: [2,3,4,-1,4]
#
#
#
#
# 提示:
#
#
# 1 <= nums.length <= 10^4
# -10^9 <= nums[i] <= 10^9
#
#
#


# @lc code=start
class Solution:
    def nextGreaterElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        stack, ans = [], [-1] * n
        for i in range(2 * n):
            index = i % n
            while stack and nums[index] > nums[stack[-1]]:
                ans[stack.pop()] = nums[index]

            stack.append(index)

        return ans


# @lc code=end
