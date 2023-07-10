#
# @lc app=leetcode.cn id=494 lang=python3
#
# [494] 目标和
#
# https://leetcode.cn/problems/target-sum/description/
#
# algorithms
# Medium (49.09%)
# Likes:    1407
# Dislikes: 0
# Total Accepted:    287.1K
# Total Submissions: 584.4K
# Testcase Example:  '[1,1,1,1,1]\n3'
#
# 给你一个整数数组 nums 和一个整数 target 。
#
# 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
#
#
# 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
#
#
# 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
#
#
#
# 示例 1：
#
#
# 输入：nums = [1,1,1,1,1], target = 3
# 输出：5
# 解释：一共有 5 种方法让最终目标和为 3 。
# -1 + 1 + 1 + 1 + 1 = 3
# +1 - 1 + 1 + 1 + 1 = 3
# +1 + 1 - 1 + 1 + 1 = 3
# +1 + 1 + 1 - 1 + 1 = 3
# +1 + 1 + 1 + 1 - 1 = 3
#
#
# 示例 2：
#
#
# 输入：nums = [1], target = 1
# 输出：1
#
#
#
#
# 提示：
#
#
# 1
# 0
# 0
# -1000
#
#
#


# @lc code=start
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        total, n = sum(nums), len(nums)
        if total < abs(target) or (total + target) % 2 == 1:
            return 0

        positive = (total + target) // 2
        dp = [0] * (positive + 1)
        dp[0] = 1

        for i in range(0, n):
            for j in range(positive, nums[i] - 1, -1):
                dp[j] += dp[j - nums[i]]

        return dp[positive]


# @lc code=end
