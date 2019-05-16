#
# @lc app=leetcode.cn id=4 lang=python3
#
# [4] 寻找两个有序数组的中位数
#
# https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
#
# algorithms
# Hard (33.24%)
# Likes:    1050
# Dislikes: 0
# Total Accepted:    54.6K
# Total Submissions: 156.5K
# Testcase Example:  '[1,3]\n[2]'
#
# 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
#
# 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
#
# 你可以假设 nums1 和 nums2 不会同时为空。
#
# 示例 1:
#
# nums1 = [1, 3]
# nums2 = [2]
#
# 则中位数是 2.0
#
#
# 示例 2:
#
# nums1 = [1, 2]
# nums2 = [3, 4]
#
# 则中位数是 (2 + 3)/2 = 2.5
#
#
#


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        def findKth(arr1, x1, y1, arr2, x2, y2, k):
            m, n = y1 - x1 + 1, y2 - x2 + 1
            if m < n:
                return findKth(arr2, x2, y2, arr1, x1, y1, k)

            if n == 0:
                return arr1[x1+k-1]

            if k == 1:
                return min(arr1[x1], arr2[x2])

            k2 = min(k // 2, n)
            k1 = k - k2

            if arr1[x1+k1-1] == arr2[x2+k2-1]:
                return arr1[x1+k1-1]
            elif arr1[x1+k1-1] > arr2[x2+k2-1]:
                return findKth(arr1, x1, y1, arr2, x2+k2, y2, k-k2)
            else:
                return findKth(arr1, x1+k1, y1, arr2, x2, y2, k-k1)

        def find(arr1, arr2, k):
            return findKth(arr1, 0, len(arr1)-1, arr2, 0, len(arr2)-1, k)

        total = len(nums1) + len(nums2)
        if total % 2 == 0:
            c1 = find(nums1, nums2, total // 2)
            c2 = find(nums1, nums2, total // 2 + 1)
            return (c1 + c2) / 2.0
        else:
            return float(find(nums1, nums2, total // 2 + 1))
