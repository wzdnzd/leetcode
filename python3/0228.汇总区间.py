#
# @lc app=leetcode.cn id=228 lang=python3
#
# [228] 汇总区间
#
# https://leetcode.cn/problems/summary-ranges/description/
#
# algorithms
# Easy (54.39%)
# Likes:    284
# Dislikes: 0
# Total Accepted:    102.4K
# Total Submissions: 187.9K
# Testcase Example:  '[0,1,2,4,5,7]'
#
# 给定一个  无重复元素 的 有序 整数数组 nums 。
# 
# 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于
# nums 的数字 x 。
# 
# 列表中的每个区间范围 [a,b] 应该按如下格式输出：
# 
# 
# "a->b" ，如果 a != b
# "a" ，如果 a == b
# 
# 
# 
# 
# 示例 1：
# 
# 
# 输入：nums = [0,1,2,4,5,7]
# 输出：["0->2","4->5","7"]
# 解释：区间范围是：
# [0,2] --> "0->2"
# [4,5] --> "4->5"
# [7,7] --> "7"
# 
# 
# 示例 2：
# 
# 
# 输入：nums = [0,2,3,4,6,8,9]
# 输出：["0","2->4","6","8->9"]
# 解释：区间范围是：
# [0,0] --> "0"
# [2,4] --> "2->4"
# [6,6] --> "6"
# [8,9] --> "8->9"
# 
# 
# 
# 
# 提示：
# 
# 
# 0 <= nums.length <= 20
# -2^31 <= nums[i] <= 2^31 - 1
# nums 中的所有值都 互不相同
# nums 按升序排列
# 
# 
#

# @lc code=start
from typing import List
class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        if not nums:
            return []
                
        left, right, ans = 0, 1, []            
        while right < len(nums):
            while right < len(nums) and nums[right] == nums[right-1] + 1:
                right += 1
            
            ranges = f"{nums[left]}"
            if nums[right-1] != nums[left]:
                ranges += f"->{nums[right-1]}"
            ans.append(ranges)
            left = right
            right += 1
        
        if left != len(nums):
            ans.append(str(nums[-1]))
        
        return ans
# @lc code=end

