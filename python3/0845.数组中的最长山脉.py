#
# @lc app=leetcode.cn id=845 lang=python3
#
# [845] 数组中的最长山脉
#
# https://leetcode.cn/problems/longest-mountain-in-array/description/
#
# algorithms
# Medium (42.29%)
# Likes:    268
# Dislikes: 0
# Total Accepted:    49.6K
# Total Submissions: 117.4K
# Testcase Example:  '[2,1,4,7,3,2,5]'
#
# 把符合下列属性的数组 arr 称为 山脉数组 ：
#
#
# arr.length >= 3
# 存在下标 i（0 < i < arr.length - 1），满足
#
# arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
# arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
#
#
#
#
# 给出一个整数数组 arr，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 0 。
#
#
#
# 示例 1：
#
#
# 输入：arr = [2,1,4,7,3,2,5]
# 输出：5
# 解释：最长的山脉子数组是 [1,4,7,3,2]，长度为 5。
#
#
# 示例 2：
#
#
# 输入：arr = [2,2,2]
# 输出：0
# 解释：不存在山脉子数组。
#
#
#
#
# 提示：
#
#
# 1 <= arr.length <= 10^4
# 0 <= arr[i] <= 10^4
#
#
#
#
# 进阶：
#
#
# 你可以仅用一趟扫描解决此问题吗？
# 你可以用 O(1) 空间解决此问题吗？
#
#
#

# @lc code=start
from typing import List


class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        if len(arr) < 3:
            return 0

        l, r, n, ans = 0, 1, len(arr), 0
        while r < n:
            increasing = False
            while r < n and arr[r] > arr[r - 1]:
                r += 1
                increasing = True

            if increasing and r < n and arr[r] < arr[r - 1]:
                while r < n and arr[r] < arr[r - 1]:
                    r += 1

                ans = max(ans, r - l)
                l = r - 1
            else:
                l = r
                r += 1

        return ans

# @lc code=end
