/*
 * @lc app=leetcode.cn id=1287 lang=golang
 *
 * [1287] 有序数组中出现次数超过25%的元素
 *
 * https://leetcode.cn/problems/element-appearing-more-than-25-in-sorted-array/description/
 *
 * algorithms
 * Easy (58.21%)
 * Likes:    91
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 55.2K
 * Testcase Example:  '[1,2,2,6,6,6,6,7,10]'
 *
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 *
 * 请你找到并返回这个整数
 *
 *
 *
 * 示例：
 *
 *
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 *
 *
 */

// @lc code=start
package main

func findSpecialInteger(arr []int) int {
	n, l := len(arr), 0
	for r := 1; r < n; r++ {
		if arr[r] == arr[l] {
			continue
		}

		if r-l > n/4 {
			break
		}

		l = r
	}

	return arr[l]

}

// @lc code=end
