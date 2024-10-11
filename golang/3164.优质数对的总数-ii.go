/*
 * @lc app=leetcode.cn id=3164 lang=golang
 *
 * [3164] 优质数对的总数 II
 *
 * https://leetcode.cn/problems/find-the-number-of-good-pairs-ii/description/
 *
 * algorithms
 * Medium (30.33%)
 * Likes:    25
 * Dislikes: 0
 * Total Accepted:    9.5K
 * Total Submissions: 25.5K
 * Testcase Example:  '[1,3,4]\n[1,3,4]\n1'
 *
 * 给你两个整数数组 nums1 和 nums2，长度分别为 n 和 m。同时给你一个正整数 k。
 *
 * 如果 nums1[i] 可以被 nums2[j] * k 整除，则称数对 (i, j) 为 优质数对（0 <= i <= n - 1, 0 <= j
 * <= m - 1）。
 *
 * 返回 优质数对 的总数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums1 = [1,3,4], nums2 = [1,3,4], k = 1
 *
 * 输出：5
 *
 * 解释：
 *
 * 5个优质数对分别是 (0, 0), (1, 0), (1, 1), (2, 0), 和 (2, 2)。
 *
 *
 * 示例 2：
 *
 *
 * 输入：nums1 = [1,2,4,12], nums2 = [2,4], k = 3
 *
 * 输出：2
 *
 * 解释：
 *
 * 2个优质数对分别是 (3, 0) 和 (3, 1)。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= n, m <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^6
 * 1 <= k <= 10^3
 *
 *
 */

// @lc code=start
package main

func numberOfPairs(nums1 []int, nums2 []int, k int) int64 {
	records := map[int]int{}

	for _, x := range nums1 {
		if x%k != 0 {
			continue
		}

		x /= k
		for i := 1; i*i <= x; i++ {
			if x%i != 0 {
				continue
			}

			records[i]++
			if i*i < x {
				records[x/i]++
			}
		}
	}

	var count int64
	for _, v := range nums2 {
		count += int64(records[v])
	}

	return count
}

// @lc code=end
