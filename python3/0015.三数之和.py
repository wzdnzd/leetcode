#
# @lc app=leetcode.cn id=15 lang=python3
#
# [15] 三数之和
#
# https://leetcode-cn.com/problems/3sum/description/
#
# algorithms
# Medium (19.98%)
# Likes:    975
# Dislikes: 0
# Total Accepted:    54.7K
# Total Submissions: 245.1K
# Testcase Example:  '[-1,0,1,2,-1,-4]'
#
# 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
# ？找出所有满足条件且不重复的三元组。
#
# 注意：答案中不可以包含重复的三元组。
#
# 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
#
# 满足要求的三元组集合为：
# [
# ⁠ [-1, 0, 1],
# ⁠ [-1, -1, 2]
# ]
#
#
#
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if nums is None or len(nums) < 3:
            return []

        nums.sort()
        result = set()

        for i, v in enumerate(nums[:-2]):
            if i >= 1 and v == nums[i - 1]:
                continue
            lookup = {}
            for x in nums[i + 1:]:
                if x not in lookup:
                    lookup[-v - x] = 1
                else:
                    result.add((v, -v - x, x))
        return list(map(list, result))
