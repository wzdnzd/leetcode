#
# @lc app=leetcode.cn id=16 lang=python3
#
# [16] 最接近的三数之和
#
# https://leetcode-cn.com/problems/3sum-closest/description/
#
# algorithms
# Medium (38.10%)
# Likes:    184
# Dislikes: 0
# Total Accepted:    25.6K
# Total Submissions: 63.2K
# Testcase Example:  '[-1,2,1,-4]\n1'
#
# 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
# 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
#
# 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
#
# 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
#
#
#


class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        n, result, diff = len(nums), None, float('inf')
        nums.sort()

        for i in range(n):
            if i > 0 and nums[i] == nums[i-1]:
                continue
            l, r = i+1, n-1
            while l < r:
                tmp = nums[i] + nums[l] + nums[r]
                if tmp == target:
                    return target
                elif tmp > target:
                    r -= 1
                    if abs(tmp-target) < diff:
                        diff = abs(tmp-target)
                        result = tmp
                    while l < r and nums[r] == nums[r+1]:
                        r -= 1
                else:
                    l += 1
                    if abs(tmp-target) < diff:
                        diff = abs(tmp-target)
                        result = tmp
                    while l < r and nums[l] == nums[l-1]:
                        l += 1
        return result
