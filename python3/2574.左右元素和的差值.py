#
# @lc app=leetcode.cn id=2574 lang=python3
#
# [2574] 左右元素和的差值
#
# https://leetcode.cn/problems/left-and-right-sum-differences/description/
#
# algorithms
# Easy (84.54%)
# Likes:    15
# Dislikes: 0
# Total Accepted:    11.9K
# Total Submissions: 14.1K
# Testcase Example:  '[10,4,8,3]'
#
# 给你一个下标从 0 开始的整数数组 nums ，请你找出一个下标从 0 开始的整数数组 answer ，其中：
# 
# 
# answer.length == nums.length
# answer[i] = |leftSum[i] - rightSum[i]|
# 
# 
# 其中：
# 
# 
# leftSum[i] 是数组 nums 中下标 i 左侧元素之和。如果不存在对应的元素，leftSum[i] = 0 。
# rightSum[i] 是数组 nums 中下标 i 右侧元素之和。如果不存在对应的元素，rightSum[i] = 0 。
# 
# 
# 返回数组 answer 。
# 
# 
# 
# 示例 1：
# 
# 输入：nums = [10,4,8,3]
# 输出：[15,1,11,22]
# 解释：数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
# 数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
# 
# 
# 示例 2：
# 
# 输入：nums = [1]
# 输出：[0]
# 解释：数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
# 数组 answer 为 [|0 - 0|] = [0] 。
# 
# 
# 
# 
# 提示：
# 
# 
# 1 <= nums.length <= 1000
# 1 <= nums[i] <= 10^5
# 
# 
#

# @lc code=start
class Solution:
    def leftRightDifference(self, nums: List[int]) -> List[int]:
        total, left, right = sum(nums), [0], []
        for i in range(len(nums) - 1):
            left.append(left[-1] + nums[i])
            total -= nums[i]
            right.append(total)

        right.append(0)
        return [abs(left[i] - right[i]) for i in range(len(left))]
# @lc code=end

