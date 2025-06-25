/*
 * @lc app=leetcode.cn id=2040 lang=golang
 *
 * [2040] 两个有序数组的第 K 小乘积
 *
 * https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (35.61%)
 * Likes:    66
 * Dislikes: 0
 * Total Accepted:    5.1K
 * Total Submissions: 11.5K
 * Testcase Example:  '[2,5]\n[3,4]\n2'
 *
 * 给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，请你返回第 k （从 1 开始编号）小的
 * nums1[i] * nums2[j] 的乘积，其中 0 <= i < nums1.length 且 0 <= j < nums2.length
 * 。
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [2,5], nums2 = [3,4], k = 2
 * 输出：8
 * 解释：第 2 小的乘积计算如下：
 * - nums1[0] * nums2[0] = 2 * 3 = 6
 * - nums1[0] * nums2[1] = 2 * 4 = 8
 * 第 2 小的乘积为 8 。
 *
 *
 * 示例 2：
 *
 * 输入：nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
 * 输出：0
 * 解释：第 6 小的乘积计算如下：
 * - nums1[0] * nums2[1] = (-4) * 4 = -16
 * - nums1[0] * nums2[0] = (-4) * 2 = -8
 * - nums1[1] * nums2[1] = (-2) * 4 = -8
 * - nums1[1] * nums2[0] = (-2) * 2 = -4
 * - nums1[2] * nums2[0] = 0 * 2 = 0
 * - nums1[2] * nums2[1] = 0 * 4 = 0
 * 第 6 小的乘积为 0 。
 *
 *
 * 示例 3：
 *
 * 输入：nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
 * 输出：-6
 * 解释：第 3 小的乘积计算如下：
 * - nums1[0] * nums2[4] = (-2) * 5 = -10
 * - nums1[0] * nums2[3] = (-2) * 4 = -8
 * - nums1[4] * nums2[0] = 2 * (-3) = -6
 * 第 3 小的乘积为 -6 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums1.length, nums2.length <= 5 * 10^4
 * -10^5 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 和 nums2 都是从小到大排好序的。
 *
 *
 */

// @lc code=start
package main

func kthSmallestProduct(nums1 []int, nums2 []int, k int64) int64 {
	m, n, p1, p2 := len(nums1), len(nums2), 0, 0

	for p1 < m && nums1[p1] < 0 {
		p1++
	}

	for p2 < n && nums2[p2] < 0 {
		p2++
	}

	left, right := int64(-1e10), int64(1e10)
	for left <= right {
		mid := (left + right) / 2
		count := int64(0)
		i1, i2 := 0, p2-1

		for i1 < p1 && i2 >= 0 {
			if int64(nums1[i1])*int64(nums2[i2]) > mid {
				i1++
			} else {
				count += int64(p1 - i1)
				i2--
			}
		}

		i1, i2 = p1, n-1
		for i1 < m && i2 >= p2 {
			if int64(nums1[i1])*int64(nums2[i2]) > mid {
				i2--
			} else {
				count += int64(i2 - p2 + 1)
				i1++
			}
		}

		i1, i2 = 0, p2
		for i1 < p1 && i2 < n {
			if int64(nums1[i1])*int64(nums2[i2]) > mid {
				i2++
			} else {
				count += int64(n - i2)
				i1++
			}
		}

		i1, i2 = p1, 0
		for i1 < m && i2 < p2 {
			if int64(nums1[i1])*int64(nums2[i2]) > mid {
				i1++
			} else {
				count += int64(m - i1)
				i2++
			}
		}

		if count < k {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}

	return left
}

// @lc code=end
