#
# @lc app=leetcode.cn id=2248 lang=python3
#
# [2248] 多个数组求交集
#
# https://leetcode.cn/problems/intersection-of-multiple-arrays/description/
#
# algorithms
# Easy (67.10%)
# Likes:    29
# Dislikes: 0
# Total Accepted:    15.9K
# Total Submissions: 23.8K
# Testcase Example:  '[[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]'
#
# 给你一个二维整数数组 nums ，其中 nums[i] 是由 不同 正整数组成的一个非空数组，按 升序排列 返回一个数组，数组中的每个元素在 nums
# 所有数组 中都出现过。
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
# 输出：[3,4]
# 解释：
# nums[0] = [3,1,2,4,5]，nums[1] = [1,2,3,4]，nums[2] = [3,4,5,6]，在 nums
# 中每个数组中都出现的数字是 3 和 4 ，所以返回 [3,4] 。
# 
# 示例 2：
# 
# 
# 输入：nums = [[1,2,3],[4,5,6]]
# 输出：[]
# 解释：
# 不存在同时出现在 nums[0] 和 nums[1] 的整数，所以返回一个空列表 [] 。
# 
# 
# 
# 
# 提示：
# 
# 
# 1 <= nums.length <= 1000
# 1 <= sum(nums[i].length) <= 1000
# 1 <= nums[i][j] <= 1000
# nums[i] 中的所有值 互不相同
# 
# 
#

# @lc code=start
class Solution:
    def intersection(self, nums: List[List[int]]) -> List[int]:
        ans = set(nums[0])
        for i in range(1, len(nums)):
            ans = ans.intersection(nums[i])
            if len(ans) == 0:
                break
        
        return  sorted(list(ans))
# @lc code=end

