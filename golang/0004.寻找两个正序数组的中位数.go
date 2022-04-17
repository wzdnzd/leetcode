/*
 * @lc app=leetcode.cn id=4 lang=golang
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (41.14%)
 * Likes:    5264
 * Dislikes: 0
 * Total Accepted:    665.4K
 * Total Submissions: 1.6M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 *
 *
 *
 *
 *
 * 提示：
 *
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 *
 */

// @lc code=start
package main

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m := len(nums1)
	n := len(nums2)

	mid := (m + n) / 2
	if (m+n)%2 == 1 {
		return findKTh(nums1, 0, m-1, nums2, 0, n-1, mid+1)
	}

	v1 := findKTh(nums1, 0, m-1, nums2, 0, n-1, mid)
	v2 := findKTh(nums1, 0, m-1, nums2, 0, n-1, mid+1)

	return (v1 + v2) / 2
}

func findKTh(nums1 []int, start1, end1 int, num2 []int, start2, end2, k int) float64 {
	m := end1 - start1 + 1
	n := end2 - start2 + 1

	if m > n {
		return findKTh(num2, start2, end2, nums1, start1, end1, k)
	}

	if m == 0 {
		return float64(num2[start2+k-1])
	}

	if k == 1 {
		return float64(min(nums1[start1], num2[start2]))
	}

	i := start1 + min(m, k/2) - 1
	j := start2 + min(n, k/2) - 1

	if nums1[i] < num2[j] {
		return findKTh(nums1, i+1, end1, num2, start2, end2, k-(i-start1+1))
	}

	return findKTh(nums1, start1, end1, num2, j+1, end2, k-(j-start2+1))

}

func min(n1, n2 int) int {
	if n1 < n2 {
		return n1
	}

	return n2
}

// @lc code=end
